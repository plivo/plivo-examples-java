import java.io.IOException;
import java.net.URL;
import java.util.Collections;

import com.plivo.api.Plivo;
import com.plivo.api.exceptions.PlivoRestException;
import com.plivo.api.models.message.Message;
import com.plivo.api.models.message.MessageCreateResponse;

class MessageCreate {
    public static void main(String[] args) {
        Plivo.init("YOUR_AUTH_ID", "YOUR_AUTH_TOKEN");
        try {
            MessageCreateResponse response = Message.creator(
                    "1111111111", // Sender's phone number with country code
                    Collections.singletonList("+2222222222<+333333333"), // Receivers' phone numbers with country code. The numbers are separated by "<" delimiter.
                    "This randomly generated text can be used in your layout "
                            + "(webdesign , websites, books, posters ... ) for free. This text is entirely free of law. "
                            + "Feel free to link to this site by using the image below or"
                            + " by making a simple text link") // Your SMS text message

                            /*parameters.put("text", "このランダムに生成されたテキストは、自由のためのあなたのレイアウト（"
                                    + "ウェブデザイン、ウェブサイト、書籍、ポスター...）で使用することができます。このテキストは、"
                                    + "法律の完全に無料です。下の画像を使用して、または単純なテキストリンクを作ることで、"
                                    + "このサイトへのリンクフリーです"); // Your SMS text message - English
                            */
                            /*parameters.put("text", "Ce texte généré aléatoirement peut-être utilisé dans vos maquettes"
                                    + " (webdesign, sites internet,livres, affiches...) gratuitement. "
                                    + "Ce texte est entièrement libre de droit. N'hésitez pas à faire un "
                                    + "lien sur ce site en utilisant l'image ci-dessous"
                                    + "ou en faisant un simple lien texte"); // Your SMS text message - French
                            */

                    .url(new URL("http://foo.com/sms_status/"))
                    .create();
            System.out.println(response);
        }

        catch (PlivoRestException | IOException e) {
            e.printStackTrace();
        }
    }
}


class MessageGet
{
    public static void main(String [] args)
    {
        Plivo.init("YOUR_AUTH_ID","YOUR_AUTH_TOKEN");
        try
        {
            Message response = Message.getter("87aeb954-3558-11eb-8618-0242ac110003") // Message UUID of the details to be fetched
                    .get();
            System.out.println(response.getUnits());
        }
        catch (PlivoRestException | IOException e)
        {
            e.printStackTrace();
        }
    }
}



// Sample Output
/*
{
  "api_id": "176be7da-355c-11eb-a738-0242ac110003",
  "message": "message(s) queued",
  "message_uuid": [
    "176dbe3e-355c-11eb-a738-0242ac110003"
  ]
}
// Output for English
Units : 2

// Output for Japanes
Units : 3

// Output for French
Units : 5
*/
