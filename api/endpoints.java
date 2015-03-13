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
            System.out.println(resp);
        }catch (PlivoException e){
            System.out.println(e.getLocalizedMessage());
        }

        /*
        Sample Output
        Endpoint [
            serverCode=201, 
            username=testEndpoint150310115033, 
            sipUri=null, 
            alias=testEndpoint, 
            endpointId=22386716736727, 
            password=null, 
            resourceUri=null, 
            apiId=a8650632-c71b-11e4-b932-22000ac50fac, 
            error=null, 
            message=created
        ]
        */

        // Get details of all existing endpoints
        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("limit","2"); // The number of results per page
        parameters.put("offset","0"); // The number of value items by which the results should be offset

        try {
            EndpointFactory resp = api.getEndpoints(parameters);
            System.out.println(resp);
            // Print the total number of endpoints
            System.out.println("Total count : " + resp.meta.total_count);
        }catch (PlivoException e){
            System.out.println(e.getLocalizedMessage());
        }

        /*
        Sample Output
        EndpointFactory [
            serverCode=200, 
            meta=EndpointMeta [
                previous=null, 
                totalCount=4, 
                offset=0, 
                limit=2, 
                next=/v1/Account/XXXXXXXXXXXXXXXXX/Endpoint/?limit=2&offset=2
            ], 
            error=null, 
            apiId=0dceba7c-c71c-11e4-ac1f-22000ac51de6, 
            endpointList=[
                Endpoint [
                    serverCode=null, 
                    username=testEndpoint150310115033, 
                    sipUri=sip:testEndpoint150310115033@phone.plivo.com, 
                    alias=testEndpoint, 
                    endpointId=22386716736727, 
                    password=cc03e747a6afbbcbf8be7668acfebee5, 
                    resourceUri=/v1/Account/XXXXXXXXXXXXXXXXX/Endpoint/22386716736727/, 
                    apiId=null, 
                    error=null, 
                    message=null
                ], 
                Endpoint [
                    serverCode=null, 
                    username=testEndpoint123150310113848, 
                    sipUri=sip:testEndpoint123150310113848@phone.plivo.com, 
                    alias=testEndpoint123, 
                    endpointId=59627429467949, 
                    password=cc03e747a6afbbcbf8be7668acfebee5, 
                    resourceUri=/v1/Account/XXXXXXXXXXXXXXXXX/Endpoint/59627429467949/, 
                    apiId=null, 
                    error=null, 
                    message=null
                ]
            ]
        ]
        Total count : 4
        */

        // Get details of a single endpoint
        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("endpoint_id","22386716736727"); // ID of the endpoint for which the details have to be retrieved

        try {
            Endpoint resp = api.getEndpoint(parameters);
            System.out.println(resp);
        }catch (PlivoException e){
            System.out.println(e.getLocalizedMessage());
        }

        /*
        Sample Output
        Endpoint [
            serverCode=200, 
            username=testEndpoint150310115033, 
            sipUri=sip:testEndpoint150310115033@phone.plivo.com, 
            alias=testEndpoint, 
            endpointId=22386716736727, 
            password=cc03e747a6afbbcbf8be7668acfebee5, 
            resourceUri=/v1/Account/XXXXXXXXXXXXXXXXX/Endpoint/22386716736727/, 
            apiId=647de046-c71c-11e4-b932-22000ac50fac, 
            error=null, 
            message=null
        ]
        */

        // Modify an endpoint
        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("endpoint_id","22386716736727"); // ID of the endpoint that has to be modified
        parameters.put("alias","NewName"); // Values that have to be updated

        try {
            GenericResponse resp = api.editEndpoint(parameters);
            System.out.println(resp);
        }catch (PlivoException e){
            System.out.println(e.getLocalizedMessage());
        }

        /*
        Sample Output
        GenericResponse [
            serverCode=202, 
            message=changed, 
            error=null, 
            apiId=1ca52b66-c71d-11e4-9107-22000afaaa90
        ]
        */

        // Delete an Endpoint
        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("endpoint_id","22386716736727"); // ID of the endpoint that as to be deleted

        try {
            GenericResponse resp = api.deleteEndpoint(parameters);
            System.out.println(resp);
        }catch (PlivoException e){
            System.out.println(e.getLocalizedMessage());
        }

        /*
        Sample Output
        GenericResponse [
            serverCode=204, 
            message=no response, 
            error=null, 
            apiId=unknown
        ]

        Unsuccessful Output
        GenericResponse [
            serverCode=404, 
            message=null, 
            error=not found, 
            apiId=45d8f058-c71d-11e4-b932-22000ac50fac
        ]
        */
    }
}    