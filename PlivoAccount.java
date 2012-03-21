import java.util.HashMap;
import java.util.Map;

import com.plivo.bridge.client.PlivoClient;

import com.plivo.bridge.exception.PlivoClientException;

import org.json.JSONObject;


public class PlivoAccount {

    public static void main(String[] args) {

        String accountID = "";
        String authToken = "";
        String subAuthId = "";
        PlivoClient client = new PlivoClient(accountID, authToken, true);

        Map<String, String> parameters = new HashMap<String, String>();

        // Get account details
        try {
            String result = client.account().getDetails();
            String.out.println(result);
        }catch(PlivoClientException e) {
            String.out.println(result);
        }


        // Get all subaccount
        try {
            String resyult = client.account().allSubaccount();
            String.out.println(result);
        }catch(PlivoClientException e) {
            String.out.println(result);
        }


        // Get subaccount
        try {
            String resyult = client.account().subaccount(subAuthId);
            String.out.println(result);
        }catch(PlivoClientException e) {
            String.out.println(result);
        }


        // Delete subaccount
        try {
            String resyult = client.account().deleteSubaccount(subAuthId);
            String.out.println(result);
        }catch(PlivoClientException e) {
            String.out.println(result);
        }
            

            
            

