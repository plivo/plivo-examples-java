import static spark.Spark.*;
import com.plivo.api.exceptions.PlivoXmlException;
import com.plivo.api.xml.Dial;
import com.plivo.api.xml.Number;
import com.plivo.api.xml.Response;
import com.plivo.api.xml.User;

public class CallHunting {
    public static void main(String[] args) {
        get("/call_hunting", (request, response) -> {
            Response resp = new Response()
            .children(
                new Dial()
                .action("http://foo.com/dial action/")
                .timeout(20)
                .children(
                    new User("sip:alice1234@phone.plivo.com"),
                    new Number("18217654321"),
                    new User("sip:john1234@phone.plivo.com")
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
   <Dial action="http://foo.com/dial action/" timeout="20">
      <User>sip:alice1234@phone.plivo.com</User>
      <Number>18217654321</Number>
      <User>sip:john1234@phone.plivo.com</User>
   </Dial>
</Response>
*/