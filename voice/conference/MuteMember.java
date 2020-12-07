import java.io.IOException;
import com.plivo.api.Plivo;
import com.plivo.api.exceptions.PlivoRestException;
import com.plivo.api.models.conference.Conference;
import com.plivo.api.models.conference.ConferenceMemberActionResponse;

class MuteCreate {
    public static void main(String [] args) {
        Plivo.init("YOUR_AUTH_ID","YOUR_AUTH_TOKEN");
        try {
            ConferenceMemberActionResponse response = Conference.memberMuter(
						"Sample_Room", // Conference room name
						"25854" // Member_id to be muted
						)
                    .mute();

            System.out.println(response);
        } catch (PlivoRestException | IOException e) {
            e.printStackTrace();
        }
    }
}


/*
Sample Output
{
  "api_id": "58c53c45-35e2-11eb-a568-0242ac110008",
  "member_id": [
    "56842"
  ],
  "message": "muted"
}
*/
