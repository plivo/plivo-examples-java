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
        parameters.put("number","1111111111");
        parameters.put("app_id","16632742496743552");

        try {
            GenericResponse resp = api.linkApplicationNumber(parameters);
            System.out.println(getFields(resp));
        }catch (PlivoException e){  
            System.out.println(e.getLocalizedMessage());
        }  
        
        // Unlink an application from a phone number
        LinkedHashMap<String, String> param = new LinkedHashMap<String, String>();
        param.put("number","1111111111");
        
        try {
            GenericResponse resp = api.unlinkApplicationNumber(param);
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
Link an application to a phone number
serverCode=202
message=changed
error=null
apiId=4b903fea-b6a3-11e4-9107-22000afaaa90

Unlink an application from a phone number
serverCode=202
message=changed
error=null
apiId=c2772164-b6a3-11e4-b423-22000ac8a2f8