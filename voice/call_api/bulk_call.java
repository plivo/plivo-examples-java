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
            "+11111111111", // The phone number to be used as the caller id
            Collections.singletonList("+222222222<+333333333"), // The phone numers to which the all has to be placed. The numbers are separated by "<" delimiter.
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
  "api_id": "866f82ac-352e-11eb-8997-0242ac110007",
  "message": "calls fired",
  "request_uuid": [
    "42021c3b-3104-4214-90fc-9fe7260f75a5",
    "2577d827-9a92-4e83-98cd-603c7b1b36e9"
  ]
}
*/
