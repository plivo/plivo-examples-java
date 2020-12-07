// recordUsingApi.java
import com.plivo.api.xml.GetDigits;
import com.plivo.api.xml.Response;
import com.plivo.api.xml.Speak;
import static spark.Spark.*;

public class Get_digit {
    public static void main(String[] args) {
        get("/get_digit", (request, response) -> {
            Response resp = new Response()
                    .children(
                            new GetDigits()
                                    .action("http://www.foo.com/record_action/")
                                    .numDigits(1)
                                    .method("GET")
                                    .redirect(false)
                                    .children(new Speak("Press 1 to record this call"))
                    );
            return resp.toXmlString();
        });
    }
}

/*
Sample Output
<Response>
   <GetDigits action="http://www.foo.com/record_action/" method="GET" numDigits="1" redirect="false">
      <Speak>Press 1 to record this call</Speak>
   </GetDigits>
</Response>
*/

//recordApiAction.java
import com.plivo.api.Plivo;
import com.plivo.api.exceptions.PlivoRestException;
import com.plivo.api.models.call.Call;
import com.plivo.api.models.call.actions.CallRecordCreateResponse;
import java.io.IOException;
import static spark.Spark.*;

public class Record {
    public static void main(String[] args) {
        get("/record_action", (request, response) ->
        {
            String digit = request.queryParams("Digits");
            String call_uuid = request.queryParams("CallUUID");
            System.out.println("Digit : " + digit + " Call UUID : " + call_uuid);
            if(digit.equals("1")){
                Plivo.init("YOUR_AUTH_ID", "YOUR_AUTH_TOKEN");
                try {
                    CallRecordCreateResponse resp = Call.recorder("eba53b9e-8fbd-45c1-9444-696d2172fbc8")
                            .record();
                    System.out.println(response);
                } catch (PlivoRestException | IOException e) {
                    e.printStackTrace();
                }}
            else
                {
                System.out.println("Wrong Input");
                }
            return "Record action completed";
        });
    }
}

/*
Sample Output

Digit : 1 Call UUID : fa494fea-c954-11e4-9a6a-f58c44fe61dd
{
  "url": "http://s3.amazonaws.com/recordings_2013/48dfaf60-3b2a-11e3.mp3",
  "message": "call recording started",
  "recording_id": "48dfaf60-3b2a-11e3",
  "api_id": "c7b69074-58be-11e1-86da-adf28403fe48"
}
*/
