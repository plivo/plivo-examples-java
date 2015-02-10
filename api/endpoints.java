import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import java.util.LinkedHashMap;

import com.plivo.helper.api.client.*;
import com.plivo.helper.api.response.endpoint.Endpoint;
import com.plivo.helper.api.response.endpoint.EndpointFactory;
import com.plivo.helper.api.response.response.GenericResponse;
import com.plivo.helper.exception.PlivoException;

public class App {

    public static void main(String[] args) throws IllegalAccessException {

        String auth_id = "Your AUTH_ID";
        String auth_token = "Your AUTH_TOKEN";
        
        RestAPI api = new RestAPI(auth_id, auth_token, "v1");
        
        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("username","testEndpoint"); // The username for the endpoint to be created
        parameters.put("password","test123"); // The password for your endpoint username
        parameters.put("alias","testEndpoint"); // Alias for this endpoint
        
        // Create an Endpoint
        try {
            Endpoint resp = api.createEndpoint(parameters);
            System.out.println(getFields(resp));
        }catch (PlivoException e){
            System.out.println(e.getLocalizedMessage());
        }

        /*
        Sample Output
        serverCode=201
        username=testEndpoint150210112609
        sipUri=null
        alias=testEndpoint
        endpointId=20762201543927
        password=null
        resourceUri=null
        apiId=9c2c6b54-b117-11e4-ac1f-22000ac51de6
        error=null
        */

        // Get details of all existing endpoints
        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("limit","2"); // The number of results per page
        parameters.put("offset","0"); // The number of value items by which the results should be offset

        try {
            EndpointFactory resp = api.getEndpoints(parameters);
            System.out.println("API ID : " + resp.apiId);
            System.out.println("Meta : ");
            System.out.println(getFields(resp.meta));
            System.out.println("Objects : ");
            int count = resp.endpointList.size();
            for (int i = 0 ; i < count; i++){
                System.out.println(getFields(resp.endpointList.get(i)));
            } 
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

        Total count : 3
        */

        // Get details of a single endpoint
        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("endpoint_id","20762201543927"); // ID of the endpoint for which the details have to be retrieved

        try {
            Endpoint resp = api.getEndpoint(parameters);
            System.out.println(getFields(resp));
        }catch (PlivoException e){
            System.out.println(e.getLocalizedMessage());
        }

        /*
        Sample Output
        serverCode=200
        username=testEndpoint150210112609
        sipUri=sip:testEndpoint150210112609@phone.plivo.com
        alias=testEndpoint
        endpointId=20762201543927
        password=cc03e747a6afbbcbf8be7668acfebee5
        resourceUri=/v1/Account/XXXXXXXXXXXXXXXXX/Endpoint/20762201543927/
        apiId=8ed90146-b118-11e4-ac1f-22000ac51de6
        error=null
        */

        // Modify an endpoint
        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("endpoint_id","20762201543927"); // ID of the endpoint that has to be modified
        parameters.put("alias","NewName"); // Values that have to be updated

        try {
            GenericResponse resp = api.editEndpoint(parameters);
            System.out.println(getFields(resp));
        }catch (PlivoException e){
            System.out.println(e.getLocalizedMessage());
        }

        /*
        Sample Output
        serverCode=202
        message=changed
        error=null
        apiId=d52aa190-b118-11e4-b932-22000ac50fac
        */

        // Delete an Endpoint
        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("endpoint_id","20762201543927"); // ID of the endpoint that as to be deleted

        try {
            GenericResponse resp = api.deleteEndpoint(parameters);
            System.out.println(getFields(resp));
        }catch (PlivoException e){
            System.out.println(e.getLocalizedMessage());
        }

        /*
        Sample Output
        serverCode=204
        message=no response
        error=null
        apiId=unknown

        Unsuccessful Output
        serverCode=404
        message=null
        error=not found
        apiId=198b7aa8-b119-11e4-b423-22000ac8a2f8
        */
    }

    public static String getFields(Object obj) throws IllegalAccessException {
        StringBuffer buffer = new StringBuffer();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field f : fields) {
          if (!Modifier.isStatic(f.getModifiers())) {
            f.setAccessible(true);
            Object value = f.get(obj);
            buffer.append(f.getName());
            buffer.append("=");
            buffer.append("" + value);
            buffer.append("\n");
          }
        }
        return buffer.toString();
    }
}    