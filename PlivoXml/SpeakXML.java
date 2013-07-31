package com.plivo.helper;

import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.xml.elements.PlivoResponse;
import com.plivo.helper.xml.elements.Speak;

public class HangupCall {
 	public static void main(String[] args) {
		PlivoResponse response = new PlivoResponse();
        Speak speak = new Speak("Plivo calling.");

        // Set various attributes for speak
        speak.setLanguage("en-US");
        speak.setVoice("WOMAN");
        
        try {
            response.append(speak);
            System.out.println(response.toXML());
        } catch (PlivoException e) {
            System.out.println(e.getLocalizedMessage());
        }
	}
}
