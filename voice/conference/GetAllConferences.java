import java.io.IOException;
import com.plivo.api.Plivo;
import com.plivo.api.exceptions.PlivoRestException;
import com.plivo.api.models.conference.Conference;
import com.plivo.api.models.conference.ConferenceList;

class ConferenceGet {
    public static void main(String [] args) {
        Plivo.init("YOUR_AUTH_ID","YOUR_AUTH_TOKEN");
        try {
            ConferenceList response = Conference.listGetter()
                    .get();
            System.out.println(response);

						//Prints only the name of the conference
            System.out.println("Conference name : " + response.getConferences());
        } catch (PlivoRestException | IOException e) {
            e.printStackTrace();
        }
    }
}

/*
Sample Output
{
  "api_id": "7db30b8f-35df-11eb-a568-0242ac110008",
  "conferences": [
    "Sample_Room"
  ]
}
Only the name of the conference
Conference name : [Sample_Room]
*/
