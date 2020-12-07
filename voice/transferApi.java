// transferApi.java
import com.plivo.api.xml.Response;
import com.plivo.api.xml.Speak;
import static spark.Spark.*;

public class TransferApi {
    public static void main(String[] args) {
        get("/transfer_api", (request, response) -> {
            Response resp = new Response()
                    .children(
                            new GetDigits()
                                    .action("http://www.foo.com/transfer/")
                                    .method("POST")
                                    .numDigits(1)
                                    .redirect(false)
                                    .timeout(7)
                                    .children(
                                            new Speak("Press 1 to transfer your call")
                                    ),
                            new Speak("Input not received. Thank you.")
                    );
            return resp.toXmlString();
        });

    }
}
/*
Sample Output
<Response>
   <GetDigits action="http://www.foo.com/transfer_action/" method="POST" timeout="7" numDigits="1" redirect="false">
      <Speak>Press 1 to transfer your call</Speak>
   </GetDigits>
   <Speak>Input not received. Thank you.</Speak>
</Response>

*/

// transferAction.java
import com.plivo.api.Plivo;
import com.plivo.api.exceptions.PlivoRestException;
import com.plivo.api.models.call.Call;
import com.plivo.api.models.call.CallUpdateResponse;
import com.plivo.api.models.call.LegSpecifier;
import java.io.IOException;
import static spark.Spark.*;

public class TransferAction {
    public static void main(String[] args) {
        get("/transfer_action", (request, response) -> {
            String digit = request.queryParams("Digits");
            String call_uuid = request.queryParams("CallUUID");
            System.out.println("Digit : " + digit + " Call UUID : " + call_uuid);

            if(digit.equals("1"))
            {
                Plivo.init("YOUR_AUTH_ID","YOUR_AUTH_TOKEN");
                try {
                    CallUpdateResponse resp = Call.updater("eba53b9e-8fbd-45c1-9444-696d2172fbc8") // UUID of the call to be transferred
                            .legs(LegSpecifier.ALEG)
                            .alegUrl("http://aleg_url")
                            .alegMethod("GET")
                            .update();
                    System.out.println(response);
                } catch (PlivoRestException | IOException e) {
                    e.printStackTrace();
                }
            }
            return "Transfer Completed";
        });
    }
}

/*
Sample Output

Digit : 1 Call UUID : 2d3ccd56-c954-11e4-aaa3-fb391e29dbd3
{
"message": "call transfered",
"api_id": "08c94608-58bd-11e1-86da-adf28403fe48"
}
*/


// The Aleg URL will excute the following for successful Transfer.
import com.plivo.api.xml.Dial;
import com.plivo.api.xml.Number;
import com.plivo.api.xml.Response;
import static spark.Spark.*;

public class Aleg_url {
    public static void main(String[] args) {
        get("/aleg_url", (request, response) -> {
            Response resp = new Response()
                    .children(
                            new Dial()
                                    .children(
                                            new Number("+111111111")
                                    )
                    );
            return resp.toXmlString();
        });
    }
}

/*
Sample output
<Response>
<Dial>
<Number>15671234567</Number>
</Dial>
</Response>
*/
