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
messageUuids=[9d787344-a87b-11e4-890b-22000aec819c]
error=null
apiId=9d633d26-a87b-11e4-ac1f-22000ac51de6
Api ID : 9d633d26-a87b-11e4-ac1f-22000ac51de6
Message : message(s) queued
Message UUID : 9d787344-a87b-11e4-890b-22000aec819c
*/