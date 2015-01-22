package com.plivo.test;

import java.util.LinkedHashMap;

import com.plivo.helper.api.client.*;

import com.plivo.helper.api.response.message.MessageResponse;
import com.plivo.helper.exception.PlivoException;

public class GetDetails {

    public static void main(String[] args) {

        String authId = "Your AUTH_ID";
        String authToken = "Your AUTH_TOKEN";

        RestAPI api = new RestAPI(authId, authToken, "v1");

        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("record_id", "0936ec98-7c4c-11e4-9bd8-22000afa12b9"); // Message UUID for which the details have to be retrieved
        
        try {
            Message msg = api.getMessage(parameters);

            // Print the Message Details
            System.out.println("Api ID : " + msg.apiId);
            System.out.println("From Number : " + msg.fromNumber);
            System.out.println("To Number : " + msg.toNumber);
            System.out.println("Message Direction : " + msg.messageDirection);
            System.out.println("Message State : " + msg.messageState);
            System.out.println("Message UUID : " + msg.messageUUID);
            System.out.println("Units : " + msg.units);
            
        } catch (PlivoException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}

// Sample Output
/*
Api ID : 39022fde-a215-11e4-b932-22000ac50fac
From Number : 1111111111
To Number : 2222222222
Message Direction : outbound
Message State : delivered
Message UUID : 0936ec98-7c4c-11e4-9bd8-22000afa12b9
Units : 4
*/