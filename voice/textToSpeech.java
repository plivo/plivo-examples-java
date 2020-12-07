import com.plivo.api.xml.Response;
import com.plivo.api.xml.Speak;
import static spark.Spark.*;

public class TextToSpeech {
    public static void main(String[] args) {
        get("/text_to_speech", (request, response) -> {
            Response resp = new Response()
                    .children(new Speak("Hello, Welcome to Plivo","WOMAN","en-GB",1),
                            new Speak("Ce texte généré aléatoirement peut-être utilisé dans vos maquettes","WOMAN","fr-FR",1),
                            new Speak("Это случайно сгенерированный текст может быть использован в макете","WOMAN","ru-RU",1)
                    );
            return resp.toXmlString();
        });
    }
}

/*
Sample Output
<Response>
   <Speak voice="WOMAN" language="en-GB" loop="1">Hello, Welcome to Plivo</Speak>
   <Speak voice="WOMAN" language="fr-FR" loop="1">Ce texte généré aléatoirement peut-être utilisé dans vos maquettes</Speak>
   <Speak voice="WOMAN" language="ru-RU" loop="1">Это случайно сгенерированный текст может быть использован в макете</Speak>
</Response>
*/
