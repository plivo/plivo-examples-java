package plivoexample;

import java.io.IOException;

import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.xml.elements.Message;
import com.plivo.helper.xml.elements.PlivoResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class replyToSms extends HttpServlet {
    private static final long serialVersionUID = 1L;    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String from_number = req.getParameter("From");
        String to_number = req.getParameter("To");
        String text = req.getParameter("Text");
        System.out.println("From : " + from_number + " To : " + to_number + " Text : " + text);
        
        PlivoResponse response = new PlivoResponse();
        Message msg = new Message("Thank you for your message");
        msg.setSource(to_number);
        msg.setDestination(from_number);
        try {
            response.append(msg);
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
            port ="8080";
        Server server = new Server(Integer.valueOf(port));
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new replyToSms()),"/reply_to_sms");
        server.start();
        server.join();
    }
}


// Sample Output
/*
From : 1111111111, To : 2222222222, Text : Hi, from Plivo 
<Response>
    <Message dst="1111111111" src="2222222222">Thank you for your message</Message>
</Response>
*/