package com.plivo.test;

import static spark.Spark.post;

public class ReceiveMessage {
    public static void main(String[] args) {
        post("/receive_sms", (req,res) -> {
            try{
                String from_number = req.queryParams("From"); // Sender's phone number
                String to_number = req.queryParams("To"); // Receiver's phone number
                String text = req.queryParams("Text"); // The text which was received
                
                // Print the message detaisl
                System.out.println("From : " + from_number);
                System.out.println("To : " + to_number);
                System.out.println("Text : " + text);
                return "Message Received";
                
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
From : 1111111111
To : 2222222222
Text : Hi, from Plivo
*/
