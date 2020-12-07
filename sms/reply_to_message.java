import static spark.Spark.*;
import com.plivo.api.xml.Message;
import com.plivo.api.xml.Response;

public class Reply {
    public static void main(String[] args) {
        get("/reply_sms", (request, response) -> {
            // Sender's phone number
            String from_number = request.queryParams("From");
            // Receiver's phone number - Plivo number
            String to_number = request.queryParams("To");
            // The text which was received
            String text = request.queryParams("Text");
            response.type("application/xml");
            // Print the message
            System.out.println(from_number + " " + to_number + " " + text);

            // send the details to generate an XML
            Response res = new Response().children(
                    // from_number is passed as destination,to_number is passed as source and text
                    // is being passed
                    new Message(to_number, from_number, "Thank you, we have received your request")
                            .callbackMethod("POST").callbackUrl("http://foo.com/sms status/").type("sms"));
            // Returns the XML
            return res.toXmlString();
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
