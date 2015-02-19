// conferenceXml.java
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

public class conferenceXml extends HttpServlet {
    private static final long serialVersionUID = 1L;    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        PlivoResponse response = new PlivoResponse();
        Speak spk = new Speak("You will now be placed into a demo conference. This is brought to you by Plivo. To know more visit us at plivo.com");
        Conference conf = new Conference("demo");
        conf.setEnterSound("beep:2");
        conf.setRecord(true);
        conf.setAction("https://dry-fortress-4047.herokuapp.com/conf_action");
        conf.setMethod("GET");
        conf.setCallbackUrl("https://dry-fortress-4047.herokuapp.com/conf_callback");
        conf.setCallbackMethod("GET");
        
        try {
            response.append(spk);
            response.append(conf);
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
        context.addServlet(new ServletHolder(new conferenceXml()),"/response/conference");
        context.addServlet(new ServletHolder(new confAction()),"/conf_action");
        context.addServlet(new ServletHolder(new confCallback()),"/conf_callback");
        server.start();
        server.join();
    }
}

// confAction.java
package plivoexample;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class confAction extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String conf_name = req.getParameter("ConferenceName");
        String conf_uuid = req.getParameter("ConferenceUUID");
        String conf_mem_id = req.getParameter("ConferenceMemberID");
        String record_url = req.getParameter("RecordUrl");
        String record_id = req.getParameter("RecordingID");
        System.out.println("Conference Name : " + conf_name + " Conference UUID : " + conf_uuid + " Conference Member ID : " + conf_mem_id + 
                            " Record URL : " + record_url + " Record ID : " + record_id);
        resp.getWriter().print("Conference Name : " + conf_name + " Conference UUID : " + conf_uuid + " Conference Member ID : " + conf_mem_id + 
                " Record URL : " + record_url + " Record ID : " + record_id);
    }
}

// confCallback.java
package plivoexample;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class confCallback extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String conf_action = req.getParameter("ConferenceAction");
        String conf_name = req.getParameter("ConferenceName");
        String conf_uuid = req.getParameter("ConferenceUUID");
        String conf_mem_id = req.getParameter("ConferenceMemberID");
        String call_uuid = req.getParameter("CallUUID");
        String record_url = req.getParameter("RecordUrl");
        String record_id = req.getParameter("RecordingID");
        System.out.println("Conference Action : " + conf_action + " Conference Name : " + conf_name + " Conference UUID : " + conf_uuid + " Conference Member ID : " + conf_mem_id + 
                            " Call UUID : " + call_uuid + " Record URL : " + record_url + " Record ID : " + record_id);
        resp.getWriter().print("Conference Action : " + conf_action + "Conference Name : " + conf_name + " Conference UUID : " + conf_uuid + " Conference Member ID : " + conf_mem_id + 
                " Call UUID : " + call_uuid + " Record URL : " + record_url + " Record ID : " + record_id);
    }
}

/*
Sample Output
<Response>
    <Speak>
        You will now be placed into a demo conference. This is brought to you by Plivo. To know more visit us at plivo.com
    </Speak>
    <Conference method="GET" record="true" action="https://dry-fortress-4047.herokuapp.com/conf_action" enterSound="beep:2" 
        callbackUrl="https://dry-fortress-4047.herokuapp.com/conf_callback" callbackMethod="GET">demo</Conference>
</Response>

Conference Name : demo Conference UUID : 7f4ec520-b81c-11e4-8ed1-fb0a4c731172 Conference Member ID : 173 
Record URL : http://s3.amazonaws.com/recordings_2013/7f0dc868-b81c-11e4-a664-0026b945b52b.mp3 Record ID : 7f0dc868-b81c-11e4-a664-0026b945b52b

Conference Action : exit Conference Name : demo Conference UUID : 7f4ec520-b81c-11e4-8ed1-fb0a4c731172 
Conference Member ID : 173 Call UUID : 70cd098a-b81c-11e4-8e90-fb0a4c731172 Record URL : null Record ID : null

*/