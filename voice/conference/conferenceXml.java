import static spark.Spark.*;
import com.plivo.api.xml.*;

class Conference {
    public static void main(String[] args) {
        get("/conference", (request, response) -> {
            Response resp = new Response()
                    .children(
                            new Speak("You will now be placed into a demo conference.This is brought\n to you by Plivo.To know more visit us at plivo.com"),
                            new Conference("demo")
                                    .endConferenceOnExit(true)
                                    .startConferenceOnEnter(false)
                                    .enterSound("beep:2")
                                    .waitSound("http://www.foo.com/waitmusic/")
                    );
            return resp.toXmlString();
        });
    }
}

/*
Sample Output
<Response>
   <Speak>You will now be placed into a demo conference.This is brought
 to you by Plivo.To know more visit us at plivo.com</Speak>
   <Conference enterSound="beep:2" startConferenceOnEnter="false" endConferenceOnExit="true" waitSound="http://www.foo.com/waitmusic/">demo</Conference>
</Response>
*/
