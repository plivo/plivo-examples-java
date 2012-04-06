import java.util.HashMap;
import java.util.Map;

import com.plivo.bridge.client.PlivoClient;

import com.plivo.bridge.exception.PlivoClientException;

import org.json.JSONObject;


public class PlivoMessage {

    public static void main(String[] args) {

        String accountID = "";
        String authToken = "";

        String srcNumber = "12033XXXXX";
        String dstNumber = "13220XXXXX";

        String text = "Hi, message from Plivo";
        String type = "sms";

        PlivoClient client = new PlivoClient(accountID, authToken, true);

        Map<String, String> parameters = new HashMap<String, String>();

        parameters.put("src", srcNumber);
        parameters.put("dst", dstNumber);
        parameters.put("text", text);
        parameters.put("type", type);

        try {
            String result = client.message().sendMessage(parameters);
            System.out.println(result);
        }catch(PlivoClientException e) {
            System.out.println(e);
        }
    }
}

