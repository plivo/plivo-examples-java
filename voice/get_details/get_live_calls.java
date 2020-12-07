import java.io.IOException;
import com.plivo.api.Plivo;
import com.plivo.api.exceptions.PlivoRestException;
import com.plivo.api.models.call.LiveCall;
import com.plivo.api.models.call.LiveCallListResponse;

class LiveCallList {
    public static void main(String [] args) {
        Plivo.init("YOUR_AUTH_ID","YOUR_AUTH_TOKEN");
        try {
            LiveCallListResponse response = LiveCall.listGetter().get();


            System.out.println(response);
        } catch (PlivoRestException | IOException e) {
            e.printStackTrace();
        }
    }
}

/*
Sample Output
{
  "api_id": "56f1b13b-3701-11eb-b66d-0242ac110006",
  "calls": [
    "10c1a22e-2d08-41f4-8b1c-b2b9b7cc3010"
  ]
}
*/
