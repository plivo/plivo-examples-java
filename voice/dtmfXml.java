import static spark.Spark.*;
import com.plivo.api.xml.*;


class Dtmf {
    public static void main(String[] args) {
        get("/dtmf", (request, response) -> {
            Response resp = new Response()
                    .children(
                            new Speak("Connecting your call.."),
                            new Dtmf("12345")
                    );
            System.out.println(resp.toXmlString());
            return resp.toXmlString();
        });
    }
}

/*
Sample Output
<?xml version="1.0" encoding="UTF-8"?>
<Response>
   <Speak>Connecting your call..</Speak>
   <DTMF>12345</DTMF>
</Response>
*/
