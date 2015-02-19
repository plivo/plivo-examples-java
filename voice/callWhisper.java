package plivoexample;

import java.io.IOException;

import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.xml.elements.Dial;
import com.plivo.helper.xml.elements.Number;
import com.plivo.helper.xml.elements.PlivoResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class callWhisper extends HttpServlet {
    private static final long serialVersionUID = 1L;    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        PlivoResponse response = new PlivoResponse();
        Dial dial = new Dial();
        dial.setConfirmKey("https://dry-fortress-4047.herokuapp.com/confirm_sound");
        dial.setConfirmSound("5");
        Number num1 = new Number("1111111111");
        Number num2 = new Number("2222222222");
        Number num3 = new Number("3333333333");
        
        try {
            dial.append(num1);
            dial.append(num2);
            dial.append(num3);
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
        context.addServlet(new ServletHolder(new callWhisper()),"/call_whisper");
        context.addServlet(new ServletHolder(new confirmSounr()),"/confirm_sound");
        server.start();
        server.join();
    }
}

/*
Sample Output
<Response>
    <Dial confirmSound="5" confirmKey="https://dry-fortress-4047.herokuapp.com/confirm_sound">
        <Number>1111111111</Number>
        <Number>2222222222</Number>
        <Number>3333333333</Number>
    </Dial>
</Response>
*/

// confirmSound.jav

package plivoexample;

import java.io.IOException;

import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.xml.elements.PlivoResponse;
import com.plivo.helper.xml.elements.Speak;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class confirmSound extends HttpServlet {
    private static final long serialVersionUID = 1L;    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        PlivoResponse response = new PlivoResponse();
        Speak spk = new Speak("Press 5 to answer the call");
        
        try {
            response.append(spk);
            System.out.println(response.toXML());
            resp.addHeader("Content-Type", "text/xml");
            resp.getWriter().print(response.toXML());;
        } catch (PlivoException e) {
            e.printStackTrace();
        }       
    }
}

/*
Sample Output
<Response>
    <Speak>Press 5 to answer the call</Speak>
</Response>
*/