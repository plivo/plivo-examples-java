package sending_sms.sending_sms;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;

import com.plivo.helper.api.client.*;
import com.plivo.helper.api.response.response.GenericResponse;
import com.plivo.helper.exception.PlivoException;

public class App {
    public static void main(String[] args) throws IllegalAccessException {

        String auth_id = "Your AUTH_ID";
        String auth_token = "Your AUTH_TOKEN";
        
        RestAPI api = new RestAPI(auth_id, auth_token, "v1");
        
        // Link an application to a phone number
        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("number","12105030864");
        parameters.put("app_id","27082215185108636");

        try {
            GenericResponse resp = api.linkApplicationNumber(parameters);
            System.out.println(resp);
        }catch (PlivoException e){  
            System.out.println(e.getLocalizedMessage());
        }  
        
        // Unlink an application from a phone number
        LinkedHashMap<String, String> param = new LinkedHashMap<String, String>();
        param.put("number","12105030864");
        
        try {
            GenericResponse resp = api.unlinkApplicationNumber(param);
            System.out.println(resp);
        }catch (PlivoException e){  
            System.out.println(e.getLocalizedMessage());
        }
        
    }
}


/*
Sample Output
Link an application to a phone number
GenericResponse [
    serverCode=202, 
    message=changed, 
    error=null, 
    apiId=4c08dfc8-c723-11e4-af95-22000ac54c79
]

Unlink an application from a phone number
GenericResponse [
    serverCode=202, 
    message=changed, 
    error=null, 
    apiId=eccbd56e-c723-11e4-ac1f-22000ac51de6
]