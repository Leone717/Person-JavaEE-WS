package hu.leone;

import java.math.BigInteger;
import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class FactorialSessionBean
 */
@Stateless(mappedName = "leoneBeans.FactorialSessionBean")
@LocalBean
@Asynchronous//elkuldi a kerest, folytatja a dolgat, kesobb kap valaszt, minden metodusa aszinkron lesz
public class FactorialSessionBean implements FactorialSessionBeanRemote, FactorialSessionBeanLocal {

    public FactorialSessionBean() {
        
    }
    
    /*Future: a kliens le tudja kerdezni a meghivott metodus eredmenyet, 
     * ellenorizheti hogy exception-t dobott-e, vagy leallithatja a futo hivast*/
    public Future<BigInteger> calculateFactorial(int num) {
    	System.out.println("calculateFactorial() called at " + System.currentTimeMillis());
    	
    	BigInteger result = BigInteger.ONE;
    	for(int i = 1; i <= num; i++) {
    		result = result.multiply(BigInteger.valueOf(i));
    	}
    	
    	return new AsyncResult<BigInteger>(result);
    	
    }

}
