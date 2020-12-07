import java.io.IOException;
import com.plivo.api.Plivo;
import com.plivo.api.exceptions.PlivoRestException;
import com.plivo.api.models.conference.Conference;
import com.plivo.api.models.conference.ConferenceMemberActionResponse;

class DeafCreate {
    public static void main(String [] args) {
        Plivo.init("YOUR_AUTH_ID","YOUR_AUTH_TOKEN");
        try {
            ConferenceMemberActionResponse response = Conference.memberDeafer(
						"Sample_Room", // Conference room name
						"25854" // Member_id to be deafed
						)
                    .deaf();

            System.out.println(response);
        } catch (PlivoRestException | IOException e) {
            e.printStackTrace();
        }
    }
}

/*
Sample Output
{
  "api_id": "aa793117-35e1-11eb-a568-0242ac110008",
  "member_id": [
    "56839"
  ],
  "message": "deaf"
}
*/
