package plivoexample;

import java.io.IOException;

import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.xml.elements.Dtmf;
import com.plivo.helper.xml.elements.PlivoResponse;
import com.plivo.helper.xml.elements.Speak;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class dtmfXml extends HttpServlet {
    private static final long serialVersionUID = 1L;    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        PlivoResponse response = new PlivoResponse();
        Dtmf dt = new Dtmf("12345");
        Speak speak = new Speak("Connecting your call..");
        
        try {
            response.append(speak);
            response.append(dt);
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
        context.addServlet(new ServletHolder(new dtmfXml()),"/dtmf");
        server.start();
        server.join();
    }
}

/*
Sample Output
<Response>
    <Speak>Connecting your call..</Speak>
    <DTMF>12345</DTMF>
</Response>
*/