package plivoexample;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class handleDeliveryReport extends HttpServlet {
    private static final long serialVersionUID = 1L;    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String from_number = req.getParameter("From");
        String to_number = req.getParameter("To");
        String status = req.getParameter("Status");
        String uuid = req.getParameter("MessageUUID");
        System.out.println("From : " + from_number + " To : " + to_number + " Status : " + status + " Message UUID : " + uuid);
        resp.getWriter().print("From : " + from_number + " To : " + to_number + " Status : " + status + " Message UUID : " + uuid);
    }

    public static void main(String[] args) throws Exception {
        String port = System.getenv("PORT");
        if(port==null)
            port ="8080";
        Server server = new Server(Integer.valueOf(port));
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new handleDeliveryReport()),"/delivery_report");
        server.start();
        server.join();
    }
}


// Sample Output
/*
From : 1111111111 To : 2222222222 Status : queued Message UUID : 4a4faa1e-b421-11e4-816b-22000ae3827c
From : 1111111111 To : 2222222222 Status : sent Message UUID : 4a4faa1e-b421-11e4-816b-22000ae3827c
From : 1111111111 To : 2222222222 Status : delivered Message UUID : 4a4faa1e-b421-11e4-816b-22000ae3827c

*/