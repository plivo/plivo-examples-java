package com.plivo.test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;

import com.plivo.helper.api.client.*;
import com.plivo.helper.api.response.call.Call;
import com.plivo.helper.api.response.call.LiveCallFactory;
import com.plivo.helper.exception.PlivoException;

public class App {
    public static void main(String[] args) throws IllegalAccessException {

        String auth_id = "Your AUTH_ID";
        String auth_token = "Your AUTH_TOKEN";
        
        RestAPI api = new RestAPI(auth_id, auth_token, "v1");
        
        // API ID is returned for every API request. 
        // Request UUID is request id of the call. This ID is returned as soon as the call is fired irrespective of whether the call is answered or not

        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("to","2222222222"); // The phone numer to which the all has to be placed
        parameters.put("from","1111111111"); // The phone number to be used as the caller id
        parameters.put("answer_url","https://dry-fortress-4047.herokuapp.com/speak"); // The URL invoked by Plivo when the outbound call is answered
        parameters.put("answer_method","POST"); // method to invoke the answer_url

        try {
            Call resp = api.makeCall(parameters);
            System.out.println("API ID : " + resp.apiId)); 
            System.out.println("Request UUID : " + resp.requestUUID)); 
        }catch (PlivoException e){  
            System.out.println(e.getLocalizedMessage());
        }

        // Call UUID is th id of a live call. This ID is returned only after the call is answered.
        RestAPI apis = new RestAPI(authId, authToken, "v1");
        
        try {
            LiveCallFactory lc = apis.getLiveCalls();
            System.out.println("Call UUID : ");
            int count = lc.liveCallList.size();
            for (int i = 0; i < count; i++)
            {
                System.out.println(lc.liveCallList.get(i));
            }
        } catch (PlivoException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}

/*
Sample Output

API ID : 14e2c8e2-b602-11e4-af95-22000ac54c79
Request UUID : df0657fb-a275-4c36-bdd3-f82e6a6c3ed0

Call UUID :
bcda8f86-b818-11e4-b703-6b862a4e0d56
*/
