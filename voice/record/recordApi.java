import java.io.IOException;
import com.plivo.api.Plivo;
import com.plivo.api.exceptions.PlivoRestException;
import com.plivo.api.models.call.Call;
import com.plivo.api.models.call.actions.CallRecordCreateResponse;
import com.plivo.api.models.conference.Conference;
import com.plivo.api.models.conference.ConferenceRecordCreateResponse;
import static spark.Spark.*;

public class RecordApi {
    public static void main(String[] args) {
        get("/record_api", (request, response) -> {
            Plivo.init("YOUR_AUTH_ID","YOUR_AUTH_TOKEN");
            // Start recording a call.
            try {
                CallRecordCreateResponse resp = Call.recorder("eba53b9e-8fbd-45c1-9444-696d2172fbc8")
                        .record();

                System.out.println(resp);
            } catch (PlivoRestException | IOException e) {
                e.printStackTrace();
            }
            // Stop recording a call.
            try {
                Call.recordStopper("eba53b9e-8fbd-45c1-9444-696d2172fbc8")
                        .recordStop();

                System.out.println("Deleted successfully.");
            } catch (PlivoRestException | IOException e) {
                e.printStackTrace();
            }
            // Start recording a conference.
            try {
                ConferenceRecordCreateResponse resp = Conference.recorder("demo")
                        .record();

                System.out.println(resp);
            } catch (PlivoRestException | IOException e) {
                e.printStackTrace();
            }
            // Stop recording a conference.
            try {
                Conference.recordStopper("demo")
                        .stop();

                System.out.println("Deleted successfully.");
            } catch (PlivoRestException | IOException e) {
                e.printStackTrace();
            }
            return "Record API completed";
        });
    }
}


/*
Sample Output

Start recording a call.
{
  "url": "http://s3.amazonaws.com/recordings_2013/48dfaf60-3b2a-11e3.mp3",
  "message": "call recording started",
  "recording_id": "48dfaf60-3b2a-11e3",
  "api_id": "c7b69074-58be-11e1-86da-adf28403fe48"
}

Stop recordnig a call
HTTP status code: 202
No message

Start recording a conference.
{
	"api_id": "2867b6e2-58c3-11e1-86da-adf28403fe48",
	"message": "conference recording started",
	"recording_id": "93bc7c6a-3b2b-11e3",
	"url": "http://s3.amazonaws.com/recordings_2013/93bc7c6a-3b2b-11e3.mp3",
}

Stop recording a conference
HTTP status code: 202
No message
*/
