package com.plivo.test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;

import com.plivo.helper.api.client.*;
import com.plivo.helper.api.response.call.Call;
import com.plivo.helper.exception.PlivoException;

public class App {
    public static void main(String[] args) throws IllegalAccessException {

        String auth_id = "Your AUTH_ID";
        String auth_token = "Your AUTH_TOKEN";
        
        RestAPI api = new RestAPI(auth_id, auth_token, "v1");
        

        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("to","2222222222<3333333333"); // The phone numers to which the all has to be placed. The numbers are separated by "<" delimiter.
        parameters.put("from","1111111111"); // The phone number to be used as the caller id
        parameters.put("answer_url","https://dry-fortress-4047.herokuapp.com/speak"); // The URL invoked by Plivo when the outbound call is answered
        parameters.put("answer_method","GET"); // method to invoke the answer_url

        try {
           BulkCall resp = api.makeBulkCall(parameters);
           System.out.println(resp);   
        }catch (PlivoException e){  
           System.out.println(e.getLocalizedMessage());
        }
    }
}

/*
Sample Output
serverCode: 201
message: calls fired
requestUUID: [1ddf96f2-fed2-4412-8fbc-fb10bf77b9b3, 6695c385-7fc5-4bbb-bb36-f26fc1e0ca67]
apiId: 50d7d542-c8cf-11e4-8ccf-22000afb14f7
error: null
*/
