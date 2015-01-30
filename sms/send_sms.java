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
            System.out.print(getFields(msgResponse));
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

    // Get all the fields in the Response
    public static String getFields(Object obj) throws IllegalAccessException {
        StringBuffer buffer = new StringBuffer();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field f : fields) {
          if (!Modifier.isStatic(f.getModifiers())) {
            f.setAccessible(true);
            Object value = f.get(obj);
            buffer.append(f.getName());
            buffer.append("=");
            buffer.append("" + value);
            buffer.append("\n");
          }
        }
        return buffer.toString();
    }
}

// Sample Output
/*
serverCode=202
message=message(s) queued
messageUuids=[6a2aaa6c-a87a-11e4-a4ca-22000afd0b0c]
error=null
apiId=6a143d9a-a87a-11e4-96e3-22000abcb9af

Api ID : dbda0556-a206-11e4-a2d1-22000ac5040c
Message : message(s) queued
Message UUID : dbf3b582-a206-11e4-b328-22000afd044b
*/