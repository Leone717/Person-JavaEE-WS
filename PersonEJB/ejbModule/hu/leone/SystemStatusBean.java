package hu.leone;

import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * Session Bean implementation class SystemStatusBean
 * 
 * nem hasznalhato stateful bean-eknel
 */
//@DependsOn - az inicialas egy masik singleton-tol fugg
@Singleton(mappedName = "leoneBeans.SystemStatusBean")//kontener kezeli a konkurrens hozzaferest 
@LocalBean
@Startup //azonnal jojjon letre egy peldany, mivel senki nem használja, deploy utan azonnal csinal egy peldanyt, a timer is elindul
public class SystemStatusBean {

    public SystemStatusBean() {
    }
    
    /*@Schedules(	
    		{@Schedule(hour = "*", minute = "5", second = "0"),
    			@Schedule(hour = "*", minute = "30", second = "0")})*/
    @Schedule(hour = "*", minute = "*", second = "0,30")
    public void printSystemStatus() {
    	String systemStatus = System.currentTimeMillis() + "\tFree memory=" + Runtime.getRuntime().freeMemory() + " bytes";
    	//sysout
    	System.out.println(systemStatus);
    }

}