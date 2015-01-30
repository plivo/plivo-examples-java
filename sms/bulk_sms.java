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
            System.out.print(getFields(msgResponse));
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
            System.out.print(getFields(msgResponse));
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

// Sample output
/*
serverCode=202
message=message(s) queued
messageUuids=[f510e58e-a879-11e4-a4ca-22000afd0b0c, f5108382-a879-11e4-b328-22000afd044b]
error=null
apiId=f4f263fc-a879-11e4-b423-22000ac8a2f8
Message UUID : f510e58e-a879-11e4-a4ca-22000afd0b0c
Message UUID : f5108382-a879-11e4-b328-22000afd044b

Sample Output for invalid number

serverCode=400
message=null
messageUuids=null
error=11111 is not a valid phone number
apiId=e0421a42-a879-11e4-ac1f-22000ac51de6
11111 is not a valid phone number
*/