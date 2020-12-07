import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import static spark.Spark.*;

//Incoming SMS, forward to an email

public class SendEmail {
    public static void main(String[] args) {
        get("/send_to_email", (request, response) -> {
            // Sender's phone number
            String from_number = request.queryParams("From");
            // Receiver's phone number - Plivo number
            String to_number = request.queryParams("To");
            // The text which was received
            String text = request.queryParams("Text");

            // Print the message
            System.out.println(from_number + " " + to_number + " " + text);

            //authentication info
            final String username = "yourUsername@email.com";
            final String password = "password";
            String fromEmail = "fromemail@yahoo.com";
            String toEmail = "toEmail@example.com";

            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");

            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username,password);
                }
            });
            //Start our mail message
            MimeMessage msg = new MimeMessage(session);
            try {
                msg.setFrom(new InternetAddress(fromEmail));
                msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
                msg.setSubject("Subject Line");

                Multipart emailContent = new MimeMultipart();

                //Text body part
                MimeBodyPart textBodyPart = new MimeBodyPart();
                // Passing the receved message content
                textBodyPart.setText(text);

                Transport.send(msg);
                System.out.println("Sent message successfully....");
            } catch (MessagingException e) {
                e.printStackTrace();
            }

            return "Message sent to Email";
        });
    }
}
/*
Sample Output
From : 1111111111 To : 2222222222 Text : Hi, from Plivo

Sent message successfully....
*/
