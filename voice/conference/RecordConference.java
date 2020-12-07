import java.io.IOException;
import com.plivo.api.Plivo;
import com.plivo.api.exceptions.PlivoRestException;
import com.plivo.api.models.conference.Conference;
import com.plivo.api.models.conference.ConferenceRecordCreateResponse;

class RecordCreate {
    public static void main(String [] args) {
        Plivo.init("YOUR_AUTH_ID","YOUR_AUTH_TOKEN");
        try {
            ConferenceRecordCreateResponse response = Conference.recorder(
						"Sample_Room" // Name of the conference room to be recorded
						)
                    .record();

            System.out.println(response);
        } catch (PlivoRestException | IOException e) {
            e.printStackTrace();
        }
    }
}

/*
Sample Output
{
  "api_id": "2ed79ba5-35e3-11eb-83e5-0242ac110006",
  "message": "conference recording started",
  "recording_id": "2ee66bce-35e3-11eb-911a-027798047064",
  "url": "https://media.plivo.com/v1/Account/MAMDC1NTE3ZGQ4NWNJNM/Recording/2ee66bce-35e3-11eb-911a-00000000000.mp3"
}
*/
