package plivoexample;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class receiveSms extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String from_number = req.getParameter("From");
        String to_number = req.getParameter("To");
        String text = req.getParameter("Text");
        System.out.println("From : " + from_number + " To : " + to_number + " Text : " + text);
        resp.getWriter().print("From : " + from_number + "To : " + to_number + "Text : " + text);
    }

    public static void main(String[] args) throws Exception {
        String port = System.getenv("PORT");
        if(port==null)
            port ="8080";
        Server server = new Server(Integer.valueOf(port));
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new Main()),"/receive_sms");
        server.start();
        server.join();
    }
}

// Sample Output
/*
From : 1111111111
To : 2222222222
Text : Hi, from Plivo
*/
