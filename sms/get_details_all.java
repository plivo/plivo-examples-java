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

        try {
            MessageFactory msg = api.getMessages();
        
            System.out.println("Api ID : " + msg.apiId);
            System.out.println("Meta : ");
            System.out.println("Limit : " + msg.meta.limit);
            System.out.println("Offset : " + msg.meta.offset);
            System.out.println("Total Count : " + msg.meta.totalCount);
            System.out.println("Next : " + msg.meta.next);
            System.out.println("Previous : " + msg.meta.previous);
            
            int count = msg.messageList.size();
            System.out.println("Objects : ");
            for (int i = 0 ; i < count; i++){
                System.out.println("Api ID : " + msg.messageList.get(i).apiId);
                System.out.println("From Number : " + msg.messageList.get(i).fromNumber);
                System.out.println("Message Direction : " + msg.messageList.get(i).messageDirection);
                System.out.println("Message State : " + msg.messageList.get(i).messageState);
                System.out.println("Message Time : " + msg.messageList.get(i).messageTime);
                System.out.println("Message UUID : " + msg.messageList.get(i).messageUUID);
                System.out.println("Resource Uri : " + msg.messageList.get(i).resourceUri);
                System.out.println("To Number : " + msg.messageList.get(i).toNumber);
                System.out.println("Total Amount : " + msg.messageList.get(i).totalAmount);
            }
            
            
        } catch (PlivoException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}

// Sample Output
/*
Api ID : 195801f4-a21a-11e4-b153-22000abcaa64
Meta : 
Limit : 2
Offset : 5
Total Count : 393
Next : /v1/Account/XXXXXXXXXXXXXXX/Message/?limit=2&offset=7
Previous : /v1/Account/XXXXXXXXXXXXXXX/Message/?limit=2&offset=3
Objects : 
Api ID : null
From Number : 1111111111
Message Direction : outbound
Message State : sent
Message Time : 2015-01-22 11:43:51+04:00
Message UUID : 689c7430-a20a-11e4-b328-22000afd044b
Resource Uri : /v1/Account/XXXXXXXXXXXXXXX/Message/689c7430-a20a-11e4-b328-22000afd044b/
To Number : 2222222222
Total Amount : 0.01300
Api ID : null
From Number : 1111111111
Message Direction : outbound
Message State : sent
Message Time : 2015-01-22 11:32:40+04:00
Message UUID : d8598e7c-a208-11e4-b328-22000afd044b
Resource Uri : /v1/Account/XXXXXXXXXXXXXXX/Message/d8598e7c-a208-11e4-b328-22000afd044b/
To Number : 2222222222
Total Amount : 0.00650
*/