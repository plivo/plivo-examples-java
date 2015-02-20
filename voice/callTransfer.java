// callTransfer.java

package plivoexample;

import java.io.IOException;

import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.xml.elements.PlivoResponse;
import com.plivo.helper.xml.elements.Redirect;
import com.plivo.helper.xml.elements.Speak;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plivo.helper.api.client.*;
import com.plivo.helper.api.response.call.Call;
import com.plivo.helper.exception.PlivoException;

public class callTransfer extends HttpServlet {
    private static final long serialVersionUID = 1L;    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        PlivoResponse response = new PlivoResponse();
        Speak speak = new Speak("Please wait while your call is being transferred");
        Redirect rd = new Redirect("https://dry-fortress-4047.herokuapp.com/connect");
        
        try {
            response.append(speak);
            response.append(rd);
            
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
        context.addServlet(new ServletHolder(new callTransfer()),"/call_transfer");
        context.addServlet(new ServletHolder(new connect()),"/connect");
        server.start();
        server.join();
    }
}

/*
Sample Output
<Response>
    <Speak>Please wait while your call is being transferred</Speak>
    <Redirect>https://dry-fortress-4047.herokuapp.com/connect</Redirect>
</Response>
*/

// connect.java

package plivoexample;

import java.io.IOException;

import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.xml.elements.Dial;
import com.plivo.helper.xml.elements.Number;
import com.plivo.helper.xml.elements.PlivoResponse;
import com.plivo.helper.xml.elements.Speak;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class connect extends HttpServlet {
    private static final long serialVersionUID = 1L;    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        PlivoResponse response = new PlivoResponse();
        Speak speak = new Speak("Connecting your call..");
        Dial dial = new Dial();
        dial.setAction("https://dry-fortress-4047.herokuapp.com/dial_status");
        dial.setMethod("GET");
        dial.setRedirect(true);
        Number num = new Number("1111111111");
        
        try {
            response.append(speak);
            dial.append(num);
            response.append(dial);
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
    <Speak>Connecting your call..</Speak>
    <Dial action="https://dry-fortress-4047.herokuapp.com/dial_sratus" redirect="true" method="GET">
        <Number>1111111111</Number>
    </Dial>
</Response>
*/