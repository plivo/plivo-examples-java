import static spark.Spark.*;

import com.plivo.api.xml.*;
import com.plivo.api.xml.Number;


class ForwardCall {
    public static void main(String[] args) {
        get("/forward_call", (request, response) -> {
            Response resp = new Response()
                    .children(
                            new Dial()
                                    .callerId("1111111111")
                                    .children(
                                            new Number("2222222222")
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
   <Dial callerId="1111111111">
      <Number>2222222222</Number>
   </Dial>
</Response>
*/
