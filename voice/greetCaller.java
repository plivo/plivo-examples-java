package plivoexample;

import java.io.IOException;
import java.util.HashMap;

import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.xml.elements.PlivoResponse;
import com.plivo.helper.xml.elements.Speak;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class greetCaller extends HttpServlet {
    private static final long serialVersionUID = 1L;    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        HashMap<String, String> callers = new HashMap<String, String>();
        callers.put("1111111111", "ABCDEF");
        callers.put("2222222222", "VWXYZ");
        callers.put("3333333333", "QWERTY");
 
        String fromNumber = req.getParameter("From");
        String knownCaller = callers.get(fromNumber);
        String message;
        if (knownCaller == null) {
            // Use a generic message
            message = "Hello Stranger";
        } else {
            // Use the caller's name
            message = "Hello " + knownCaller;
        }
        
        PlivoResponse response = new PlivoResponse();
        Speak speak = new Speak(message);
        try {
            response.append(speak);         
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
        context.addServlet(new ServletHolder(new greetCaller()),"/greet_caller");
        server.start();
        server.join();
    }
}

/*
Sample Output
Sample Output
<Response>
    <Speak>Hello,ABCDEF</Speak>
</Response>
*/