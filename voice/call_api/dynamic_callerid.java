// Set te caller ID using Dial XML

import static spark.Spark.*;
import com.plivo.api.xml.*;
import com.plivo.api.xml.Number;

class DynamicCallerId {
    public static void main(String[] args) {
        get("/dynamic_caller_id", (request, response) -> {
            Response resp = new Response()
                    .children(
                            new Dial()
                                    .callerId("1111111111")
                                    .children(
                                            new Number("2222222222")
                                    )
                    );
            return resp.toXmlString();
        });
    }
}

/*
Sample Output
<?xml version="1.0" encoding="UTF-8"?>
<Response>
    <Dial callerId="1111111111">
        <Number>2222222222</Number>
    </Dial>
</Response>
*/


// Set the caller ID using Call API

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
            CallCreateResponse response = Call.creator("+14151234567", Collections.singletonList("+919900383513<918050897600"), "http://s3.amazonaws.com/static.plivo.com/answer.xml")
                    .answerMethod("GET")
                    .create();

            System.out.println(response);
        } catch (PlivoRestException | IOException e) {
            e.printStackTrace();
        }
    }

/*
Sample Output
{
  "api_id": "daf3ba61-3531-11eb-ac8d-0242ac110003",
  "message": "call fired",
  "request_uuid": "ead6ed7b-0e84-4aa5-b188-eba99e39e15f"
}
*/
