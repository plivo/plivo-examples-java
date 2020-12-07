import static spark.Spark.*;
import com.plivo.api.xml.*;
import java.util.Arrays;


class BlackList {
    public static void main(String[] args) {
        get("/black_list", (request, response) -> {
            String[] blacklist = { "1111111111", "2222222222" };
            String from_number = request.queryParams("From");
            if (Arrays.asList(blacklist).contains(from_number)) {
                Response resp = new Response()
                        .children(
                                new Hangup()
                                        .reason("rejected")
                        );
                return resp.toXmlString();
            }
            else {
                Response resp = new Response()
                        .children(
                                new Speak("Hello from Plivo")
                        );
                return resp.toXmlString();
            }
        });
    }
}
/*
Sample Output
Sample output when From number is in blacklist
<?xml version="1.0" encoding="UTF-8"?>
<Response>
   <Hangup reason="rejected" />
</Response>

Sample Output when From number is not in blacklist
<?xml version="1.0" encoding="UTF-8"?>
<Response>
   <Speak>Hello from Plivo</Speak>
</Response>
*/
