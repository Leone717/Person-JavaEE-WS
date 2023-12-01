package hu.leone.test;

import java.math.BigInteger;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import hu.leone.FactorialSessionBeanRemote;

public class FactorialTest {

	public static void main(String[] args) throws NamingException, InterruptedException, ExecutionException {
		/* JNDI parameters */
		Properties p = new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY,
				"weblogic.jndi.WLInitialContextFactory");
		p.put(Context.PROVIDER_URL, "t3://localhost:7001");

		
		/* JNDI connection */
		InitialContext ctx = new InitialContext(p);
		
		/* Bean searching - lookup*/
		
		FactorialSessionBeanRemote ejb = (FactorialSessionBeanRemote) 
						ctx.lookup("leoneBeans.FactorialSessionBean#hu.leone.FactorialSessionBeanRemote");
		Future<BigInteger> result = ejb.calculateFactorial(50000);
		
		while(true) {
			if(result.isDone()) {
				System.out.println("Factorial = " + result.get());
				break;
			}
			
			System.out.println("Wait 500 msec");
			Thread.sleep(500);
		}
	}
}
