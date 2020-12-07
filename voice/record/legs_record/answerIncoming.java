import com.plivo.api.exceptions.PlivoXmlException;
import com.plivo.api.xml.*;
import com.plivo.api.xml.Number;

class RecordACompleteCallSession {
    public static void main(String[] args) throws PlivoXmlException {
        // A call is made to the plivo number.
        // The answer_url returns and XML that starts recording the session and then dials to another number.
        // When the user pick up, the B Leg record starts and a music is played.
        // The action URL of the Record tag will return the Session recording details
        Response response = new Response()
                .children(
                        new Record("http://foo.com/get_recording/")
                                .method("GET")
                                .recordSession(true)
                                .redirect(false),
                        new Speak("Connecting your call!"),
                        new Dial()
                                .children(
                                        new Number("111111111")
                                )
                        .callbackUrl("http://foo.com/callback/")
                        .callbackMethod("GET")
                );
        System.out.println(response.toXmlString());
    }
}

/*
Sample Output

<Response>
   <Record action="http://foo.com/get_recording/" method="GET" redirect="false" recordSession="true" />
   <Speak>Connecting your call!</Speak>
   <Dial callbackUrl="http://foo.com/callback/" callbackMethod="GET">
      <Number>111111111</Number>
   </Dial>
</Response>

*/
