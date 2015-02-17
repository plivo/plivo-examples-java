package plivoexample;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.xml.elements.PlivoResponse;

public class smsToEmail extends HttpServlet{
    private static final long serialVersionUID = 1L;    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String from_number = req.getParameter("From");
        String to_number = req.getParameter("To");
        String text = req.getParameter("Text");
        System.out.println("From : " + from_number + " To : " + to_number + " Text : " + text);
        
        try {
          String result = smsToEmail.sendEmail(text);
          resp.getWriter().print(result);
        } catch (Exception ex) {
          ex.printStackTrace();
          resp.getWriter().print("Error");
        }      
    }
    
    public static String sendEmail(String text){
        
        // Recipient's email ID
        String to = "To mail address";

        // Sender's email ID
        String from = "From mail address";
        final String username = "Your mail address";
        final String password = "Your Password";

        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Get the Session object
        Session session = Session.getInstance(props,
        new javax.mail.Authenticator() {
           protected PasswordAuthentication getPasswordAuthentication() {
              return new PasswordAuthentication(username, password);
           }
        });

        try {
           // Create a default MimeMessage object.
           Message message = new MimeMessage(session);

           // Set From: header field of the header.
           message.setFrom(new InternetAddress(from));

           // Set To: header field of the header.
           message.setRecipients(Message.RecipientType.TO,
           InternetAddress.parse(to));

           // Set Subject: header field
           message.setSubject("Testing Subject");

           // Set the actual message
           message.setText(text);

           // Send message
           Transport.send(message);

           System.out.println("Sent message successfully....");
           
           return "Sent message successfully!";

        } catch (MessagingException e) {
              throw new RuntimeException(e);
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
        context.addServlet(new ServletHolder(new smsToEmail()),"/receive_sms");
        server.start();
        server.join();
    }
}
/*
Sample Output
From : 1111111111 To : 2222222222 Text : Hi, from Plivo

Sent message successfully....
*/
