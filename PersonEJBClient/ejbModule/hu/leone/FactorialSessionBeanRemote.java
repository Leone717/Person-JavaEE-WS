package hu.leone;

import java.math.BigInteger;
import java.util.concurrent.Future;

import javax.ejb.Remote;

@Remote
public interface FactorialSessionBeanRemote {

	public Future<BigInteger> calculateFactorial(int num);
}
