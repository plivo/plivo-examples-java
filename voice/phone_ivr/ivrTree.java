import static spark.Spark.*;
import com.plivo.api.xml.Response;
import com.plivo.api.xml.Speak;

public class IvrTree {
    public static void main(String[] args) {
        // This is the message that Plivo reads when the caller inputs a wrong number.
        String WronginputMessage = "Sorry, it's wrong input.";
        get("/ivr/secondbranch/", (request, response) -> {
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
  <GetInput inputType="dtmf speech" action="https://77efc2639d0c.ngrok.io/ivr/secondbranch/">
        <Speak>Press 1 for English. Press 2 for French. Press 3 for Russian</Speak>
    </GetInput>
    <Speak>Sorry, it's wrong input.</Speak>
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
*/
