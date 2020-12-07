import java.io.IOException;
import java.util.Collections;
import com.plivo.api.Plivo;
import com.plivo.api.exceptions.PlivoRestException;
import com.plivo.api.models.call.*;

class CallCreate {
    public static void main(String [] args) {
        Plivo.init("YOUR_AUTH_ID","YOUR_AUTH_TOKEN");
        try {
            // API ID is returned for every API request.
            // Request UUID is request id of the call. This ID is returned as soon as the call is fired irrespective of whether the call is answered or not
            CallCreateResponse response = Call.creator("+111111111", Collections.singletonList("+222222222"), "http://s3.amazonaws.com/static.plivo.com/answer.xml")
                    .answerMethod("GET")
                    .create();
            System.out.println("Api_id: " + response.getApiId());
            System.out.println("Request_id: " +response.getRequestUuid());
        } catch (PlivoRestException | IOException e) {
            e.printStackTrace();
        }
        try {
            // Call UUID is th id of a live call. This ID is returned only after the call is answered.
            LiveCallListResponse response = LiveCall.listGetter()
                    .get();
            System.out.println("Call_uuid: " + response.getCalls());
        } catch (PlivoRestException | IOException e) {
            e.printStackTrace();
        }
    }
}

/*
Sample Output

Api_id: 4bd00c51-379b-11eb-a044-0242ac110003
Request_id: 91541cdc-5f1b-47de-8863-3d1712d52c3f

Call_uuid: 91541cdc-5f1b-47de-8863-3d1712d52c3f
*/
