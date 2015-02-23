package plivoexample;

import java.io.IOException;

import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.xml.elements.PlivoResponse;
import com.plivo.helper.xml.elements.Record;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class recordXml extends HttpServlet {
    private static final long serialVersionUID = 1L;    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        // Generate a Record XML
        // The recorded file will be sent to the 'action' URL
                
        PlivoResponse response = new PlivoResponse();
        Record record = new Record();
        record.setAction("https://dry-fortress-4047.herokuapp.com/record_action");
        record.setMethod("GET");
        record.setCallbackUrl("https://dry-fortress-4047.herokuapp.com/recording_callback");
        record.setCallbackMethod("GET");
        
        try {
            response.append(record);
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
        context.addServlet(new ServletHolder(new recordingCallback()),"/recording_callback");
        context.addServlet(new ServletHolder(new recordAction()),"/record_action");
        context.addServlet(new ServletHolder(new recordXml()),"/record");
        server.start();
        server.join();
    }
}

/*
Sample Output
<Response>
<Record callbackUrl="https://dry-fortress-4047.herokuapp.com/recording_callback" callbackMethod="GET" 
    action="https://dry-fortress-4047.herokuapp.com/record_action" method="GET"/>
</Response>
*/

// recordAction.java
package plivoexample;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class recordAction extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Action URL Example

        String record_url = req.getParameter("RecordUrl");
        String record_duration = req.getParameter("RecordingDuration");
        String record_id = req.getParameter("RecordingID");
        System.out.println("Record URL : " + record_url + " Record Duration : " + record_duration + " Record ID : " + record_id);
        resp.getWriter().print("Record URL : " + record_url + " Record Duration : " + record_duration + " Record ID : " + record_id);
    }
}

/*
Sample Output
Record URL : http://s3.amazonaws.com/recordings_2013/55556666-7777-11e4-a4c8-782bcb5bb8af.mp3 Recording Duration : 27 Recording ID : daddbf04-9585-11e4-a4c8-782bcb5bb8af 
*/

//recordingCallback.java
package plivoexample;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class recordingCallback extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        // The Callback URL of record api will return the B Leg record details.

        String record_url = req.getParameter("RecordUrl");
        String record_duration = req.getParameter("RecordingDuration");
        String record_id = req.getParameter("RecordingID");
        System.out.println("Record URL : " + record_url + " Record Duration : " + record_duration + " Record ID : " + record_id);
        resp.getWriter().print("Record URL : " + record_url + " Record Duration : " + record_duration + " Record ID : " + record_id);
    }
}

/*
Sample Output
Record URL : http://s3.amazonaws.com/recordings_2013/11112222-4444-11e4-a4c8-782bcb5bb8af.mp3 Recording Duration : 22 Recording ID : 693e61fd-8150-4091-a0f8-561d4a434288 
*/