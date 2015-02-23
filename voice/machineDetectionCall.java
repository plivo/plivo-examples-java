// machineDetecttionCall.java
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
        parameters.put("answer_url","https://dry-fortress-4047.herokuapp.com/detect"); // The URL invoked by Plivo when the outbound call is answered
        parameters.put("answer_method","GET"); // method to invoke the answer_url
        parameters.put("machine_detection","true");
        parameters.put("machine_detection_time", "10000");
        parameters.put("machine_detection_url", "https://dry-fortress-4047.herokuapp.com/machine_detection");
        parameters.put("machine_detection_method", "GET");

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

// detect.java
package plivoexample;

import java.io.IOException;

import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.xml.elements.PlivoResponse;
import com.plivo.helper.xml.elements.Speak;
import com.plivo.helper.xml.elements.Wait;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class detect extends HttpServlet {
    private static final long serialVersionUID = 1L;    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        PlivoResponse response = new PlivoResponse();
        Wait wait = new Wait();
        wait.setLength(10);
        wait.setMinSilence(3000);
        wait.setSilence(true);
        Speak spk = new Speak("Hello Voicemail!");
        
        try {
            response.append(wait);
            response.append(spk);
            System.out.println(response.toXML());
            resp.addHeader("Content-Type", "text/xml");
            resp.getWriter().print(response.toXML());;
        } catch (PlivoException e) {
            e.printStackTrace();
        }       
    }

        String port = System.getenv("PORT");
        if(port==null)
            port ="8000";
        Server server = new Server(Integer.valueOf(port));
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new detect()),"/detect");
        context.addServlet(new ServletHolder(new machineDetection()),"/machine_detection");
        server.start();
        server.join();
    }
}


/*
Sample Output
<Response>
    <Wait minSilence="3000" silence="true" length="10"/>
    <Speak>Hello Voicemail!</Speak>
</Response>
*/

// machineDetection.java
package plivoexample;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class machineDetection extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String from_number = req.getParameter("From");
        String machine = req.getParameter("Machine");
        String to_number = req.getParameter("To");
        String call_uuid = req.getParameter("CallUUID");
        String event = req.getParameter("Event");
        String status = req.getParameter("CallStatus");
        System.out.println("From : " + from_number + " Machine : " + machine + " To : " + to_number + 
                            " Call UUID : " + call_uuid + " Event : " + event + " Call Status : " + status);
        resp.getWriter().print("From : " + from_number + " Machine : " + machine + " To : " + to_number + 
                " Call UUID : " + call_uuid + " Event : " + event + " Call Status : " + status);
    }
}

/*
Sample Output
From : 1111111111 Machine : true To : 2222222222 Call UUID : 0bee9dca-bb24-11e4-9838-f1e82e26fc28 Event : MachineDetection Call Status : in-progress
*/
