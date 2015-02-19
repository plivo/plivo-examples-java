package sending_sms.sending_sms;

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
        
        RestAPI api = new RestAPI(authId, authToken, "v1");
        
        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("to","2222222222"); // The phone number to which the all has to be placed
        parameters.put("from","1111111111"); // The phone number to be used as the caller id
        parameters.put("answer_url","https://dry-fortress-4047.herokuapp.com/response/conference"); // The URL invoked by Plivo when the outbound call is answered
        parameters.put("answer_method","GET"); // method to invoke the answer_url

        try {
           Call resp = api.makeCall(parameters);
           System.out.println(getFields(resp));           
        }catch (PlivoException e){  
           System.out.println(e.getLocalizedMessage());
        }
    } 

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

/*
Sample Output
serverCode=201
message=call fired
requestUUID=89eed36c-a57f-4838-a78f-640fdebaf6bc
apiId=7084114e-b81c-11e4-b932-22000ac50fac
error=null
*/