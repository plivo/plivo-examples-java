import static spark.Spark.*;
import com.plivo.api.xml.Dial;
import com.plivo.api.xml.Number;
import com.plivo.api.xml.Response;
import com.plivo.api.xml.Speak;


class ConnectCall {
    public static void main(String[] args) {
        get("/connect_call", (request, response) -> {
                Response resp = new Response()
                .children(
                        new Speak("Connecting your call"),
                        new Dial()
                                .action("http://foo.com/dial status/")
                                .method("POST")
                                .redirect(true)
                                .children(
                                        new Number("1111111111")
                                )
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
   <Speak>Connecting your call</Speak>
   <Dial>
      <Number>1111111111</Number>
   </Dial>
</Response>
*/
