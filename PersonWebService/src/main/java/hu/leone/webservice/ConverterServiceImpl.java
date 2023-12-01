package hu.leone.webservice;

import javax.jws.*;

/** SOAP - Simple Object Access Protocol: HTTP + XML - HTTP POST(Content-Type,Content-Length always)/GET request
 * - kulon sajat hibakezelest valosit meg az uzenetben
 * 
 * 	UDDI - Universal Description, Discovery and Integration(olyan szabalyrendszer, amivel bizonyos konytarakat, nyilvantartasokat hozunk letre)
 * 	WSDL(XML!) - Web Services Description Language - leirja milyen fgvei es adattipusai vannak a web szolgaltatasnak, WSDL-bol legenearlhatok
 * a kliens proxy osztalyai = XML formatum a vegpont szolgatatasainak leirasara. Lehetove teszi kulonfele uzenetmintak leirasat is
 * 	RPC - Remote Process Call
 * 	
 * SOA --> <-- Microservices
 * - SOA - Service Oriented Architecture
 * - Enterprise Bus Service (1 big) services or modules are shared and reused enterprise-wide
 * 
 * MicroService 
 * - Small specialised, reusable  componens, microservice 
 * - Better for cloud architecture, individual services that function independently
 * 
 * http://localhost:7001/console
 * Deployments -> PersonEAR -> ConverterService --> Testing --> ?WSDL(Test Point column)
 * IP address + 7001/PersonWebService/ConverterService --> Test
 * http request and response
 */

@WebService(portName = "ConverterServicePort", serviceName = "ConverterService", targetNamespace = "http://hu.leone.webservice/", endpointInterface = "hu.leone.webservice.ConverterService")
public class ConverterServiceImpl implements ConverterService {

	@Override
	public float celsius2Fahrenheit(float celsius) throws Exception {
		if (celsius < 0)
			throw new Exception("celsius nem lehet 0-nál kisebb!");
		float fahrenheit = (celsius * 9 / 5) + 32;
		return fahrenheit;
	}

	@Override
	public float fahrenheit2celsius(float fahrenheit) {
		float celsius = (fahrenheit - 32) * 5 / 9;
		return celsius;
	}

}
