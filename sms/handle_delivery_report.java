import static spark.Spark.*;

public class Delivery {
    public static void main(String[] args) {
        get("/delivery_sms", (request, response) -> {
            // Sender's phone number
            String from_number = request.queryParams("From");
            // Receiver's phone number - Plivo number
            String to_number = request.queryParams("To");
            // Message UUID
            String uuid = request.queryParams("MessageUUID");
            // The status of the message sent
            String message_status = request.queryParams("Status");

            response.type("application/xml");
            // Print the message
            System.out.println(from_number + " " + to_number + " " + message_status+ " "+ uuid);

            return "Message Received";
        });
    }
}

// Sample Output
/*
From : 1111111111 To : 2222222222 Status : queued Message UUID : 4a4faa1e-b421-11e4-816b-22000ae3827c
From : 1111111111 To : 2222222222 Status : sent Message UUID : 4a4faa1e-b421-11e4-816b-22000ae3827c
From : 1111111111 To : 2222222222 Status : delivered Message UUID : 4a4faa1e-b421-11e4-816b-22000ae3827c
*/
