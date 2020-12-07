// customTone.java
import static spark.Spark.*;
import com.plivo.api.xml.Dial;
import com.plivo.api.xml.Number;
import com.plivo.api.xml.Response;


class CustomTone {
    public static void main(String[] args) {
        get("/custom_tone", (request, response) -> {
                Response resp = new Response()
                .children(
                        new Dial()
                                .dialMusic("http://foo.com/dial_music/")
                                .children(
                                        new Number("1111111111"),
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
   <Dial dialMusic="http://foo.com/dial_music/">
      <Number>1111111111</Number>
   </Dial>
</Response>
*/

// customTonePlay.java
import static spark.Spark.*;
import com.plivo.api.xml.Play;
import com.plivo.api.xml.Response;


class CustomTonePlay {
    public static void main(String[] args) {
        get("/custom_tone_play", (request, response) -> {
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
