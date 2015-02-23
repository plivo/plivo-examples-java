package plivoexample;

import java.io.IOException;

import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.xml.elements.Dial;
import com.plivo.helper.xml.elements.Number;
import com.plivo.helper.xml.elements.PlivoResponse;
import com.plivo.helper.xml.elements.Record;
import com.plivo.helper.xml.elements.Speak;
import com.plivo.helper.xml.elements.Wait;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class answerIncoming extends HttpServlet {
    private static final long serialVersionUID = 1L;    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        // A call is made to the plivo number. 
        // The answer_url returns and XML that starts recording the session and then dials to another number.
        // When the user pick up, the B Leg record starts and a music is played.

        // The action URL of the Record tag will return the Session recording details
        
        PlivoResponse response = new PlivoResponse();
        Record record = new Record();
        record.setAction("https://dry-fortress-4047.herokuapp.com/record_action");
        record.setMethod("GET");
        record.setRedirect(false);
        record.setRecordSession(true);
        Wait wait = new Wait();
        wait.setLength(5);
        Speak spk = new Speak("Connecting your call!");
        Dial dial = new Dial();
        dial.setCallbackUrl("https://dry-fortress-4047.herokuapp.com/dial_outbound");
        dial.setCallbackMethod("GET");
        Number num = new Number("919663489033");
        
        try {
            dial.append(num);
            response.append(record);
            response.append(wait);
            response.append(spk);
            response.append(dial);
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
        context.addServlet(new ServletHolder(new answerIncoming()),"/answer_incoming");
        context.addServlet(new ServletHolder(new dialOutbound()),"/dial_outbound");
        context.addServlet(new ServletHolder(new recordingCallback()),"/recording_callback");
        context.addServlet(new ServletHolder(new recordAction()),"/reord_action");
        server.start();
        server.join();
    }
}

/*
Sample Output

<Response>
    <Record action="https://dry-fortress-4047.herokuapp.com/record_action" recordSession="true" redirect="false" method="GET"/>
    <Wait length="5"/>
    <Speak>Connecting your call!</Speak>
    <Dial callbackUrl="https://dry-fortress-4047.herokuapp.com/dial_outbound" callbackMethod="GET">
        <Number>919663489033</Number>
    </Dial>
</Response>

*/