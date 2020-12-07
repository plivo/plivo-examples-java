// machineDetecttionCall.java
import com.plivo.api.Plivo;
import com.plivo.api.exceptions.PlivoRestException;
import com.plivo.api.models.call.Call;
import com.plivo.api.models.call.CallCreateResponse;
import java.io.IOException;
import java.util.Collections;
import static spark.Spark.*;

public class MachineDetection {
    public static void main(String[] args) {
        get("/machine_detection_call", (request, response) -> {
            Plivo.init("YOUR_AUTH_ID", "YOUR_AUTH_TOKEN");
            try {
                CallCreateResponse resp = Call.creator("+14151234567", Collections.singletonList("+15671234567"), "http://www.foo.com/detect/")
                        .answerMethod("GET")
                        .machineDetectionUrl("http://www.foo.com/machine_detection/")
                        .machineDetectionTime(10000L)
                        .machineDetectionMethod("GET")
                        .machineDetection("true")
                        .create();

                System.out.println(resp);
            } catch (PlivoRestException | IOException e) {
                e.printStackTrace();
            }
            return "Call attempted";
        });
    }
}

// detect.java
import com.plivo.api.xml.*;
import static spark.Spark.*;

public class Detect {
    public static void main(String[] args) {
        get("/detect", (request, response) -> {
            Response resp = new Response()
                    .children(
                            new Wait()
                                    .minSilence(3000)
                                    .silence(true)
                                    .length(10),
                            new Speak("Hello Voicemail!")
                    );
            return resp.toXmlString();
        });
    }
}

/*
Sample Output
<Response>
   <Wait length="10" silence="true" minSilence="3000" />
   <Speak>Hello Voicemail!</Speak>
</Response>
*/

// machineDetection.java
import static spark.Spark.*;

public class Event {
    public static void main(String[] args) {
        get("/event", (request, response) -> {
            String from_number = request.queryParams("From");
            String to_number = request.queryParams("To");
            String machine = request.queryParams("Machine");
            String call_uuid = request.queryParams("CallUUID");
            String event = request.queryParams("Event");
            String status = request.queryParams("CallStatus");
            return "From : " + from_number + " Machine : " + machine + " To : " + to_number + " Call UUID : " + call_uuid + " Event : " + event + " Call Status : " + status;
        });
    }
}

/*
Sample Output
From : 1111111111 Machine : true To : 2222222222 Call UUID : 0bee9dca-bb24-11e4-9838-f1e82e26fc28 Event : MachineDetection Call Status : in-progress
*/
