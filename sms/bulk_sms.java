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
            // Print the response
            System.out.println(msgResponse);
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

        // When an invalid number is given as dst parameter, an error will be thrown and the message will not be sent

        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("src", "1111111111"); // Sender's phone number with country code
        parameters.put("dst", "2222222222<33333"); // Receivers' phone numbers with country code. The numbers are separated by "<" delimiter.
        parameters.put("text", "Hi, from Plivo"); // Your SMS text message
        
        try {
            // Send the messages
            MessageResponse msgResponse = api.sendMessage(parameters);
            // Print the response
            System.out.println(msgResponse);
        } catch (PlivoException e) {
            System.out.println(e.getLocalizedMessage());
        }

    }
}

// Sample output
/*
MessageResponse [
    serverCode=202, 
    message=message(s) queued, 
    messageUuids=[7e026888-c713-11e4-a564-22000ac6807d, 7e02984e-c713-11e4-8672-22000aff09d1], 
    error=null, 
    apiId=7de80790-c713-11e4-b423-22000ac8a2f8
]
Message UUID : 7e026888-c713-11e4-a564-22000ac6807d
Message UUID : 7e02984e-c713-11e4-8672-22000aff09d1

Sample Output for invalid number

MessageResponse [
    serverCode=400, 
    message=null, 
    messageUuids=null, 
    error=1111 is not a valid phone number, 
    apiId=a7265314-c713-11e4-b423-22000ac8a2f8
]
1111 is not a valid phone number
*/