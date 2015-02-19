// phoneIvr.java
package plivoexample;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.xml.elements.GetDigits;
import com.plivo.helper.xml.elements.Play;
import com.plivo.helper.xml.elements.PlivoResponse;
import com.plivo.helper.xml.elements.Speak;

public class phoneIvr extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    // This file will be played when a caller presses 2.
    String PLIVO_SONG = "https://s3.amazonaws.com/plivocloud/music.mp3";
    
    // This is the message that Plivo reads when the caller dials in
    String IVR_MESSAGE1 = "Welcome to the Plivo IVR Demo App. Press 1 to listen to a "
            + "pre recorded text in different languages.Press 2 to listen to a song.";
    String IVR_MESSAGE2 = "Press 1 for English. Press 2 for French. Press 3 for Russian";
    
    // This is the message that Plivo reads when the caller does nothing at all
    String NO_INPUT_MESSAGE = "Sorry, I didn't catch that. Please hangup and try again later.";
    
    // This is the message that Plivo reads when the caller inputs a wrong number.
    String WRONG_INPUT_MESSAGE = "Sorry, it's wrong input.";
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        PlivoResponse response = new PlivoResponse();
        GetDigits gd = new GetDigits();
        gd.setAction("https://dry-fortress-4047.herokuapp.com/response/ivr");
        gd.setMethod("POST");
        gd.setNumDigits(1);
        gd.setTimeout(7);
        gd.setRetries(1);
        Speak spk = new Speak(IVR_MESSAGE1);
        Speak speak = new Speak(NO_INPUT_MESSAGE);
        try {
            gd.append(spk);
            response.append(gd);
            response.append(speak);
            System.out.println(response.toXML());
            resp.addHeader("Content-Type", "text/xml");
            resp.getWriter().print(response.toXML());;
        } catch (PlivoException e) {
            e.printStackTrace();
        }       
    }
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        String digit = req.getParameter("Digits");
        PlivoResponse response = new PlivoResponse();
        
        if (digit == "1")
        {
            // Read out a text
            GetDigits gd = new GetDigits();
            gd.setAction("https://dry-fortress-4047.herokuapp.com/response/tree");
            gd.setMethod("POST");
            gd.setNumDigits(1);
            gd.setTimeout(7);
            gd.setRetries(1);
            Speak spk = new Speak(IVR_MESSAGE2);
            try {
                gd.append(spk);
                response.append(gd);
            } catch (PlivoException e) {
                e.printStackTrace();
            }
        }
        else if (digit == "2") 
        {
            // Listen to a song
            Play play = new Play(PLIVO_SONG);
            try {
                response.append(play);
            } catch (PlivoException e) {
                e.printStackTrace();
            }           
        }
        else
        {
            // Wrong input
            Speak speak = new Speak(WRONG_INPUT_MESSAGE);
            try {
                response.append(speak);
            } catch (PlivoException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println(response.toXML());
        resp.addHeader("Content-Type", "text/xml");
        resp.getWriter().print(response.toXML());           
    }

    public static void main(String[] args) throws Exception {
        String port = System.getenv("PORT");
        if(port==null)
            port ="8000";
        Server server = new Server(Integer.valueOf(port));
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new phoneIvr()),"/response/ivr");
        context.addServlet(new ServletHolder(new ivrTree()),"/response/tree");
        server.start();
        server.join();
    }
}

/*
Sample Output
<Response>
    <GetDigits numDigits="1" action="https://dry-fortress-4047.herokuapp.com/response/ivr" retries="1" method="POST" timeout="7">
        <Speak>
            Welcome to the Plivo IVR Demo App. Press 1 to listen to a pre recorded text in different languages.Press 2 to listen to a song.
        </Speak>
    </GetDigits>
    <Speak>
        Sorry, I didn't catch that. Please hangup and try again later.
    </Speak>
</Response>

If 1 is pressed, another menu is read out. Following is the generated Speak XML.
<Response>
    <GetDigits retries="1" method="POST" numDigits="1" action="https://dry-fortress-4047.herokuapp.com/response/tree" timeout="7">
        <Speak>Press 1 for English. Press 2 for French. Press 3 for Russian</Speak>
    </GetDigits>
</Response>

If 1 is pressed, the English text is read out. Following is the generated Speak XML.
<Response>
    <Speak language="en-GB">This message is being read out in English</Speak>
</Response>

If 2 is pressed, the French text is read out. Following is the generated Speak XML.
<Response>
    <Speak language="fr-FR">Ce message est lu en français</Speak>
</Response>

If 3 is pressed, the Russian text is read out. Following is the generated Speak XML.
<Response>
    <Speak language="ru-RU">Это сообщение было прочитано в России</Speak>
</Response>


If 2 is pressed, a music is played. Following is the generated Play XML.
<Response>
   <Play>https://s3.amazonaws.com/plivocloud/music.mp3</Play>
</Response>
*/