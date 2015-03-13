// recordUsingApi.java
package plivoexample;

import java.io.IOException;

import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.xml.elements.GetDigits;
import com.plivo.helper.xml.elements.PlivoResponse;
import com.plivo.helper.xml.elements.Speak;
import com.plivo.helper.xml.elements.Wait;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class recordUsingApi extends HttpServlet {
    private static final long serialVersionUID = 1L;    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        PlivoResponse response = new PlivoResponse();
        GetDigits gd = new GetDigits();
        gd.setAction("https://dry-fortress-4047.herokuapp.com/record_api_action");
        gd.setMethod("GET");
        gd.setTimeout(7);
        gd.setNumDigits(1);
        gd.setRetries(1);
        gd.setRedirect(false);
        
        Speak spk = new Speak("Press 1 to record this call");
        Wait wait = new Wait();
        wait.setLength(10);
        
        try {
            gd.append(spk);
            response.append(gd);
            response.append(wait);
            System.out.println(response.toXML());
            resp.addHeader("Content-Type", "text/xml");
            resp.getWriter().print(response.toXML());;
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
        context.addServlet(new ServletHolder(new recordUsingApi()),"/record_api");
        context.addServlet(new ServletHolder(new recordApiAction()),"/record_api_action");
        server.start();
        server.join();
    }
}

/*
Sample Output
<Response>
    <GetDigits redirect="false" retries="1" method="GET" numDigits="1" action="https://dry-fortress-4047.herokuapp.com/record_api_action" timeout="7">
        <Speak>Press 1 to record this call</Speak>
    </GetDigits><Wait length="10"/>
</Response>
*/

//recordApiAction.java
package plivoexample;

import java.io.IOException;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plivo.helper.api.client.RestAPI;
import com.plivo.helper.api.response.response.Record;
import com.plivo.helper.exception.PlivoException;

public class recordApiAction extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        String digit = req.getParameter("Digits");
        String call_uuid = req.getParameter("CallUUID");
        System.out.println("Digit : " + digit + " Call UUID : " + call_uuid);
        resp.getWriter().print("Digit : " + digit + " Call UUID : " + call_uuid);
        
        String auth_id = "Your AUTH_ID";
        String auth_token = "Your AUTH_TOKEN";
        
        RestAPI api = new RestAPI(authId, authToken, "v1");
        
        if(digit.equals("1"))
        {
            LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
            parameters.put("call_uuid",call_uuid);
            try
            {
                Record record = api.record(parameters);
                System.out.println(record);
            }
            catch (PlivoException e)
            {
                System.out.println(e.getLocalizedMessage());
            }
        }
        else
        {
            System.out.println("Wrong Input");
        }
    }
}

/*
Sample Output

Digit : 1 Call UUID : fa494fea-c954-11e4-9a6a-f58c44fe61dd
Record [
    serverCode=202, 
    url=http://s3.amazonaws.com/recordings_2013/67673232-9594-11e4-baad-842b2b17453e.mp3, 
    message=call recording started, 
    error=null, 
    api_id=ffb17b1a-c954-11e4-ac1f-22000ac51de6
]
*/