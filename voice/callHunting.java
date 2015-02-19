package plivoexample;

import java.io.IOException;

import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.xml.elements.Dial;
import com.plivo.helper.xml.elements.Number;
import com.plivo.helper.xml.elements.PlivoResponse;
import com.plivo.helper.xml.elements.Speak;
import com.plivo.helper.xml.elements.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class callHunting extends HttpServlet {
    private static final long serialVersionUID = 1L;    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        // Simultaneous dialing is useful when there are SIP users and numbers that you want to dial. 
        // The first call that connects will cancel all other tries.
                
        PlivoResponse response = new PlivoResponse();
        Speak speak = new Speak("Dialing");
        Dial dial = new Dial();
        User user= new User("sip:abcd1234@phone.plivo.com");
        Number num1 = new Number("1111111111");
        Number num2 = new Number("2222222222");
        
        try {
            dial.append(user);
            dial.append(num1);
            dial.append(num2);
            response.append(speak);
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
        context.addServlet(new ServletHolder(new callHunting()),"/call_hunting");
        server.start();
        server.join();
    }
}

/*
Sample Output
<Response>
    <Speak>Dialing</Speak>
    <Dial>
        <User>sip:abcd1234@phone.plivo.com</User>
        <Number>1111111111</Number>
        <Number>2222222222</Number>
    </Dial>
</Response>
*/