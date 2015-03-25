import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import java.util.LinkedHashMap;

import com.plivo.helper.api.client.*;
import com.plivo.helper.api.response.application.Application;
import com.plivo.helper.api.response.application.ApplicationFactory;
import com.plivo.helper.exception.PlivoException;

public class App {

    public static void main(String[] args) throws IllegalAccessException {

        String auth_id = "Your AUTH_ID";
        String auth_token = "Your AUTH_TOKEN";
        
        RestAPI api = new RestAPI(auth_id, auth_token, "v1");

        // Create an Application
        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("answer_url","http://example.com"); // The URL Plivo will fetch when a call executes this application
        parameters.put("app_name","Testing_App"); // The name of your application

        try {
            Application resp = api.createApplication(parameters);
            System.out.println((resp));
        }catch (PlivoException e){  
            System.out.println(e.getLocalizedMessage());
        }

        /*
        Sample Output
        Application [
            serverCode=201, 
            fallbackMethod=null, 
            isDefaultApplication=null, 
            applicationName=null, 
            isProductionApplication=null, 
            applicationID=10559320185257208, 
            hangupUrl=null, 
            answerUrl=null, 
            messageUrl=null, 
            resourceUri=null, 
            answerMethod=null, 
            hangupMethod=null, 
            messageMethod=null, 
            fallbackAnswerUrl=null, 
            error=null, 
            apiId=4f6b0f36-c716-11e4-9107-22000afaaa90, 
            message=created
        ]
        */

        // Get details all existing applications
        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("limit","10"); // The number of results per page
        parameters.put("offset", "0"); // The number of value items by which the results should be offset

        try {
            ApplicationFactory resp = api.getApplications(parameters);
            System.out.println(resp);
            // Print the total number of apps
            System.out.println("Total count : " + resp.meta.total_count);
        }catch (PlivoException e){  
            System.out.println(e.getLocalizedMessage());
        }

        /*
        Sample Output
        ApplicationFactory [
            serverCode=200, 
            applicationList=[
                Application [
                    serverCode=null, 
                    fallbackMethod=POST, 
                    isDefaultApplication=false, 
                    applicationName=Testing_App, 
                    isProductionApplication=null, 
                    applicationID=10559320185257208, 
                    hangupUrl=http://example.com, 
                    answerUrl=http://example.com, 
                    messageUrl=, 
                    resourceUri=/v1/Account/xxxxxxxxxxxxxxxxx/Application/10559320185257208/, 
                    answerMethod=POST, 
                    hangupMethod=POST, 
                    messageMethod=POST, 
                    fallbackAnswerUrl=, 
                    error=null, 
                    apiId=null, 
                    message=null
                ], Application [
                    serverCode=null, 
                    fallbackMethod=POST, 
                    isDefaultApplication=false, 
                    applicationName=Dial, 
                    isProductionApplication=null, 
                    applicationID=27082215185108636, 
                    hangupUrl=http://plivodirectdial.herokuapp.com/response/sip/route/?DialMusic=real&CLID=1111111111, 
                    answerUrl=http://morning-ocean-4669.herokuapp.com/response/sip/route/?DialMusic=real, 
                    messageUrl=http://plivodirectdial.herokuapp.com/response/sip/route/?DialMusic=real&CLID=11111111111, 
                    resourceUri=/v1/Account/xxxxxxxxxxxxxxxxx/Application/
                    /, 
                    answerMethod=POST, 
                    hangupMethod=POST, 
                    messageMethod=POST, 
                    fallbackAnswerUrl=http://plivodirectdial.herokuapp.com/response/sip/route/?DialMusic=real&CLID=1111111111, 
                    error=null, 
                    apiId=null, 
                    message=null
                ], 
            ]
            apiId=6fa5f0da-c717-11e4-b423-22000ac8a2f8, 
            error=null
        ]
        Total count : 9
        */

        // Get details of a single application
        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("app_id","10559320185257208"); // ID of the application for which the details have to be retrieved

        try {
            Application resp = api.getApplication(parameters);
            System.out.println(resp);
        }catch (PlivoException e){  
            System.out.println(e.getLocalizedMessage());
        }

        /*
        Sample Output
        Application [
            serverCode=200, 
            fallbackMethod=POST, 
            isDefaultApplication=false, 
            applicationName=Testing_App, 
            isProductionApplication=null, 
            applicationID=10559320185257208, 
            hangupUrl=http://example.com, 
            answerUrl=http://example.com, 
            messageUrl=, resourceUri=/v1/Account/xxxxxxxxxxxxxxxxx/Application/10559320185257208/, 
            answerMethod=POST, 
            hangupMethod=POST, 
            messageMethod=POST, 
            fallbackAnswerUrl=, 
            error=null, 
            apiId=259ae40e-c718-11e4-b423-22000ac8a2f8, 
            message=null
        ]
        */

        // Modify an application
        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("app_id","10559320185257208"); // ID of the application for which has to be modified
        parameters.put("answer_url", "http://exampletest.com"); // Values that have to be updated

        try {
            GenericResponse resp = api.editApplication(parameters);
            System.out.println(getFields(resp));
        }catch (PlivoException e){  
            System.out.println(e.getLocalizedMessage());
        }

        /*
        Sample Output
        GenericResponse [
            serverCode=202, 
            message=changed, 
            error=null, 
            apiId=daccceb4-c718-11e4-ac1f-22000ac51de6
        ]
        */

        // Delete an application
        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("app_id","10559320185257208"); // ID of the application for which has to be deleted

        try {
            GenericResponse resp = api.deleteApplication(parameters);
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
            apiId=13a16e66-c719-11e4-b423-22000ac8a2f8
        ]
        */
    }
} 