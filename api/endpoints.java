import java.io.IOException;
import com.plivo.api.Plivo;
import com.plivo.api.exceptions.PlivoRestException;
import com.plivo.api.models.base.ListResponse;
import com.plivo.api.models.endpoint.Endpoint;
import com.plivo.api.models.endpoint.EndpointCreateResponse;
import com.plivo.api.models.endpoint.EndpointUpdateResponse;

public class App {

  public static void main(String[] args) {

    String auth_id = "YOUR_AUTH_ID";
    String auth_token = "YOUR_AUTH_TOKEN";

    Plivo.init("YOUR_AUTH_ID", "YOUR_AUTH_TOKEN");

    try {
      EndpointCreateResponse response = Endpoint.creator("testusername", // Endpoint user_name
      "testpassword", // Endpoint password
      "Test Account" // Endpoint alias_name
      ).create();

      System.out.println(response);
    } catch(PlivoRestException | IOException e) {
      e.printStackTrace();
    }

    /*
        Sample Output
        {
            "alias": "Test Account",
            "api_id": "63aea1ec-339d-11eb-b9f0-0242ac110004",
            "endpoint_id": "905295392261175",
            "message": "created",
            "username": "testusername18231820540650120985555"
        }
        */

    // Get details of all existing endpoints
    try {
      ListResponse < Endpoint > response = Endpoint.lister().offset(0).limit(5).list();

      System.out.println(response);
    } catch(PlivoRestException | IOException e) {
      e.printStackTrace();
    }

    /*
        Sample Output
        {
            "api_id": "7723a116-3399-11eb-afb0-0242ac110005",
            "meta": {
                "limit": 5,
                "next": null,
                "offset": 0,
                "previous": null,
                "total_count": 5
            },
            "objects": [
                {
                    "alias": "App1",
                    "application": "/v1/Account/MAXXXXXXXXXXXXXXXX/Application/11111111111111111/",
                    "endpoint_id": "122222222222",
                    "password": "XXXXXXXXXXXXXXXXXXX",
                    "resource_uri": "/v1/Account/MAXXXXXXXXXXXXXXXX/Endpoint/122222222222/",
                    "sip_registered": "false",
                    "sip_uri": "sip:name_of_endpoint@phone.plivo.com",
                    "sub_account": null,
                    "username": "name_of_endpoint"
                },
                {
                    "alias": "App2",
                    "application": "/v1/Account/MAXXXXXXXXXXXXXXXX/Application/11111111111111111/",
                    "endpoint_id": "122222222222",
                    "password": "XXXXXXXXXXXXXXXXXXX",
                    "resource_uri": "/v1/Account/MAXXXXXXXXXXXXXXXX/Endpoint/122222222222/",
                    "sip_registered": "false",
                    "sip_uri": "sip:name_of_endpoint@phone.plivo.com",
                    "sub_account": null,
                    "username": "name_of_endpoint"
                },
                {
                    "alias": "App3",
                    "application": "/v1/Account/MAXXXXXXXXXXXXXXXX/Application/11111111111111111/",
                    "endpoint_id": "122222222222",
                    "password": "XXXXXXXXXXXXXXXXXXX",
                    "resource_uri": "/v1/Account/MAXXXXXXXXXXXXXXXX/Endpoint/122222222222/",
                    "sip_registered": "false",
                    "sip_uri": "sip:name_of_endpoint@phone.plivo.com",
                    "sub_account": null,
                    "username": "name_of_endpoint"
                },
                {
                    "alias": "App4",
                    "application": "/v1/Account/MAXXXXXXXXXXXXXXXX/Application/11111111111111111/",
                    "endpoint_id": "122222222222",
                    "password": "XXXXXXXXXXXXXXXXXXX",
                    "resource_uri": "/v1/Account/MAXXXXXXXXXXXXXXXX/Endpoint/122222222222/",
                    "sip_registered": "false",
                    "sip_uri": "sip:name_of_endpoint@phone.plivo.com",
                    "sub_account": null,
                    "username": "name_of_endpoint"
                },
                {
                    "alias": "App5",
                    "application": "/v1/Account/MAXXXXXXXXXXXXXXXX/Application/11111111111111111/",
                    "endpoint_id": "122222222222",
                    "password": "XXXXXXXXXXXXXXXXXXX",
                    "resource_uri": "/v1/Account/MAXXXXXXXXXXXXXXXX/Endpoint/122222222222/",
                    "sip_registered": "false",
                    "sip_uri": "sip:name_of_endpoint@phone.plivo.com",
                    "sub_account": null,
                    "username": "name_of_endpoint"
                },
                ]
            }

        Browse https://api.plivo.com/v1/Account/XXXXXXXXXXXXXXXXX/Endpoint/?limit=2&offset=2
        to view the next page of results. To traverse pages, browse the 'next' and 'previous' urls
        */

    // Get details of a single endpoint
    try {
      Endpoint response = Endpoint.getter("905295392261175") // Endpoint_id
      .get();

      System.out.println(response);
    } catch(PlivoRestException | IOException e) {
      e.printStackTrace();
    }

    /*
        Sample Output
        {
            "alias": "Test Account",
            "api_id": "baa895cb-339d-11eb-a15a-0242ac110004",
            "application": "/v1/Account/MAXXXXXXXXXXXXXX/Application/1222222222222222/",
            "endpoint_id": "905295392261175",
            "password": "e16b2ab8d12314bf4efbd6203906ea6c",
            "resource_uri": "/v1/Account/MAXXXXXXXXXXXXXX/Application/1222222222222222/",
            "sip_registered": "false",
            "sip_uri": "sip:testuser_name22212@phone.plivo.com",
            "sub_account": null,
            "username": "testuser_name22121"
        }
        */

    // Modify an endpoint
    try {
      EndpointUpdateResponse response = Endpoint.updater("905295392261175") // Endpoint_id
      .alias("Updated Endpoint Alias") // Alias name
      .update();

      System.out.println(response);
    } catch(PlivoRestException | IOException e) {
      e.printStackTrace();
    }

    /*
        Sample Output
        {
            "api_id": "122e12f8-339e-11eb-b898-0242ac110003",
            "message": "changed"
        }
        */

    // Delete an Endpoint
    try {
      Endpoint.deleter("905295392261234") // Endpoint_id
      .delete();

      System.out.println("Deleted successfully.");
    } catch(PlivoRestException | IOException e) {
      e.printStackTrace();
    }

    /*
        Sample Output
            Message: Deleted Successfully

        Unsuccessful Output
        {
            "api_id": "495cd1ca-339e-11eb-b9f0-0242ac110004",
            "error": "not found"
        }
        */
  }
}
