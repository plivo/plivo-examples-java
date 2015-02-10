import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import java.util.LinkedHashMap;

import com.plivo.helper.api.client.*;
import com.plivo.helper.api.response.application.Application;
import com.plivo.helper.api.response.application.ApplicationFactory;
import com.plivo.helper.api.response.response.GenericResponse;
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
            GenericResponse resp = api.createApplication(parameters);
            System.out.println(getFields(resp));
        }catch (PlivoException e){  
            System.out.println(e.getLocalizedMessage());
        }

        /*
        Sample Output
        serverCode=201
        message=created
        error=null
        apiId=a3675148-b119-11e4-b932-22000ac50fac
        */

        // Get details all existing applications

        try {
            ApplicationFactory resp = api.getApplications();
            System.out.println("API ID : " + resp.apiId);
            System.out.println("Meta : ");
            System.out.println(getFields(resp.meta));
            System.out.println("Objects : ");
            int count = resp.applicationList.size();
            for (int i = 0 ; i < count; i++){
                System.out.println(getFields(resp.applicationList.get(i)));
            } 
            // Print the total number of apps
            System.out.println("Total count : " + resp.meta.total_count);
        }catch (PlivoException e){  
            System.out.println(e.getLocalizedMessage());
        }

        /*
        Sample Output
        API ID : 80be47f2-b11c-11e4-a2d1-22000ac5040c
        Meta : 
        previous=null
        total_count=9
        offset=0
        limit=20
        next=null

        Objects : 
        serverCode=null
        fallbackMethod=POST
        isDefaultApplication=false
        applicationName=Testing_App
        isProductionApplication=null
        applicationID=21722652454836403
        hangupUrl=http://example.com
        answerUrl=http://example.com
        messageUrl=
        resourceUri=/v1/Account/XXXXXXXXXXXXXXXXX/Application/21722652454836403/
        answerMethod=POST
        hangupMethod=POST
        messageMethod=POST
        fallbackAnswerUrl=
        error=null

        serverCode=null
        fallbackMethod=POST
        isDefaultApplication=false
        applicationName=Sip default
        isProductionApplication=null
        applicationID=16982793927977910
        hangupUrl=http://plivodirectdial.herokuapp.com/response/sip/route/?DialMusic=real&CLID=919663489533
        answerUrl=http://plivodirectdial.herokuapp.com/response/sip/route/?DialMusic=real&CLID=919663489533
        messageUrl=http://plivodirectdial.herokuapp.com/response/sip/route/?DialMusic=real&CLID=919663489533
        resourceUri=/v1/Account/XXXXXXXXXXXXXXXXX/Application/16982793927977910/
        answerMethod=POST
        hangupMethod=POST
        messageMethod=POST
        fallbackAnswerUrl=http://plivodirectdial.herokuapp.com/response/sip/route/?DialMusic=real&CLID=919663489533
        error=null

        Total count : 9
        */

        // Get details of a single application
        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("app_id","221722652454836403"); // ID of the application for which the details have to be retrieved

        try {
            Application resp = api.getApplication(parameters);
            System.out.println(getFields(resp));
        }catch (PlivoException e){  
            System.out.println(e.getLocalizedMessage());
        }

        /*
        Sample Output
        serverCode=200
        fallbackMethod=POST
        isDefaultApplication=false
        applicationName=Testing_App
        isProductionApplication=null
        applicationID=21722652454836403
        hangupUrl=http://example.com
        answerUrl=http://example.com
        messageUrl=
        resourceUri=/v1/Account/XXXXXXXXXXXXXXXXX/Application/21722652454836403/
        answerMethod=POST
        hangupMethod=POST
        messageMethod=POST
        fallbackAnswerUrl=
        error=null
        */

        // Modify an application
        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("app_id","221722652454836403"); // ID of the application for which has to be modified

        try {
            GenericResponse resp = api.editApplication(parameters);
            System.out.println(getFields(resp));
        }catch (PlivoException e){  
            System.out.println(e.getLocalizedMessage());
        }

        /*
        Sample Output
        serverCode=202
        message=changed
        error=null
        apiId=e118a2b8-b11d-11e4-b932-22000ac50fac
        */

        // Delete an application
        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("app_id","221722652454836403"); // ID of the application for which has to be deleted

        try {
            GenericResponse resp = api.deleteApplication(parameters);
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
        apiId=08eac172-b11e-11e4-b423-22000ac8a2f8
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