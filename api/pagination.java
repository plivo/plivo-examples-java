import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import java.util.LinkedHashMap;

import com.plivo.helper.api.client.*;
import com.plivo.helper.api.response.application.ApplicationFactory;
import com.plivo.helper.exception.PlivoException;

public class App {

    public static void main(String[] args) throws IllegalAccessException {

        String auth_id = "Your AUTH_ID";
        String auth_token = "Your AUTH_TOKEN";
        
        RestAPI api = new RestAPI(auth_id, auth_token, "v1");

        // Get details all existing endpoints

        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("limit","2"); // The number of results per page
        parameters.put("offset","0"); // The number of value items by which the results should be offset

        try {
            EndpointFactory resp = api.getEndpoints(parameters);
            System.out.println(resp);
        }catch (PlivoException e){
            System.out.println(e.getLocalizedMessage());
        }

        /*
        Sample Output
        API ID : 07de6488-b118-11e4-b153-22000abcaa64
        Meta : 
        previous=null
        totalCount=3
        offset=0
        limit=2
        next=/v1/Account/XXXXXXXXXXXXXXXXX/Endpoint/?limit=2&offset=2

        Objects : 
        serverCode=null
        username=testEndpoint150210112609
        sipUri=sip:testEndpoint150210112609@phone.plivo.com
        alias=testEndpoint
        endpointId=20762201543927
        password=cc03e747a6afbbcbf8be7668acfebee5
        resourceUri=/v1/Account/XXXXXXXXXXXXXXXXX/Endpoint/20762201543927/
        apiId=null
        error=null

        serverCode=null
        username=test150108095716
        sipUri=sip:test150108095716@phone.plivo.com
        alias=TestSample
        endpointId=24753112937214
        password=147538da338b770b61e592afc92b1ee6
        resourceUri=/v1/Account/XXXXXXXXXXXXXXXXX/Endpoint/24753112937214/
        apiId=null
        error=null
        
        Browse https://api.plivo.com/v1/Account/XXXXXXXXXXXXXXXXX/Endpoint/?limit=2&offset=2
        to view the next page of results. To traverse pages, browse the 'next' and 'previous' urls
        */
    }
}