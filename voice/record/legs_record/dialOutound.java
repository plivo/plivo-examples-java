import com.plivo.api.exceptions.PlivoRestException;
import com.plivo.api.models.call.Call;
import com.plivo.api.models.call.actions.CallPlayCreateResponse;
import com.plivo.api.models.call.actions.CallRecordCreateResponse;
import java.io.IOException;
import java.util.Collections;
import static spark.Spark.*;

public class ReceiveSms {
    public static void main(String[] args) {
        get("/beep_detection", (request, response) -> {

            // The Callback URL of Dial will make a request to the Record API which will record only the B Leg
            // Play API is invoked which will play a music only on the B Leg.

            String event = request.queryParams("Event");
            String call_uuid = request.queryParams("DialBLegUUID");

            if (event.equals("DialAnswer"))
            {
                try {
                    CallRecordCreateResponse resp = Call.recorder(call_uuid) // ID of the call
                            .callbackMethod("GET")
                            .callbackUrl("http://foo.com/callback/")
                            .record();

                    System.out.println(resp);
                } catch (PlivoRestException | IOException e) {
                    e.printStackTrace();
                }

                try {
                    CallPlayCreateResponse resp = Call.player(call_uuid, // ID of the call
                    Collections.singletonList("https://s3.amazonaws.com/plivocloud/Trumpet.mp3"))
                            .play();
                    System.out.println(resp);
                } catch (PlivoRestException | IOException e) {
                    e.printStackTrace();
                }
            }
            else
                {
                    System.out.println("Invalid");
                }
            return "Excecution completed!";
        });
    }
}

/*
Sample Output

Output of the Record API request
{
  "url": "http://s3.amazonaws.com/recordings_2013/48dfaf60-3b2a-11e3.mp3",
  "message": "call recording started",
  "recording_id": "48dfaf60-3b2a-11e3",
  "api_id": "c7b69074-58be-11e1-86da-adf28403fe48"
}

Output of the Play API
{
  "message": "play started",
  "api_id": "07abfd94-58c0-11e1-86da-adf28403fe48"
}
*/
