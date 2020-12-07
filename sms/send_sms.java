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
                    Collections.singletonList("+2222222222"), // Receivers' phone numbers with country code. The numbers are separated by "<" delimiter.
                    "Hello, this is test message") // Your SMS text message
                    .url(new URL("http://foo.com/sms_status/"))
                    .create();
            System.out.println(response);
        }

        catch (PlivoRestException | IOException e) {
            e.printStackTrace();
        }
    }
}

// Sample output
/*
{
  "api_id": "6cfd111e-3558-11eb-af78-0242ac110007",
  "message": "message(s) queued",
  "message_uuid": [
    "6cff70da-3558-11eb-af78-0242ac110007",
  ]
}
*/
