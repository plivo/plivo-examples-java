import com.plivo.api.xml.Response;
import com.plivo.api.xml.Speak;
import static spark.Spark.*;

public class GreetCaller {
    public static void main(String[] args) {
        get("/greet_caller", (request, response) -> {
            String caller_name = request.queryParams("callerName");
            String message;
            if (caller_name == null) {
                // Use a generic message
                message = "Hello Stranger";
            } else {
                // Use the caller's name
                message = "Hello " + caller_name;
            }
            Response resp = new Response()
                    .children(
                            new Speak(message)
                    );
            return resp.toXmlString());
        });
    }
}

/*
Sample Output
<Response>
    <Speak>Hello,<caller_name></Speak>
</Response>
*/
