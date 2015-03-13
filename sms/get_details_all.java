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
        // Get details off all the messages
        try {
            MessageFactory msg = api.getMessages();
            // Print the complete response
            System.out.println(msg);
        } catch (PlivoException e) {
            System.out.println(e.getLocalizedMessage());
        }

        // Filtering the records
        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("limit", "2"); // The number of results per page
        parameters.put("offset", "0"); // The number of value items by which the results should be offset
        parameters.put("message_state", "sent"); // The state of the message to be filtered
        parameters.put("message_direction", "outbound"); // The direction of te message to be fltered

        try {
            MessageFactory msg = api.getMessages(parameters);
            // Print the complete response
            System.out.println(msg);
        } catch (PlivoException e) {
            System.out.println(e.getLocalizedMessage());
        }   
    }
}

// Sample Output without filter
/*
MessageFactory [
    serverCode=200, 
    meta=MessageMeta [
        previous=null, 
        totalCount=524, 
        offset=0, 
        limit=20, next=/v1/Account/XXXXXXXXXXXXXXX/Message/?limit=20&offset=20
    ], 
    apiId=470cf77a-c714-11e4-af95-22000ac54c79, 
    messageList=[
        Message [
            cloudRate=null, 
            carrierRate=null,
            messageDirection=outbound, 
            toNumber=2222222222, 
            messageState=delivered, 
            totalAmount=0.00650, 
            fromNumber=1111111111, 
            messageUUID=7e02984e-c713-11e4-8672-22000aff09d1, 
            messageTime=2015-03-10 16:22:06+05:30, 
            resourceUri=/v1/Account/XXXXXXXXXXXXXXX/Message/7e02984e-c713-11e4-8672-22000aff09d1/, 
            messageType=sms, 
            totalRate=0.00650, 
            units=1, 
            error=null, 
            apiId=null
        ], Message [
            cloudRate=null, 
            carrierRate=null, 
            messageDirection=outbound, 
            toNumber=3333333333, 
            messageState=delivered, 
            totalAmount=0.00650, 
            fromNumber=1111111111, 
            messageUUID=7e026888-c713-11e4-a564-22000ac6807d, 
            messageTime=2015-03-10 16:22:06+05:30, 
            resourceUri=/v1/Account/XXXXXXXXXXXXXXX/Message/7e026888-c713-11e4-a564-22000ac6807d/, 
            messageType=sms, 
            totalRate=0.00650, 
            units=1, 
            error=null, 
            apiId=null
        ]
    ], 
    error=null
]

Sample Ouput with filter

MessageFactory [
    serverCode=200, 
    meta=MessageMeta [
        previous=null, 
        totalCount=101, 
        offset=0, 
        limit=2, 
        next=/v1/Account/XXXXXXXXXXXXXXX/Message/?message_state=sent&limit=2&offset=2&message_direction=outbound
    ], 
    apiId=dae130ce-c714-11e4-af95-22000ac54c79, 
    messageList=[
        Message [
            cloudRate=null, 
            carrierRate=null, 
            messageDirection=outbound, 
            toNumber=2222222222, 
            messageState=sent, 
            totalAmount=0.00700, 
            fromNumber=1111111111, 
            messageUUID=25d0dd3c-bb53-11e4-8ced-22000ae3827c, 
            messageTime=2015-02-23 17:27:31+05:30, 
            resourceUri=/v1/Account/XXXXXXXXXXXXXXX/Message/25d0dd3c-bb53-11e4-8ced-22000ae3827c/, 
            messageType=sms, 
            totalRate=0.00350, 
            units=2, 
            error=null, 
            apiId=null
        ], Message [
            cloudRate=null, 
            carrierRate=null, 
            messageDirection=outbound, 
            toNumber=3333333333, 
            messageState=sent, 
            totalAmount=0.00700, 
            fromNumber=1111111111, 
            messageUUID=f22a2678-bb52-11e4-8ab5-22000af8012c, 
            messageTime=2015-02-23 17:26:05+05:30, 
            resourceUri=/v1/Account/XXXXXXXXXXXXXXX/Message/f22a2678-bb52-11e4-8ab5-22000af8012c/, 
            messageType=sms, 
            totalRate=0.00350, 
            units=2, 
            error=null, 
            apiId=null
        ]
    ], 
    error=null
]
*/