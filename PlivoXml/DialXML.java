package com.plivo.helper;

import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.xml.elements.Number;
import com.plivo.helper.xml.elements.Dial;
import com.plivo.helper.xml.elements.PlivoResponse;

public class DialXML {
    public static void main(String[] args) {
        PlivoResponse response = new PlivoResponse();
        Dial dial = new Dial();
		dial.setCallerId("12345678901");
        Number number = new Number("12345678901");

        dial.setAction("http://http://myserverurl/setredirect/");
        dial.setMethod("GET");

        try {
            dial.append(number);
            response.append(dial);

            System.out.println(response.toXML());
        } catch (PlivoException e) {
            System.out.println(e.getLocalizedMessage());
        }   
    }
}
