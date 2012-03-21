import com.plivo.bridge.to.command.ApplicationResponse;
import com.plivo.bridge.to.command.Speak;
import javax.xml.bind.Marshaller;


public class PlivoXml {
    public static void main(String[] args) {

        ApplicationResponse response = new ApplicationResponse();

        Speak speakElement = new Speak();
        speakElement.setText("Calling from Plivo");
        speakElement.setVoice("MAN");

        speakElement.setLanguage("en_US");
        speakElement.setLoop(8);

        response.setSpeak(speakElement);

        // Output
        // <Response>
        // <Speak loop="8" voice="MAN" language="en_US">Calling from Plivo</Speak>
        // </Response>

    }
}




