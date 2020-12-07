import java.io.IOException;
import com.plivo.api.Plivo;
import com.plivo.api.exceptions.PlivoRestException;
import com.plivo.api.models.conference.Conference;
import com.plivo.api.models.conference.ConferenceMemberActionResponse;

class KickCreate {
    public static void main(String [] args) {
        Plivo.init("MAMDC1NTE3ZGQ4NWNJNM","NWYzOGM1NTAyYmMzZTFmMjYzZTRmZDA1NWZlNDM5");
        try {
            ConferenceMemberActionResponse response = Conference.memberKicker(
						"Sample_Room", // Conference room name
						"25854" // Member_id to be kicked
						)
                    .kick();

            System.out.println(response);
        } catch (PlivoRestException | IOException e) {
            e.printStackTrace();
        }
    }
}

/*
Sample Output
{
  "api_id": "1ba6dc29-35e1-11eb-83e5-0242ac110006",
  "member_id": [
    "25854"
  ],
  "message": "kicked"
}
*/
