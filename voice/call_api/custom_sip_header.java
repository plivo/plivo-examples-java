import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import com.plivo.api.Plivo;
import com.plivo.api.exceptions.PlivoRestException;
import com.plivo.api.models.call.Call;
import com.plivo.api.models.call.CallCreateResponse;

class CallCreate {
    public static void main(String [] args) {
        Plivo.init("YOUR_AUTH_ID","YOUR_AUTH_TOKEN");
        try {
          Map<String, String> headers = new HashMap<>();
            headers.put("key", "value");
            CallCreateResponse response = Call.creator(
            "+11111111111", // The phone number to be used as the caller id
            Collections.singletonList("+222222222"), // The phone numers to which the all has to be placed. The numbers are separated by "<" delimiter.
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
  ]
}



The SIP header can be seen as a query parameter in the answer_url as "X-PH-key=value"
path="/speak?Direction=outbound&From=1111111111&ALegUUID=b4b71302-b6c8-11e4-95c7-fb0a4c731172&BillRate=0.00300&
To=sip%3Atest150108095716%40phone.plivo.com&X-PH-key=value&CallUUID=b4b71302-b6c8-11e4-95c7-fb0a4c731172&
ALegRequestUUID=8501b9f8-7115-449c-8c41-4280e663bde1&RequestUUID=8501b9f8-7115-449c-8c41-4280e663bde1&
SIP-H-To=%3Csip%3Atest150108095716%40phone.plivo.com%3E%3Btag%3DqdF2R1oMgcOZioAZ6fuyWpVxPtMENeXC&CallStatus=in-progress&Event=StartApp"
*/
