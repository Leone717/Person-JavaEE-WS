package hu.leone;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Stateful;

import hu.leone.datatypes.Book;

/**
 * Session Bean implementation class BookStoreSessionBean
 * @Remove: futasa utan a kontener megszunteti
 * @PrePassivate: passzivalas, disk-re iras elott fut 
 * @PreActivate: aktivalas, disk-rol olvasas utan fut 
 * @PostConstructor: letrehozast kovetoen fut
 * @PreDestroy: a @Remove-val jelolt metodus utan hivodik meg
 * @ConcurrencyManagement(ConcurrencyManagementType.CONTAINER/BEAN) - konkurrens hozzaferes kezeles 
 */
@Stateful(mappedName = "leoneBeans.BookStoreSessionBean")
@LocalBean
public class BookStoreSessionBean implements BookStoreSessionBeanRemote, BookStoreSessionBeanLocal {

	private List<Book> basket;
	
    /**
     * Default constructor. 
     */
    public BookStoreSessionBean() {
        basket = new ArrayList<>();
    }

	@Override
	public void toBasket(Book book) {
		basket.add(book);
	}

	@Override
	public Book[] getBookList() {
		return (Book[])basket.toArray(new Book[basket.size()]);
	}

	@Override
	public void rentBooks() {
		System.out.println("Rent following books:");
		System.out.println(basket);
		
		resetBasket();
		
	}
	
	@Remove
	void resetBasket() {
		basket = null;
	}

}
