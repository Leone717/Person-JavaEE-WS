package hu.leone.test;

import webservice.leone.hu.ConverterService;
import webservice.leone.hu.ConverterService_Service;
import webservice.leone.hu.Exception_Exception;

public class ConverterServiceClient {

	private static ConverterService_Service service = new ConverterService_Service();

	public static void main(String[] args) throws Exception_Exception {

		ConverterService converterService = service.getConverterServicePort();

		float fahrenheit = converterService.celsius2Fahrenheit(36);
		System.out.println("36 celsius = " + fahrenheit + " fahrenheit");

	}

}
