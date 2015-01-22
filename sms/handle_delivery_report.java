package com.plivo.test;

import static spark.Spark.post;

public class HandleDeliveryReport {
    public static void main(String[] args) {
        post("/deliver_report", (req,res) -> {
            try{
                String from_number = req.queryParams("From"); // Sender's phone number
                String to_number = req.queryParams("To"); // Receiver's phone number
                String status = req.queryParams("Status"); // The status of the message
                String uuid = req.queryParams("MessageUUID"); // UUID of the message 
                
                // Print the Message details
                System.out.println("From : " + from_number);
                System.out.println("To : " + to_number);
                System.out.println("Status : " + status);
                System.out.println("Message UUID : " + uuid);
                return "Delivery Reported";
                
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
Status : Sent
Message UUID : 1aead330-8ff9-11e4-9bd8-22000afa12b9
*/