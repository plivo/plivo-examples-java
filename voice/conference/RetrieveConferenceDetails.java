import java.io.IOException;
import com.plivo.api.Plivo;
import com.plivo.api.exceptions.PlivoRestException;
import com.plivo.api.models.conference.Conference;
import com.plivo.api.models.conference.Conference;

class ConferenceGet {
    public static void main(String [] args) {
        Plivo.init("YOUR_AUTH_ID","YOUR_AUTH_TOKEN");
        try {
            Conference response = Conference.getter(
						"Sample_Room" // Name of the conference room for the details to be fetched.
						)
                .get();

            System.out.println(response);
        } catch (PlivoRestException | IOException e) {
            e.printStackTrace();
        }
    }
}

/*
Sample Output
{
  "api_id": "b1947a67-35e3-11eb-a8d3-0242ac110005",
  "conference_member_count": "1",
  "conference_name": "Sample_Room",
  "conference_run_time": "617",
  "members": [
    {
      "call_uuid": "653f453f-bbdc-44a3-a254-4798f5059aa3",
      "caller_name": "",
      "deaf": false,
      "direction": "outbound",
      "from": "111111111",
      "join_time": "617",
      "member_id": "56842",
      "muted": true,
      "to": "222222222"
    }
  ]
}
*/
