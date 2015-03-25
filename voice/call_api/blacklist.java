package plivoexample;

import java.io.IOException;
import java.util.Arrays;

import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.xml.elements.Hangup;
import com.plivo.helper.xml.elements.PlivoResponse;
import com.plivo.helper.xml.elements.Speak;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class blackList extends HttpServlet {
    private static final long serialVersionUID = 1L;    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String[] blacklist = { "1111111111", "2222222222" };
        String fromNumber = req.getParameter("From");
        PlivoResponse response = new PlivoResponse();
        if (Arrays.asList(blacklist).contains(fromNumber)) {
            Hangup hang = new Hangup();
            hang.setReason("rejected");
            try{
                response.append(hang);
            } catch (PlivoException e) {
                e.printStackTrace();
            }
        } else {
            Speak speak = new Speak("Hello, from Plivo!");
            try{
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
        context.addServlet(new ServletHolder(new blackList()),"/reject_caller");
        server.start();
        server.join();
    }
}

/*
Sample Output
Sample output when From number is in blacklist
<Response>
    <Hangup reason="rejected"/>
</Response>

Sample Output when From number is not in blacklist
<Response>
    <Speak>Hello from Plivo</Speak>
</Response>
*/