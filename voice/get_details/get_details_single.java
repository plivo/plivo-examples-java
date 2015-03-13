package sending_sms.sending_sms;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;

import com.plivo.helper.api.client.*;
import com.plivo.helper.api.response.call.CDR;
import com.plivo.helper.exception.PlivoException;

public class App {
    public static void main(String[] args) throws IllegalAccessException {

        String auth_id = "Your AUTH_ID";
        String auth_token = "Your AUTH_TOKEN";
        
        RestAPI api = new RestAPI(authId, authToken, "v1");
        
        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("record_id", "55309cee-821d-11e4-9a73-498d468c930b"); // The ID of the call
        
        try {
            CDR cdr = api.getCDR(parameters);
            System.out.println(cdr);
        } catch (PlivoException e) {
            System.out.println(e.getLocalizedMessage());
        }
    } 
}