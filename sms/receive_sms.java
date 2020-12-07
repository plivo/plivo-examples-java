import static spark.Spark.*;

public class ReceiveSms {
    public static void main(String[] args) {
        get("/receive_sms", (request, response) -> {
            // Sender's phone number
            String from_number = request.queryParams("From");
            // Receiver's phone number - Plivo number
            String to_number = request.queryParams("To");
            // The text which was received
            String text = request.queryParams("Text");

            // Print the message
            System.out.println(from_number + " " + to_number + " " + text);

            return "Message Received";
        });
    }
}

// Sample Output
/*
From : 1111111111 To : 2222222222 Text : Hi, from Plivo
*/
