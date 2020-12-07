import static spark.Spark.*;
import java.io.IOException;
import com.plivo.api.xml.Dial;
import com.plivo.api.xml.Number;
import com.plivo.api.xml.Response;


class CallWhisper {
    public static void main(String[] args) {
        get("/call_whisper", (request, response) -> {
                Response resp = new Response()
                .children(
                        new Dial()
                                .confirmKey("5")
                                .confirmSound("http://confirm_sound/")
                                .children(
                                        new Number("18217654321"),
                                        new Number("15671234567"),
                                        new Number("15671289109")
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
   <Dial confirmSound="http://confirm_sound/" confirmKey="5">
      <Number>18217654321</Number>
      <Number>15671234567</Number>
      <Number>15671289109</Number>
   </Dial>
</Response>
*/

// confirmSound.jav

import static spark.Spark.*;
import com.plivo.api.xml.Response;
import com.plivo.api.xml.Speak;


class speak {
    public static void main(String[] args) {
        get("/speak", (request, response) -> {
            Response resp = new Response()
                    .children(
                            new Speak("Press 5 to answer the call")
                                    .loop(3)
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
    <Speak>Press 5 to answer the call</Speak>
</Response>
*/
