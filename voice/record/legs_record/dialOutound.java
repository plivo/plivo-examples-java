package plivoexample;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;

import com.plivo.helper.api.client.RestAPI;
import com.plivo.helper.api.response.response.GenericResponse;
import com.plivo.helper.api.response.response.Record;
import com.plivo.helper.exception.PlivoException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class dialOutbound extends HttpServlet {
    private static final long serialVersionUID = 1L;    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        // The Callback URL of Dial will make a request to the Record API which will record only the B Leg
        // Play API is invoked which will play a music only on the B Leg.

        String event = req.getParameter("Event");
        String call_uuid = req.getParameter("DialBLegUUID");
        
        if (event.equals("DialAnswer"))
        {
            String auth_id = "Your AUTH_ID";
            String auth_token = "Your AUTH_TOKEN";
            
            RestAPI api1 = new RestAPI(authId, authToken, "v1");
            RestAPI api2 = new RestAPI(authId, authToken, "v1");
            
            LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
            parameters.put("call_uuid", call_uuid);
            parameters.put("callback_url", "https://dry-fortress-4047.herokuapp.com/recording_callback");
            parameters.put("callback_method", "GET");
            
            LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
            params.put("call_uuid", call_uuid);
            params.put("urls", "https://s3.amazonaws.com/plivocloud/Trumpet.mp3");
            
            try{
                Record record = api1.record(parameters);
                System.out.println(getFields(record));
                
                GenericResponse play = api2.play(params);
                System.out.println(getFields(play));
            }catch (PlivoException e){  
                System.out.println(e.getLocalizedMessage());
            } catch (IllegalAccessException e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
        else
        {
            System.out.println("Invalid");
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

Output of the Record API request
api_id=5cbb3612-bb30-11e4-ac1f-22000ac51de6
message=async api spawned
serverCode=200
url=null
error=null

Output of the Play XML request
serverCode=202
apiId=5d1a1a42-bb30-11e4-af95-22000ac54c79
message=play started
error=null
*/