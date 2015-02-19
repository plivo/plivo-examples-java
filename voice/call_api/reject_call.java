package plivoexample;

import java.io.IOException;

import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.xml.elements.Hangup;
import com.plivo.helper.xml.elements.PlivoResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class hangup extends HttpServlet {
    private static final long serialVersionUID = 1L;    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        PlivoResponse response = new PlivoResponse();
        Hangup hang = new Hangup();
        hang.setReason("busy");
        hang.setSchedule(60);
        
        try {
            response.append(hang);
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
        context.addServlet(new ServletHolder(new hangup()),"/hangup");
        server.start();
        server.join();
    }
}

/*
Sample Output
<Response>
    <Hangup schedule="60" reason="busy"/>
</Response>
*/
