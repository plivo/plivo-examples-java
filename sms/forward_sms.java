import static spark.Spark.*;
import com.plivo.api.xml.Message;
import com.plivo.api.xml.Response;

public class ForwardSMS {
    public static void main(String[] args) {
        get("/forward_sms", (request, response) -> {
            // Sender's phone number
            String from_number = request.queryParams("From");
            // Receiver's phone number - Plivo number
            String to_number = request.queryParams("To");
            // The text which was received
            String text = request.queryParams("Text");
            // Returns the response in application/xml type
            response.type("application/xml");
            // Print the message
            System.out.println(from_number + " " + to_number + " " + text);

            // The phone number to which the SMS has to be forwarded
            String to_forward = "+3333333333";
            // send the details to generate an XML
            Response res = new Response().children(
                    // from_number,to_forward,text is being passed
                    new Message(from_number, to_forward, text).callbackMethod("POST")
                            .callbackUrl("http://foo.com/sms status/").type("sms"));
            // Returns the XML
            return res.toXmlString();
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
