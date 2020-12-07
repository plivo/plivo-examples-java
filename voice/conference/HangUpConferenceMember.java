import com.plivo.api.Plivo;
import com.plivo.api.exceptions.PlivoRestException;
import com.plivo.api.models.conference.Conference;
import java.io.IOException;

class MemberDelete {
    public static void main(String [] args) {
        Plivo.init();
        try {
            Conference.memberHangupper(
						"My Conf Room", // Conference room name
						 "1" // Member_id that needs to hangup
						 )
            .hangup();

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
