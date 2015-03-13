package sending_sms.sending_sms;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;

import com.plivo.helper.api.client.*;
import com.plivo.helper.api.response.call.LiveCallFactory;
import com.plivo.helper.exception.PlivoException;

public class App {
    public static void main(String[] args) throws IllegalAccessException {

        String auth_id = "Your AUTH_ID";
        String auth_token = "Your AUTH_TOKEN";
        
        RestAPI api = new RestAPI(authId, authToken, "v1");
        
       // LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        
        try {
            LiveCallFactory lc = api.getLiveCalls();
            System.out.println(lc);
        } catch (PlivoException e) {
            System.out.println(e.getLocalizedMessage());
        }
    } 
}

/*
Sample Output
API ID : be2006dc-b818-11e4-8ccf-22000afb14f7
liveCallList
bcda8f86-b818-11e4-b703-6b862a4e0d56
*/