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
        
        RestAPI api = new RestAPI(auth_id, auth_token, "v1");

        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("to","sip:abcd150108095716@phone.plivo.com"); // The phone numer to which the all has to be placed. Sip endpoints must be prefixed with sip:
        parameters.put("from","1111111111"); // The phone number to be used as the caller id
        parameters.put("answer_url","https://dry-fortress-4047.herokuapp.com/speak"); // The URL invoked by Plivo when the outbound call is answered
        parameters.put("answer_method","GET"); // Method to invoke the answer_url
        parameters.put("sip_headers", "Test=Sample"); // List of SIP headers in the form of 'key=value' pairs, separated by commas.

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
requestUUID=8501b9f8-7115-449c-8c41-4280e663bde1
apiId=b462f0e2-b6c8-11e4-9107-22000afaaa90
error=null

The SIP header can be seen as a query parameter in the answer_url
path="/speak?Direction=outbound&From=1111111111&ALegUUID=b4b71302-b6c8-11e4-95c7-fb0a4c731172&BillRate=0.00300&
To=sip%3Atest150108095716%40phone.plivo.com&X-PH-Test=Sample&CallUUID=b4b71302-b6c8-11e4-95c7-fb0a4c731172&
ALegRequestUUID=8501b9f8-7115-449c-8c41-4280e663bde1&RequestUUID=8501b9f8-7115-449c-8c41-4280e663bde1&
SIP-H-To=%3Csip%3Atest150108095716%40phone.plivo.com%3E%3Btag%3DqdF2R1oMgcOZioAZ6fuyWpVxPtMENeXC&CallStatus=in-progress&Event=StartApp"