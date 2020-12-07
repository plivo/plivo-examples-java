import java.io.IOException;
import com.plivo.api.Plivo;
import com.plivo.api.exceptions.PlivoRestException;
import com.plivo.api.models.conference.Conference;

class SpeakDelete {
    public static void main(String [] args) {
        Plivo.init("YOUR_AUTH_ID","YOUR_AUTH_TOKEN");
        try {
            Conference.memberSpeakStopper(
						"Sample_Room", // Conference room name
						"56842" // Member_id of the conference
						)
                    .delete();

            System.out.println("Deleted successfully.");
        } catch (PlivoRestException | IOException e) {
            e.printStackTrace();
        }
    }
}

/*
Sample Output
serverCode=204
message=no response
error=null
apiId=unknown

Deleted successfully.
*/
