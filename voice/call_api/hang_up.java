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

        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("call_uuid","defb0706-86a6-11e4-b303-498d468c930b"); // UUID of the call to be hung up

        try {
            GenericResponse resp = api.hangupCall(parameters);
            System.out.println(resp);   
        }catch (PlivoException e){  
            System.out.println(e.getLocalizedMessage());
        }  
    }
}

/*
Sample Output
serverCode=204
message=no response
error=null
apiId=unknown
*/