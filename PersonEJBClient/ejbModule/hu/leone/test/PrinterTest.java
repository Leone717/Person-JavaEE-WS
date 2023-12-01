package hu.leone.test;

import java.util.Properties;
import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.oracle.webservices.impl.jms.wsdl.DeliveryMode;

import hu.leone.datatypes.Book;

public class PrinterTest {

	public static void main(String[] args) throws NamingException, JMSException {
		/* JNDI parameters */
		Properties p = new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
		p.put(Context.PROVIDER_URL, "t3://localhost:7001");
		// p.put(Context.PROVIDER_URL, "t3://10.0.2.107:7001");

		/* JNDI connection */
		InitialContext ctx = new InitialContext(p);

		/* JMS ConnectionFactory - lookup */
		ConnectionFactory connFact = (ConnectionFactory) ctx.lookup("LEO_ConnectionFactory");

		/* JMS Queue - lookup */
		Destination queue = (Destination) ctx.lookup("LEO_Queue");

		/**
		 * create connection és session boolean transacted = false; session =
		 * connection.createSession(transacted, Session.AUTO_ACKNOWLEDGE); automatikus
		 * visszaigazolas kuldese
		 */
		try (Connection connection = connFact.createConnection(); Session session = connection.createSession()) {

			/* create producer */
			MessageProducer producer = session.createProducer(queue);
			/*
			 * producer.setTimeToLive(1000); - eddig el az uzenet a rendszerben 1. mod
			 * producer.setDeliveryMode(javax.jms.DeliveryMode.PERSISTENT); - biztos celba
			 * er az uzenet producer.setPriority(7);
			 */

			/* create message */
			TextMessage message = session.createTextMessage();
			message.setText(new Date() + "\tWelcome here today!");
			message.setStringProperty("caller", "Leone");
			/*
			 * message.setJMSExpiration(1000); - eddig el az uzenet a rendszerben 2. mod
			 * message.setJMSPriority(5);
			 */

			/* uzenet kuldese */
			producer.send(message);

			ObjectMessage objectMessage = session
					.createObjectMessage(new Book("Think and Grow Rich", "Napoleon Hill", "2007"));
			producer.send(objectMessage);
			// connection.close();
			// session.close();
		} catch (JMSException ex) {
			ex.printStackTrace();
		}

	}

}
