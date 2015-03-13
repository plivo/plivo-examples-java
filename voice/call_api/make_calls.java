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
        parameters.put("to","2222222222"); // The phone numer to which the all has to be placed
        parameters.put("from","1111111111"); // The phone number to be used as the caller id
        parameters.put("answer_url","https://dry-fortress-4047.herokuapp.com/speak"); // The URL invoked by Plivo when the outbound call is answered
        parameters.put("answer_method","GET"); // method to invoke the answer_url

        try {
           Call resp = api.makeCall(parameters);
           System.out.println(getFields(resp));           
        }catch (PlivoException e){  
           System.out.println(e.getLocalizedMessage());
        }
    }
}

/*
Sample Output
serverCode=201
message=call fired
requestUUID=df0657fb-a275-4c36-bdd3-f82e6a6c3ed0
apiId=14e2c8e2-b602-11e4-af95-22000ac54c79
error=null
*/
