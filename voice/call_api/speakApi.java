import java.io.IOException;
import com.plivo.api.Plivo;
import com.plivo.api.exceptions.PlivoRestException;
import com.plivo.api.models.call.Call;
import com.plivo.api.models.call.actions.CallSpeakCreateResponse;

class SpeakCreate {
    public static void main(String [] args) {
        Plivo.init();
        try {
            CallSpeakCreateResponse response = Call.speaker("eba53b9e-8fbd-45c1-9444-696d2172fbc8", // CallUUID of the live call
            "Hello World" //Text to be played over the call.
            )
                .speak();

            System.out.println(response);
        } catch (PlivoRestException | IOException e) {
            e.printStackTrace();
        }
    }
}

/*
Sample Output
{
  "message": "speak started",
  "api_id": "07abfd94-58c0-11e1-86da-adf28403fe48"
}
*/
