package com.plivo.test;

import java.util.LinkedHashMap;

import com.plivo.helper.api.client.*;

import com.plivo.helper.api.response.message.MessageResponse;
import com.plivo.helper.exception.PlivoException;

public class SendAlphanumeric {

    public static void main(String[] args) {
        
        String authId = "Your AUTH_ID";
        String authToken = "Your AUTH_TOKEN";

        RestAPI api = new RestAPI(authId, authToken, "v1");

        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("src", "ALPHA-ID"); // Alphanumeric Sender ID
        parameters.put("dst", "1111111111"); // Receiver's phone number with country code
        parameters.put("text", "Hi, from Plivo"); // Your SMS text message
        
        try {
            // Send the Message
            MessageResponse msgResponse = api.sendMessage(parameters);
            // Print the Api ID
            System.out.println("Api ID : " + msgResponse.apiId);
            // Print the Response Message
            System.out.println("Message : " + msgResponse.message);
            if (msgResponse.serverCode == 202) {
                // Print the Message UUID
                System.out.println("Message UUID : " + msgResponse.messageUuids.get(i).toString());
            } else {
                System.out.println(msgResponse.error); 
            }
        } catch (PlivoException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}

// Sample Output
/*
Api ID : 0b743dea-a20c-11e4-b423-22000ac8a2f8
Message : message(s) queued
Message UUID : 0b8a4676-a20c-11e4-890b-22000aec819c
*/