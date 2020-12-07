import static spark.Spark.*;
import com.plivo.api.xml.*;
import com.plivo.api.xml.Number;


class SeqDial {
    public static void main(String[] args) {
        get("/sequential_dial", (request, response) -> {
            Response resp = new Response()
                    .children(
                            new Dial()
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
   <Dial>
      <User>sip:alice1234@phone.plivo.com</User>
      <Number>18217654321</Number>
      <User>sip:john1234@phone.plivo.com</User>
   </Dial>
</Response>
*/
