package com.plivo.helper;

import com.plivo.helper.xml.elements.PlivoResponse;
import com.plivo.helper.xml.elements.Dial;
import com.plivo.helper.xml.elements.Number;

public class DialXML {
 	public static void main(String[] args) {
 		PlivoResponse response = new PlivoResponse();
		Dial dial = new Dial(); 		
 		Number number = new Number();

 		// Set the number to be called
		number.setNumber("12345678901");

		// Set up dial
 		dial.addNumber(number);
 		dial.setAction("http://http://myserverurl/setredirect/");
 		dial.setMethod("GET");

		// Make the final response
 		response.addDial(dial);
 		
 		System.out.println(response.serializeToXML());
	}
}
