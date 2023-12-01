package hu.leone;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import hu.leone.datatypes.Book;

/**
 * Message-Driven Bean implementation class for: PrinterMDB
 * 
 * P2P(Point-To_point): queue, egy kliens egy cimzettnek kuld uzenetet
 * 1-N(Publish-Subscriber): topic, egy kliens tobb cimzettnek kuld uzentet
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "LEO_Queue"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "LEO_Queue")
public class PrinterMDB implements MessageListener {

    public PrinterMDB() {
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
        System.out.println("onMessage() called");
        
        String msg = "";
        
        try(PrintWriter pw = new PrintWriter(new FileWriter("C:\\Users\\mike\\Desktop\\printer.txt", true))) {

            if(message instanceof TextMessage) {
            	TextMessage textMsg = (TextMessage) message;
            	String caller = textMsg.getStringProperty("caller");
            	msg = caller + "\t" + textMsg.getText();
            }
            if(message instanceof ObjectMessage) {
            	ObjectMessage objMsg = (ObjectMessage) message;
            	Book book = (Book) objMsg.getObject();
            	msg = book.toString(); 
            }

        	pw.println( msg );
        	
        } catch (IOException | JMSException e) {
			e.printStackTrace();
		}
        System.out.println("onMessage() - message processed");
        
    }

}

