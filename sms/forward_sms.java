package com.plivo.test;

import static spark.Spark.post;

import com.plivo.helper.xml.elements.Message;
import com.plivo.helper.xml.elements.PlivoResponse;

public class ForwardMessage {
    public static void main(String[] args) {
        post("/forward_sms", (req,res) -> {
            try{
                String from_number = req.queryParams("From"); // Sender's phone number
                String to_number = req.queryParams("To"); // Receiver's phone number
                String text = req.queryParams("Text"); // The text message which was received
                
                // Print the message
                System.out.printf("From : %s, To : %s, Text : %s ", from_number, to_number, text);
                
                String to_forward = "3333333333"; // The number to which the message has to be forwarded
                
                PlivoResponse response = new PlivoResponse();
                // Generate Message XML
                Message message = new Message("Hi, from Plivo");
                message.setSource(to_number);
                message.setDestination(to_forward);
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
    <Message dst="3333333333" src="2222222222">Hi, from Plivo</Message>
</Response>
*/