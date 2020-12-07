import java.io.IOException;
import com.plivo.api.Plivo;
import com.plivo.api.exceptions.PlivoRestException;
import com.plivo.api.models.base.ListResponse;
import com.plivo.api.models.endpoint.Endpoint;

public class App {

    public static void main(String[] args) {

        String auth_id = "YOUR_AUTH_ID";
        String auth_token = "YOUR_AUTH_TOKEN";

        Plivo.init("YOUR_AUTH_ID", "YOUR_AUTH_TOKEN");

        try {
            ListResponse < Endpoint > response = Endpoint.lister()
                .offset(0)
                .limit(5)
                .list();

            System.out.println(response);
        } catch (PlivoRestException | IOException e) {
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
    }
}
