package hu.leone;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import hu.leone.interceptors.Metrics;

/**
 * Session Bean implementation class CurrencySessionBean
 */
@Stateless(mappedName = "leoneBeans.CurrencySessionBean")
@LocalBean /* No-interface view */
public class CurrencySessionBean implements CurrencySessionBeanRemote, CurrencySessionBeanLocal {

	private final Map<String, Integer> RATES;
	
    /**
     * Default constructor. 
     */
    public CurrencySessionBean() {
    	RATES = new HashMap<>();
        RATES.put("USD", 352);
        RATES.put("EUR", 377);
    }
    
    /** metodusok melyek "elfogjak" az uzleti metodushivast mielott az meghivasa kerul
     * megszakitjak a bean metodushivasat, modosithatjak a bemeno parametereket, akar akadalyozhatjak a tenyleges megivast
     * Session, Message-driven Entity Bean(EJB 2.1-es)-hez rendelhetok*/
    @Interceptors(Metrics.class)
    public double convertCurrency(double HUFamount, String toCurrency) throws Exception {
    	
    	if( !RATES.containsKey(toCurrency) ) {
    		throw new Exception("Currency is not found!");
    	}
    	
    	Thread.sleep((long) Math.random() * 2000);
    	 	
    	return HUFamount / RATES.get(toCurrency);
    }

}

