import java.io.IOException;
import java.util.Collections;
import com.plivo.api.Plivo;
import com.plivo.api.exceptions.PlivoRestException;
import com.plivo.api.models.call.Call;
import com.plivo.api.models.call.CallCreateResponse;

class CallCreate {
    public static void main(String [] args) {
        Plivo.init("YOUR_AUTH_ID","YOUR_AUTH_TOKEN");
        try {
            CallCreateResponse response = Call.creator(
                    "+111111111", // The phone numer to which the all has to be placed
                    Collections.singletonList("+2222222222"), // The phone number to be used as the caller id
                    "http://s3.amazonaws.com/static.plivo.com/answer.xml") // The URL invoked by Plivo when the outbound call is answered
                    .answerMethod("GET") // method to invoke the answer_url
                    .create();

            System.out.println(response);
        } catch (PlivoRestException | IOException e) {
            e.printStackTrace();
        }
    }
}

/*
Sample Output
{
  "api_id": "d2ee75a6-355e-11eb-8b03-0242ac110005",
  "message": "call fired",
  "request_uuid": "d6529a6e-f3c1-4416-bcc2-01ceeffd4966"
}
*/
