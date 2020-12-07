import static spark.Spark.*;
import com.plivo.api.xml.*;


class ReceiveCalls {
    public static void main(String[] args) {
        get("/receive_calls", (request, response) -> {
            Response resp = new Response()
                    .children(
                            new Speak("Hello, Welcome to Plivo")
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
   <Speak>Hello, Welcome to Plivo</Speak>
</Response>
*/
