package com.plivo.test;

import static spark.Spark.post;

import com.plivo.helper.xml.elements.Message;
import com.plivo.helper.xml.elements.PlivoResponse;

public class ReplyToMessage {
    public static void main(String[] args) {
        post("/reply_to_sms", (req,res) -> {
            try{
                String from_number = req.queryParams("From"); // Sender's phone number
                String to_number = req.queryParams("To"); // Receiver's phone number
                String text = req.queryParams("Text"); // The text message which was received
                
                // Print the Message
                System.out.printf("From : %s, To : %s, Text : %s ", from_number, to_number, text);

                PlivoResponse response = new PlivoResponse();

                // Generate the Message XML
                Message message = new Message("Thank you for your message");
                message.setSource(to_number);
                message.setDestination(from_number);
                response.append(message);

                // Print the XML
                System.out.println(response.toXML());

                // Return the XML
                res.type("text/xml");
                return response.toXML();
                
            }
            catch (Exception ex){
                ex.printStackTrace();
                return "Error";
            }
        });
    }
}

// Sample Output
/*
From : 1111111111, To : 2222222222, Text : Hi, from Plivo 
<Response>
    <Message dst="1111111111" src="2222222222">Thank you for your message</Message>
</Response>
*/