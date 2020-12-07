import java.io.IOException;
import com.plivo.api.Plivo;
import com.plivo.api.exceptions.PlivoRestException;
import com.plivo.api.models.conference.Conference;
import com.plivo.api.models.conference.ConferenceMemberActionResponse;

class PlayCreate {
    public static void main(String [] args) {
        Plivo.init("YOUR_AUTH_ID","YOUR_AUTH_TOKEN");
        try {
            ConferenceMemberActionResponse response = Conference.memberPlayer(
						"Sample_Room", // Conference room name
						"56842", // Conference member_id for which the audio will be played.
						"https://s3.amazonaws.com/plivocloud/Trumpet.mp3" // URL of the audio
						)
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
  "api_id": "d1b4073d-35e2-11eb-a8d3-0242ac110005",
  "member_id": [
    "56842"
  ],
  "message": "play queued into conference"
}
*/
