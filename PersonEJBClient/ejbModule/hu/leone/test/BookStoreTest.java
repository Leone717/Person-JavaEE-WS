package hu.leone.test;

import java.util.Arrays;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import hu.leone.BookStoreSessionBeanRemote;
import hu.leone.datatypes.Book;

public class BookStoreTest {

	public static void main(String[] args) throws NamingException, InterruptedException {
		
		/* JNDI parameters */
		Properties p = new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY,
				"weblogic.jndi.WLInitialContextFactory");
		p.put(Context.PROVIDER_URL, "t3://localhost:7001");

		
		/* JNDI connection */
		InitialContext ctx = new InitialContext(p);
		
		/* Bean searching - lookup*/
		BookStoreSessionBeanRemote ejb = (BookStoreSessionBeanRemote) ctx.lookup("leoneBeans.BookStoreSessionBean#hu.leone.BookStoreSessionBeanRemote");
		
		Book book1 = new Book("Think and Grow Rich", "Napoleon Hill", "2007");
		ejb.toBasket(book1);
		
		Thread.sleep(20000);
		
		Book book2 = new Book("The Unknown Kimi Raikkonen", "Kari Hotakainen", "2018");
		ejb.toBasket(book2);
		
		System.out.println(Arrays.asList(ejb.getBookList()));
		
		ejb.rentBooks();
		
	}
}
