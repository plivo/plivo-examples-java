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
            System.out.println(msg);

            // Print the number of SMS units
            System.out.println("Units : " + msg.units);

            // Print the state of the message
                System.out.println("Message State : " + msg.messageState);
            
        } catch (PlivoException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}

// Sample Output
/*
Message [
    cloudRate=null, 
    carrierRate=null, 
    messageDirection=outbound, 
    toNumber=919663489033, 
    messageState=delivered, 
    totalAmount=0.02600, 
    fromNumber=18583650866, 
    messageUUID=0936ec98-7c4c-11e4-9bd8-22000afa12b9, 
    messageTime=2014-12-05 12:27:54+05:30, 
    resourceUri=/v1/Account/XXXXXXXXXXXXXXX/Message/0936ec98-7c4c-11e4-9bd8-22000afa12b9/, 
    messageType=sms, 
    totalRate=0.00650, 
    units=4, 
    error=null, 
    apiId=dc9dc0c2-c713-11e4-af95-22000ac54c79
]
Units : 4
Message State : delivered
*/