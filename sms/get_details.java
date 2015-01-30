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
            System.out.println(getFields(msg));

            // Print the number of SMS units
            System.out.println("Units : " + msg.units);

            // Print the state of the message
                System.out.println("Message State : " + msg.messageState);
            
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
cloudRate=null
carrierRate=null
messageDirection=outbound
toNumber=919663489033
messageState=delivered
totalAmount=0.02600
fromNumber=18583650866
messageUUID=0936ec98-7c4c-11e4-9bd8-22000afa12b9
messageTime=2014-12-05 10:57:54+04:00
resourceUri=/v1/Account/XXXXXXXXXXXXXXX/Message/0936ec98-7c4c-11e4-9bd8-22000afa12b9/
messageType=sms
totalRate=0.00650
units=4
error=null
apiId=67d1cf58-a87e-11e4-b423-22000ac8a2f8

Units : 4
Message State : delivered
*/