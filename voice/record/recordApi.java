package sending_sms.sending_sms;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;

import com.plivo.helper.api.client.*;
import com.plivo.helper.api.response.response.GenericResponse;
import com.plivo.helper.api.response.response.Record;
import com.plivo.helper.exception.PlivoException;

public class App {
    public static void main(String[] args) throws IllegalAccessException {

        String auth_id = "Your AUTH_ID";
        String auth_token = "Your AUTH_TOKEN";
        
        // To record a call
        RestAPI api1 = new RestAPI(authId, authToken, "v1");
        
        LinkedHashMap<String, String> parameters1 = new LinkedHashMap<String, String>();
        parameters1.put("call_uuid","xxxxxxxxxxx"); // ID of the call
        parameters1.put("time_limit","40"); // Max recording duration in seconds
        parameters1.put("callback_url","https://dry-fortress-4047.herokuapp.com/recording_callback"); // The URL invoked by the API when the recording ends
        parameters1.put("callback_method","GET"); // The method which is used to invoke the callback_url
        parameters1.put("transcriptionType","auto"); // The type of transcription required 
        parameters1.put("transcriptionUrl", "https://dry-fortress-4047.herokuapp.com/transcription"); // The URL where the transcription while be sent from Plivo
        parameters1.put("transcriptionMethod", "GET"); // The method used to invoke transcriptionUrl
       

        try {
           Record resp1 = api1.record(parameters1);
           System.out.println(resp1);
        }catch (PlivoException e){  
           System.out.println(e.getLocalizedMessage());
        }
        
        // To stop recording a call
        RestAPI api2 = new RestAPI(authId, authToken, "v1");
        
        LinkedHashMap<String, String> parameters2 = new LinkedHashMap<String, String>();
        parameters2.put("call_uuid","xxxxxxxxxxx"); // ID of the call
        
        try{
            GenericResponse resp2 = api2.stopRecord(parameters2);
            System.out.println(resp2);
         }catch (PlivoException e){  
            System.out.println(e.getLocalizedMessage());
         }
        
        // To record a conference call
        RestAPI api3 = new RestAPI(authId, authToken, "v1");
        
        LinkedHashMap<String, String> parameters3 = new LinkedHashMap<String, String>();
        parameters3.put("conference_name","demo"); // The conference name
        parameters3.put("callback_url","https://dry-fortress-4047.herokuapp.com/recording_callback"); // The URL invoked by the API when the recording ends
        parameters3.put("callback_method","GET"); // The method which is used to invoke the callback_url
        
        try{
            Record resp3 = api3.record(parameters3);
            System.out.println(resp3);
        }catch (PlivoException e){  
            System.out.println(e.getLocalizedMessage());
        }
        
        // To stop recording a conference call
        RestAPI api4 = new RestAPI(authId, authToken, "v1");
        
        LinkedHashMap<String, String> parameters4 = new LinkedHashMap<String, String>();
        parameters4.put("conference_name","demo"); // The conference name
        
        try{
            GenericResponse resp4 = api4.stopRecord(parameters4);
            System.out.println(resp4);
         }catch (PlivoException e){  
            System.out.println(e.getLocalizedMessage());
         }
    } 
}