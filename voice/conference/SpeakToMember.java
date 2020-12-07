import java.io.IOException;
import com.plivo.api.Plivo;
import com.plivo.api.exceptions.PlivoRestException;
import com.plivo.api.models.conference.Conference;
import com.plivo.api.models.conference.ConferenceMemberActionResponse;

class SpeakCreate {
    public static void main(String [] args) {
        Plivo.init("YOUR_AUTH_ID","YOUR_AUTH_TOKEN");
        try {
            ConferenceMemberActionResponse response = Conference.memberSpeaker(
						"Sample_Room", // Conference room name
						"56842", // Member_id of the conference
						"Hello! Member. Welcome to the conference Sample_Room" // Text message to be played to the member
						)
                    .speak();

            System.out.println(response);
        } catch (PlivoRestException | IOException e) {
            e.printStackTrace();
        }
    }
}
/*
Sample Output
{
  "api_id": "358230c0-35e4-11eb-a568-0242ac110008",
  "member_id": [
    "56842"
  ],
  "message": "speak queued into conference"
}
*/
