package hu.leone.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**	AOP - Aspektus orientalt programozas 
 * 	pl. logolas, biztonsagi ellenorzes OO technikaval nehezkes
 * 	Ezeket valahol kulon implementaljuk, kodban csak deklaraljuk hova keruljenek egy illesztovel(weaver) */

public class Metrics {
	
	/**	Dobhat exception-t, hivhat JNDI-t, JDBC-t, JMS-t, mas EB-t vagy EntityManager-t
	 * 	InvocationContext: bemeno parameters, itt a HUFamount es toCurrency*/
	@AroundInvoke	//ezzel jeloljuk az inerceptor-t
	public Object profile(InvocationContext inv) throws Exception {
		/* TS1 idomeres */
		long TS1 = System.currentTimeMillis();
		try {
			//proba
			Object[] params = inv.getParameters();
			for(Object p : params) {
				System.out.println("params: " + p.toString());
			}
			return inv.proceed(); /* uzleti metodus meghivasa */
		} finally {
			/* TS2 idomeres */
			long TS2 = System.currentTimeMillis();
			
			/* valaszido */
			long responseTime = TS2 - TS1;
			System.out.println("<< " + inv.getMethod() + " >> took " + responseTime + "msec");
		}
	}
}
