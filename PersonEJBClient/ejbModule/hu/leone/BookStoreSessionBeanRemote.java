package hu.leone;

import javax.ejb.Remote;

import hu.leone.datatypes.Book;

@Remote
public interface BookStoreSessionBeanRemote {

	public void toBasket(Book book);
	public Book[] getBookList();
	void rentBooks();
}
