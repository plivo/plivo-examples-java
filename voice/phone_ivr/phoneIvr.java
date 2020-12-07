// phoneIvr.java
import static spark.Spark.*;

import com.plivo.api.xml.GetInput;
import com.plivo.api.xml.Play;
import com.plivo.api.xml.Response;
import com.plivo.api.xml.Speak;

public class Ivr {
    public static void main(String[] args) {
        // This file will be played when a caller presses 2.
        String PlivoSong = "https://s3.amazonaws.com/plivocloud/music.mp3";
        // This is the message that Plivo reads when the caller dials in
        String IvrMessage1 = "Welcome to the Plivo IVR Demo App. Press 1 to listen to a pre recorded text in different languages. Press 2 to listen to a song.";
        String IvrMessage2 = "Press 1 for English. Press 2 for French. Press 3 for Russian";
        // This is the message that Plivo reads when the caller does nothing at all
        String NoinputMessage = "Sorry, I didn't catch that. Please hangup and try again later.";
        // This is the message that Plivo reads when the caller inputs a wrong number.
        String WronginputMessage = "Sorry, it's wrong input.";
        post("/ivr/", (request, response) -> {
            response.type("application/xml");
            Response resp = new Response();
            resp.children(
                new GetInput()
                        .action("https://77efc2639d0c.ngrok.io/ivr/firstbranch/")
                        .method("POST")
                        .inputType("dtmf")
                        .digitEndTimeout(5)
                        .redirect(true)
                        .children(
                                new Speak(IvrMessage1)
                        )
            );
            resp.children(new Speak(NoinputMessage));
            return resp.toXmlString();
        });
        post("/ivr/firstbranch/", (request, response) -> {
            response.type("application/xml");
            String digit = request.queryParams("Digits");
            Response resp = new Response();
            if (digit.equals("1")){
                resp.children(
                        new GetInput()
                                .action("https://77efc2639d0c.ngrok.io/ivr/secondbranch/")
                                .method("POST")
                                .inputType("dtmf")
                                .digitEndTimeout(5)
                                .redirect(true)
                                .children(
                                        new Speak(IvrMessage2)
                                )
                );
                resp.children(new Speak(NoinputMessage));
            }
            else if (digit.equals("2")){
                resp.children(
                        new Play(PlivoSong)
                );
            }
            else {
                resp.children(
                        new Speak(WronginputMessage)
                );
            }
            return resp.toXmlString();
        });
        post("/ivr/secondbranch/", (request, response) -> {
            response.type("application/xml");
            String digit = request.queryParams("Digits");
            Response resp = new Response();
            if (digit.equals("1")){
                resp.children(
                        new Speak("This message is being read out in English", "MAN","en-GB",1)
                );
            }
            else if (digit.equals("2")){
                resp.children(
                        new Speak("Ce message est lu en français", "MAN", "fr-FR", 1)
                );
            }
            else if (digit.equals("3")){
                resp.children(
                        new Speak("Это сообщение было прочитано в России", "MAN", "ru-RU", 1)
                );
            }
            else {
                resp.children(
                        new Speak(WronginputMessage)
                );
            }
            return resp.toXmlString();
        });
    }
}

/*
Sample Output
<Response>
<Response>
  <GetInput inputType="dtmf speech" action="https://77efc2639d0c.ngrok.io/ivr/firstbranch/">
    <Speak>Welcome to the Plivo IVR Demo App. Press 1 to listen to a pre recorded text in different languages. Press 2 to listen to a song.</Speak>
  </GetInput>
    <Speak>
        Sorry, I didn't catch that. Please hangup and try again later.
    </Speak>
</Response>

If 1 is pressed, another menu is read out. Following is the generated Speak XML.
<Response>
  <GetInput inputType="dtmf speech" action="https://77efc2639d0c.ngrok.io/ivr/secondbranch/">
        <Speak>Press 1 for English. Press 2 for French. Press 3 for Russian</Speak>
    </GetInput>
</Response>

If 1 is pressed, the English text is read out. Following is the generated Speak XML.
<Response>
    <Speak language="en-GB">This message is being read out in English</Speak>
</Response>

If 2 is pressed, the French text is read out. Following is the generated Speak XML.
<Response>
    <Speak language="fr-FR">Ce message est lu en français</Speak>
</Response>

If 3 is pressed, the Russian text is read out. Following is the generated Speak XML.
<Response>
    <Speak language="ru-RU">Это сообщение было прочитано в России</Speak>
</Response>


If 2 is pressed, a music is played. Following is the generated Play XML.
<Response>
   <Play>https://s3.amazonaws.com/plivocloud/music.mp3</Play>
</Response>
*/
