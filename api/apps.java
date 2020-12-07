import java.io.IOException;
import com.plivo.api.Plivo;
import com.plivo.api.exceptions.PlivoRestException;
import com.plivo.api.models.application.Application;
import com.plivo.api.models.application.ApplicationCreateResponse;
import com.plivo.api.models.base.ListResponse;
import com.plivo.api.models.application.ApplicationUpdateResponse;

public class App {

  public static void main(String[] args) throws IllegalAccessException {

    String auth_id = "YOUR_AUTH_ID";
    String auth_token = "YOUR_AUTH_TOKEN";

    Plivo.init("YOUR_AUTH_ID", "YOUR_AUTH_TOKEN");

    try {
      ApplicationCreateResponse response = Application.creator("app name").answerUrl("http://answer.url").create();

      System.out.println(response);
    } catch(PlivoRestException | IOException e) {
      e.printStackTrace();
    }

    /*
        Sample Output
        {
            "api_id": "4d1a18a6-33a0-11eb-b898-0242ac110003",
            "app_id": "11624750585743683",
            "message": "created
        }
        */

    // Get details all existing applications
    try {
      ListResponse < Application > response = Application.lister().offset(0).limit(5).list();

      System.out.println(response);
    } catch(PlivoRestException | IOException e) {
      e.printStackTrace();
    }

    /*
        Sample Output
        {
            "api_id": "bf3073f5-33a0-11eb-a15a-0242ac110004",
            "meta": {
                "limit": 5,
                "next": "/v1/Account/MAMDC1NTE3ZGQ4NWNJNM/Application/?limit=5\u0026offset=5",
                "offset": 0,
                "previous": null,
                "total_count": 10
            },
            "objects": [
                {
                    "answer_method": "POST",
                    "answer_url": "https://run.mocky.io/",
                    "app_id": "16063863827011417",
                    "app_name": "app1",
                    "application_type": "XML",
                    "default_app": true,
                    "default_endpoint_app": false,
                    "enabled": true,
                    "fallback_answer_url": "",
                    "fallback_method": "POST",
                    "hangup_method": "POST",
                    "hangup_url": "https://run.mocky.io/",
                    "log_incoming_message": true,
                    "message_method": "POST",
                    "message_url": "",
                    "public_uri": false,
                    "resource_uri": "/v1/Account/MAMDC1NTE3ZGQ4NWNJNM/Application/16063863827011417/",
                    "sip_uri": "sip:12222222222223@app.plivo.com",
                    "sub_account": null
                },
                {
                    "answer_method": "GET",
                    "answer_url": "https://run.mocky.io/",
                    "app_id": "77506472664956327",
                    "app_name": "app1",
                    "application_type": "XML",
                    "default_app": false,
                    "default_endpoint_app": true,
                    "enabled": true,
                    "fallback_answer_url": "",
                    "fallback_method": "POST",
                    "hangup_method": "POST",
                    "hangup_url": "https://run.mocky.io/",
                    "log_incoming_message": true,
                    "message_method": "POST",
                    "message_url": "https://run.mocky.io/",
                    "public_uri": false,
                    "resource_uri": "/v1/Account/MAMDC1NTE3ZGQ4NWNJNM/Application/77506472664956327/",
                    "sip_uri": "sip:12222222222223@app.plivo.com",
                    "sub_account": "/v1/Account/SANWY1YTZLMDK3MDZHZW/"
                },
                {
                    "answer_method": "POST",
                    "answer_url": "https://run.mocky.io/",
                    "app_id": "11624750585743683",
                    "app_name": "app name",
                    "application_type": "XML",
                    "default_app": false,
                    "default_endpoint_app": false,
                    "enabled": true,
                    "fallback_answer_url": null,
                    "fallback_method": "POST",
                    "hangup_method": "POST",
                    "hangup_url": "https://run.mocky.io/",
                    "log_incoming_message": true,
                    "message_method": "POST",
                    "message_url": null,
                    "public_uri": false,
                    "resource_uri": "/v1/Account/MAMDC1NTE3ZGQ4NWNJNM/Application/11624750585743683/",
                    "sip_uri": "sip:12222222222223@app.plivo.com",
                    "sub_account": null
                },
                {
                    "answer_method": "POST",
                    "answer_url": "https://run.mocky.io/",
                    "app_id": "17371468466407823",
                    "app_name": "app1",
                    "application_type": "XML",
                    "default_app": false,
                    "default_endpoint_app": false,
                    "enabled": true,
                    "fallback_answer_url": "",
                    "fallback_method": "POST",
                    "hangup_method": "POST",
                    "hangup_url": "https://run.mocky.io/",
                    "log_incoming_message": true,
                    "message_method": "POST",
                    "message_url": "",
                    "public_uri": false,
                    "resource_uri": "/v1/Account/MAMDC1NTE3ZGQ4NWNJNM/Application/17371468466407823/",
                    "sip_uri": "sip:12222222222223@app.plivo.com",
                    "sub_account": null
                },
                {
                    "answer_method": "POST",
                    "answer_url": "https://run.mocky.io/",
                    "app_id": "28596691685931059",
                    "app_name": "app1",
                    "application_type": "XML",
                    "default_app": false,
                    "default_endpoint_app": false,
                    "enabled": true,
                    "fallback_answer_url": "",
                    "fallback_method": "POST",
                    "hangup_method": "POST",
                    "hangup_url": "https://run.mocky.io/",
                    "log_incoming_message": true,
                    "message_method": "POST",
                    "message_url": "",
                    "public_uri": false,
                    "resource_uri": "/v1/Account/MAMDC1NTE3ZGQ4NWNJNM/Application/28596691685931059/",
                    "sip_uri": "sip:12222222222223@app.plivo.com",
                    "sub_account": null
                }
            }
        */

    // Modify an application
    try {
      ApplicationUpdateResponse response = Application.updater("16063863827011417").answerUrl("http://updated.answer.url").update();

      System.out.println(response);
    } catch(PlivoRestException | IOException e) {
      e.printStackTrace();
    }

    /*
        Sample Output
        INFO: {
            "api_id": "97cdc9bd-33a1-11eb-b898-0242ac110003",
            "message": "changed
        }
        */

    // Delete an application
    try {
      Application.deleter("15784735442685051").delete();

      System.out.println("Deleted successfully.");
    } catch(PlivoRestException | IOException e) {
      e.printStackTrace();
    }

    /*
        Sample Output
            Message: Deleted successfully.

        Unsuccessful Output
        {
            "api_id": "ddd124a7-33a1-11eb-a15a-0242ac110004",
            "error": "Application 15784735442685051 not found.
        }
        */
  }
}
