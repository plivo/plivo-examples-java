package com.plivo.test;

import java.util.LinkedHashMap;

import com.plivo.helper.api.client.*;

import com.plivo.helper.api.response.message.MessageResponse;
import com.plivo.helper.exception.PlivoException;

public class SendMessage {

    public static void main(String[] args) {

        String authId = "Your AUTH_ID";
        String authToken = "Your AUTH_TOKEN";

        RestAPI api = new RestAPI(authId, authToken, "v1");
        
        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("src", "1111111111"); // Sender's phone number with country code
        parameters.put("dst", "2222222222"); // Receiver's phone number with country code
        parameters.put("text", "Hi, from Plivo"); // Your SMS text message
        // Send Unicode text
        //parameters.put("text", "こんにちは、元気ですか？"); // Your SMS text message - Japanese
        //parameters.put("text", "Ce est texte généré aléatoirement"); // Your SMS text message - French
        parameters.put("url", "http://server/message/notification/"); // The URL to which with the status of the message is sent
        parameters.put("method", "GET"); // The method used to call the url
            
        try {
            // Send the message
            MessageResponse msgResponse = api.sendMessage(parameters);

            // Print the response
            System.out.println(msgResponse);
            // Print the Api ID
            System.out.println("Api ID : " + msgResponse.apiId);
            // Print the Response Message
            System.out.println("Message : " + msgResponse.message);

            if (msgResponse.serverCode == 202) {
                // Print the Message UUID
                System.out.println("Message UUID : " + msgResponse.messageUuids.get(0).toString());
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
MessageResponse [
    serverCode=202, message=message(s) queued, 
    messageUuids=[2b8dc180-c711-11e4-a564-22000ac6807d], 
    error=null, 
    apiId=2b765bf8-c711-11e4-8ccf-22000afb14f7
]
Api ID : 2b765bf8-c711-11e4-8ccf-22000afb14f7
Message : message(s) queued
Message UUID : 2b8dc180-c711-11e4-a564-22000ac6807d
*/