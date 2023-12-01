package hu.leone.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/** SOAP 
 * @OneWay - a metudsnak csak input parameterei vannak
 * @WebParam, @WebResult - ki es bemeneti parameterek jellemzoit adhatjuk meg
 * @WebServiceRef - kliens oldalon tudunk egy webservice-hez referenciat definialni*/
@WebService(name = "ConverterService", targetNamespace = "http://hu.leone.webservice/")
public interface ConverterService {

	@WebMethod(operationName = "celsius2Fahrenheit")
	public float celsius2Fahrenheit(@WebParam(name = "celsius") float celsius) throws Exception;
	
	@WebMethod(operationName = "fahrenheit2celsius")
	public float fahrenheit2celsius(@WebParam(name = "fahrenheit") float fahrenheit);

}