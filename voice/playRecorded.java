import static spark.Spark.*;
import com.plivo.api.xml.Play;
import com.plivo.api.xml.Response;


class PlayRecord {
    public static void main(String[] args) {
        get("/play_record", (request, response) -> {
                Response resp = new Response()
            .children(
                    new Play("https://s3.amazonaws.com/Trumpet.mp3")
            );
              return resp.toXmlString();
        });
    }
}

/*
Sample Output
<?xml version="1.0" encoding="UTF-8"?>
<Response>
   <Play>https://s3.amazonaws.com/Trumpet.mp3</Play>
</Response>
*/
