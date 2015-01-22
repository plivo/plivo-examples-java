package com.plivo.test;

import java.util.LinkedHashMap;

import com.plivo.helper.api.client.*;

import com.plivo.helper.api.response.message.MessageResponse;
import com.plivo.helper.exception.PlivoException;

public class BulkMessage {

    public static void main(String[] args) {
        
        String authId = "Your AUTH_ID";
        String authToken = "Your AUTH_TOKEN";

        RestAPI api = new RestAPI(authId, authToken, "v1");

        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("src", "1111111111"); // Sender's phone number with country code
        parameters.put("dst", "2222222222<3333333333"); // Receivers' phone numbers with country code. The numbers are separated by "<" delimiter.
        parameters.put("text", "Hi, from Plivo"); // Your SMS text message
        
        try {
            // Send the messages
            MessageResponse msgResponse = api.sendMessage(parameters);
            // Print the Api ID
            System.out.println("Api ID : " + msgResponse.apiId);
            // Print the Response Message
            System.out.println("Message : " + msgResponse.message);
            // Loop through the Message UUID
            if (msgResponse.serverCode == 202) {
                int count = msgResponse.messageUuids.size();
                for (int i = 0 ; i < count ; i++){
                    // Print the Message UUID
                    System.out.println("Message UUID : " + msgResponse.messageUuids.get(i).toString());
                }
            } else {
                System.out.println(msgResponse.error); 
            }
        } catch (PlivoException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}

// Sample output
/*
Api ID : d83c1874-a208-11e4-b932-22000ac50fac
Message : message(s) queued
Message UUID : d8598e7c-a208-11e4-b328-22000afd044b
Message UUID : d85a0672-a208-11e4-890b-22000aec819c
*/