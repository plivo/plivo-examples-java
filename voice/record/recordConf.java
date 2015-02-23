package plivoexample;

import java.io.IOException;

import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.xml.elements.Conference;
import com.plivo.helper.xml.elements.PlivoResponse;
import com.plivo.helper.xml.elements.Speak;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class recordConf extends HttpServlet {
    private static final long serialVersionUID = 1L;    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        PlivoResponse response = new PlivoResponse();
        Speak spk = new Speak("You will now be placed into a demo conference. This is brought to you by Plivo. To know more visit us at plivo.com");
        Conference conf = new Conference("demo"); // Name of the Conference
        conf.setEnterSound("beep:2"); // Used to play a sound when a member enters the conference
        conf.setCallbackUrl("https://dry-fortress-4047.herokuapp.com/conf_callback"); // If specified, information is sent back to this URL
        conf.setCallbackMethod("GET"); // Method used to notify callbackUrl using GET or POST.
        
        try {
            response.append(spk);
            response.append(conf);
            System.out.println(response.toXML());
            resp.addHeader("Content-Type", "text/xml");
            resp.getWriter().print(response.toXML());
        } catch (PlivoException e) {
            e.printStackTrace();
        }       
    }

    public static void main(String[] args) throws Exception {
        String port = System.getenv("PORT");
        if(port==null)
            port ="8000";
        Server server = new Server(Integer.valueOf(port));
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new recordConf()),"/record_conf");
        context.addServlet(new ServletHolder(new recordConfCallback()),"/record_conf_callback");
        server.start();
        server.join();
    }
}

/*
Sample Output
<Response>
    <Speak>
        You will now be placed into a demo conference. This is brought to you by Plivo. To know more visit us at plivo.com
    </Speak>
    <Conference callbackUrl="https://dry-fortress-4047.herokuapp.com/conf_callback" enterSound="beep:2" callbackMethod="GET">demo</Conference>
</Response>
*/

//recordConfCallback
package plivoexample;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plivo.helper.api.client.RestAPI;
import com.plivo.helper.api.response.response.Record;
import com.plivo.helper.exception.PlivoException;

public class recordConfCallback extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String conf_name = req.getParameter("ConferenceName");
        String event = req.getParameter("Event");
        
        if (event.equals("ConferenceEnter"))
        {
            String auth_id = "Your AUTH_ID";
            String auth_token = "Your AUTH_TOKEN";
                
            RestAPI api = new RestAPI(authId, authToken, "v1");
            
            LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
            parameters.put("conference_name", conf_name); // Name of the conference
            
            try{
                Record recordconf = api.recordConference(parameters);
                System.out.println(getFields(recordconf));
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

serverCode=202
url=http://s3.amazonaws.com/recordings_2013/d9579944-bb3c-11e4-94a0-0026b959a530.mp3
message=conference recording started
error=null
api_id=d9472aea-bb3c-11e4-ac1f-22000ac51de6
*/