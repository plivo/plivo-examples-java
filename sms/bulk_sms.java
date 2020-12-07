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
    "6d006e9a-3558-11eb-af78-0242ac110007"
  ]
}
*/


// When an invalid number is given as dst parameter, an error will be thrown and the message will not be sent
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

/*
Sample Output for invalid number

INFO: {
  "api_id": "87ac8f1c-3558-11eb-8618-0242ac110003",
  "invalid_number": [
    "333333333"
  ],
  "message": "message(s) queued",
  "message_uuid": [
    "87aeb954-3558-11eb-8618-0242ac110003"
  ]
}
*/
