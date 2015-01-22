package com.plivo.test;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import static spark.Spark.post;

public class App {
    public static void main(String[] args) {
        get("/forward_sms", (req,res) -> {
            try{
                String from_number = req.queryParams("From");
                String to_number = req.queryParams("To");
                String text = req.queryParams("Text");
                
                System.out.printf("From : %s, To : %s, Text : %s ", from_number, to_number, text);
                
                String result = App.sendEmail(text);
                
                return result;
                
            }
            catch (Exception ex){
                ex.printStackTrace();
                return "Error";
            }
        });
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
           message.setText("Hello, this is sample for to check send "
              + "email using JavaMailAPI ");

           // Send message
           Transport.send(message);

           System.out.println("Sent message successfully....");
           
           return "Sent message successfully!";

        } catch (MessagingException e) {
              throw new RuntimeException(e);
        }
    }
}

