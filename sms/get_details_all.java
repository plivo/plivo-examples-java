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
            // Print the API ID
            System.out.println("Api ID : " + msg.apiId);
            // Print the Meta response
            System.out.println("Meta");
            System.out.println(getFields(msg.meta));
            // Print the objects
            System.out.println("Objects");
            int count = msg.messageList.size();
            for (int i = 0 ; i < count; i++){
                System.out.println(getFields(msg.messageList.get(i)));
            }
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
            // Print the API ID
            System.out.println("Api ID : " + msg.apiId);
            // Print the Meta response
            System.out.println("Meta");
            System.out.println(getFields(msg.meta));
            // Print the objects
            System.out.println("Objects");
            int count = msg.messageList.size();
            for (int i = 0 ; i < count; i++){
                System.out.println(getFields(msg.messageList.get(i)));
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

// Sample Output without filter
/*
Api ID : 20b76958-a87d-11e4-ac1f-22000ac51de6
Meta
previous=null
totalCount=479
offset=0
limit=20
next=/v1/Account/XXXXXXXXXXXXXXX/Message/?limit=20&offset=20

Objects
cloudRate=null
carrierRate=null
messageDirection=outbound
toNumber=1111111111
messageState=delivered
totalAmount=0.03680
fromNumber=2222222222
messageUUID=9d787344-a87b-11e4-890b-22000aec819c
messageTime=2015-01-30 16:29:20+04:00
resourceUri=/v1/Account/XXXXXXXXXXXXXXX/Message/9d787344-a87b-11e4-890b-22000aec819c/
messageType=sms
totalRate=0.03680
units=1
error=null
apiId=null

cloudRate=null
carrierRate=null
messageDirection=outbound
toNumber=1111111111
messageState=sent
totalAmount=0.00700
fromNumber=2222222222
messageUUID=242de67c-a87b-11e4-890b-22000aec819c
messageTime=2015-01-30 16:25:56+04:00
resourceUri=/v1/Account/XXXXXXXXXXXXXXX/Message/242de67c-a87b-11e4-890b-22000aec819c/
messageType=sms
totalRate=0.00350
units=2
error=null
apiId=null

Sample Ouput with filter

Api ID : ecfd64ce-a880-11e4-a2d1-22000ac5040c
Meta
previous=null
totalCount=96
offset=0
limit=2
next=/v1/Account/XXXXXXXXXXXXXXX/Message/?message_state=sent&limit=2&offset=2&message_direction=outbound

Objects
cloudRate=null
carrierRate=null
messageDirection=outbound
toNumber=1111111111
messageState=sent
totalAmount=0.00700
fromNumber=2222222222
messageUUID=242de67c-a87b-11e4-890b-22000aec819c
messageTime=2015-01-30 16:25:56+04:00
resourceUri=/v1/Account/XXXXXXXXXXXXXXX/Message/242de67c-a87b-11e4-890b-22000aec819c/
messageType=sms
totalRate=0.00350
units=2
error=null
apiId=null

cloudRate=null
carrierRate=null
messageDirection=outbound
toNumber=2222222222
messageState=sent
totalAmount=0.00350
fromNumber=1111111111
messageUUID=6a2aaa6c-a87a-11e4-a4ca-22000afd0b0c
messageTime=2015-01-30 16:20:44+04:00
resourceUri=/v1/Account/XXXXXXXXXXXXXXX/Message/6a2aaa6c-a87a-11e4-a4ca-22000afd0b0c/
messageType=sms
totalRate=0.00350
units=1
error=null
apiId=null


*/