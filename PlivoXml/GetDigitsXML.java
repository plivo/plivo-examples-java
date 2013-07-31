package com.plivo.helper;

import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.xml.elements.PlivoResponse;
import com.plivo.helper.xml.elements.GetDigits;
import com.plivo.helper.xml.elements.Speak;

public class HangupCall {
 	public static void main(String[] args) {
		PlivoResponse response = new PlivoResponse();
        Speak speak = new Speak("Press one for English. Press two for Spanish");
        GetDigits getdigits = new GetDigits();
        
        // Set the various attributes for Speak
        speak.setLanguage("en-US");
        speak.setVoice("WOMAN");
        
        // Set attributes for GetDigits
        getdigits.setAction("http://myserverurl/input/");
        getdigits.setMethod("GET");

        try {
            getdigits.append(speak);
            response.append(getdigits);
            System.out.println(response.toXML());
        } catch (PlivoException e) {
            System.out.println(e.getLocalizedMessage());
        }
	}
}
