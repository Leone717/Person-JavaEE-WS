package hu.leone;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.AccessTimeout;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * Session Bean implementation class CounterSessionBean
 */
@Singleton(mappedName = "leoneBeans.CounterSessionBean")
@LocalBean
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@Startup
public class CounterSessionBean {

	@Resource(mappedName = "LEO_ConnectionFactory")
	private ConnectionFactory connFact;
	
	@Resource(mappedName = "LEO_Queue")
	private Queue queue;
	
	private Connection connection;
	
	private static int counter = 0;
	private static String lastCallTime;
	private static List<String> counterList = new LinkedList<String>();
	
	public CounterSessionBean() {
    }
    
	@PostConstruct
	public void setup() {
		try {
			connection = connFact.createConnection();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	@PreDestroy
	public void cleanup() {
		try {
			connection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	@Lock(LockType.WRITE)//nem engedelyezett a konkurrens hozzaferes, ez az alapbeallitas -> ConcurrentAccessTimeOutException
	@AccessTimeout(value = 0)//ennyit var a konkurrens hivas a lock feloldasara, lejarta utan 
    public void function() throws InterruptedException {
    	counter++;
    	
    	//Thread.sleep(20000);
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    	lastCallTime = sdf.format(new Date());
    	counterList.add(lastCallTime);
    	
    	/* create connection es session */
		try (Session session = connection.createSession()) {

			/* create producer */
			MessageProducer producer = session.createProducer(queue);

			/* create message */
			TextMessage message = session.createTextMessage();
			message.setText(new Date() + "\tfunction called");
			message.setStringProperty("caller", "Leone");
			
			/* send message */
			producer.send(message);
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
    }

	@Lock(LockType.READ) //engedelyezett a konkurrens hozzaferes, ha nincs parhuzamos iras 
	public int getCounter() {
		return counter;
	}

	@Lock(LockType.READ)
	public String getLastCallTime() {
		return lastCallTime;
	}
    
    @Lock(LockType.READ)
    public String[] getCounterList() {
		return (String[])counterList.toArray(new String[counterList.size()]);
	}


}

