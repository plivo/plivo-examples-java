// voicemail.java
package plivoexample;

import java.io.IOException;

import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.xml.elements.PlivoResponse;
import com.plivo.helper.xml.elements.Record;
import com.plivo.helper.xml.elements.Speak;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class voicemail extends HttpServlet {
    private static final long serialVersionUID = 1L;    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        PlivoResponse response = new PlivoResponse();
        Record record = new Record();
        record.setAction("https://dry-fortress-4047.herokuapp.com/record_action");
        record.setMethod("GET");
        record.setMaxLength(30);
        record.setTranscriptionType("auto");
        record.setTranscriptionUrl("https://dry-fortress-4047.herokuapp.com/transcription");
        record.setTranscriptionMethod("GET");
        
        Speak speak = new Speak("Leave your message after the tone");
        
        try {
            response.append(speak);
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
        context.addServlet(new ServletHolder(new voicemail()),"/voicemail");
        context.addServlet(new ServletHolder(new transcription()),"/transcription");
        context.addServlet(new ServletHolder(new recordAction()),"/record_action");
        server.start();
        server.join();
    }
}

/*
Sample Output
<Response>
    <Speak>Leave your message after the tone</Speak>
    <Record maxLength="30" transcriptionUrl="https://dry-fortress-4047.herokuapp.com/transcription" 
        action="https://dry-fortress-4047.herokuapp.com/record_action" transcriptionMethod="GET" method="GET" transcriptionType="auto"/>
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
        
        String record_url = req.getParameter("RecordUrl");
        String record_duration = req.getParameter("RecordingDuration");
        String record_id = req.getParameter("RecordingID");
        System.out.println("Record URL : " + record_url + " Record Duration : " + record_duration + " Record ID : " + record_id);
        resp.getWriter().print("Record URL : " + record_url + " Record Duration : " + record_duration + " Record ID : " + record_id);
    }
}

/*
Sample Output

Record URL : http://s3.amazonaws.com/recordings_2013/59581736-bb41-11e4-929e-782bcb0446c4.mp3 Record Duration : 10 Record ID : 59580836-bb41-11e4-929e-782bcb0446c4
*/

// transcription.java
package plivoexample;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class transcription extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        String transcription = req.getParameter("transcription");
        System.out.println("Transcription is : " + transcription);
        resp.getWriter().print("Transcription is : " + transcription);
    }
}

/*
Sample Output

Transcription is : Hello
*/