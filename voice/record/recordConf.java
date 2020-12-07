import static spark.Spark.*;
import com.plivo.api.xml.*;

class RecordConference {
    public static void main(String[] args) {
        get("/record_conference", (request, response) -> {
            Response resp = new Response()
                    .children(
                            new Speak("You will now be placed into a demo conference.This is brought\n to you by Plivo.To know more visit us at plivo.com"),
                            new Conference("demo")
                                    .endConferenceOnExit(true)
                                    .startConferenceOnEnter(false)
                                    .enterSound("beep:2")
                                    .waitSound("http://www.foo.com/waitmusic/")
                    );
            return resp.toXmlString();
        });
    }
}

/*
Sample Output
<Response>
   <Speak>You will now be placed into a demo conference.This is brought
 to you by Plivo.To know more visit us at plivo.com</Speak>
   <Conference enterSound="beep:2" startConferenceOnEnter="false" endConferenceOnExit="true" waitSound="http://www.foo.com/waitmusic/">demo</Conference>
</Response>
*/

//recordConfCallback
import java.io.IOException;
import com.plivo.api.Plivo;
import com.plivo.api.exceptions.PlivoRestException;
import com.plivo.api.models.conference.Conference;
import com.plivo.api.models.conference.ConferenceRecordCreateResponse;
import static spark.Spark.*;

public class RecordConferenceApi {
    public static void main(String[] args) {
        get("/record_conference_api", (request, response) -> {
            Plivo.init("YOUR_AUTH_ID","YOUR_AUTH_TOKEN");
            try {
                ConferenceRecordCreateResponse resp = Conference.recorder("demo")
                        .record();

                System.out.println(resp);
            } catch (PlivoRestException | IOException e) {
                e.printStackTrace();
            }
            return "Record completed!";
        });
    }
}


/*
Sample Output

{
	"api_id": "2867b6e2-58c3-11e1-86da-adf28403fe48",
	"message": "conference recording started",
	"recording_id": "93bc7c6a-3b2b-11e3",
	"url": "http://s3.amazonaws.com/recordings_2013/93bc7c6a-3b2b-11e3.mp3",
}
*/
