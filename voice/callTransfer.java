// callTransfer.java

import static spark.Spark.*;
import com.plivo.api.xml.Redirect;
import com.plivo.api.xml.Response;
import com.plivo.api.xml.Speak;


class CallTransfer {
    public static void main(String[] args) {
        get("/call_transfer", (request, response) -> {
            Response resp = new Response()
                    .children(
                            new Speak("Please wait while your call is being transferred."),
                            new Redirect("http://foo.com/redirect")
            );
            return resp.toXmlString();
        });
    }
}

/*
Sample Output
<?xml version="1.0" encoding="UTF-8"?>
<Response>
   <Speak>Please wait while your call is being transferred.</Speak>
   <Redirect>http://foo.com/redirect</Redirect>
</Response>
*/

// connect.java

import static spark.Spark.*;
import com.plivo.api.xml.*;
import com.plivo.api.xml.Number;


class Connect {
    public static void main(String[] args) {
        get("/connect", (request, response) -> {
            Response resp = new Response()
                    .children(
                            new Speak("Connecting your call.."),
                            new Dial()
                                    .action("http://foo.com/dial status/")
                                    .method("GET")
                                    .redirect(true)
                                    .children(
                                            new Number("1111111111")
                                    )
            );
            return resp.toXmlString();
        });
    }
}

/*
Sample Output
<?xml version="1.0" encoding="UTF-8"?>
<Response>
   <Speak>Connecting your call..</Speak>
   <Dial action="http://foo.com/dial status/" method="GET" redirect="true">
      <Number>1111111111</Number>
   </Dial>
</Response>
*/
