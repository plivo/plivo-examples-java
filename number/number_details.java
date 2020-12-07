import java.io.IOException;
import com.plivo.api.Plivo;
import com.plivo.api.exceptions.PlivoRestException;
import com.plivo.api.models.number.Number;
import com.plivo.api.models.base.ListResponse;


class NumberList {
    public static void main(String [] args) {
        Plivo.init("YOUR_AUTH_ID","YOUR_AUTH_TOKEN");
        //Get all number details
        try {
            ListResponse<Number> response = Number.lister()
                    .limit(5)
                    .offset(0)
                    .list();

            System.out.println(response);
        } catch (PlivoRestException | IOException e) {
            e.printStackTrace();
        }

        // Get details of a number
        try {
            Number response = Number.getter("17609915566") // Phone number for which the details have to be retrieved
                .get();

            System.out.println(response);
        } catch (PlivoRestException | IOException e) {
            e.printStackTrace();
        }
    }
}


/*
Get details of all numbers
Sample Output
  {
    "api_id": "2e9e62aa-35fe-11eb-9b3c-0242ac110003",
    "meta": {
        "limit": 5,
        "next": "/v1/Account/MAMDC1NTE3ZGQ4NWNJNM/Number/?limit=5&offset=5",
        "offset": 0,
        "previous": null,
        "total_count": 9
    },
    "objects": [{
            "active": true,
            "added_on": "2019-06-01",
            "alias": "",
            "application": "/v1/Account/MAXXXXXXXX/Application/11296975983863565/",
            "carrier": "Plivo",
            "city": null,
            "compliance_application_id": null,
            "compliance_status": null,
            "country": "United States",
            "mms_enabled": false,
            "mms_rate": "0.00000",
            "monthly_rental_rate": "0.25000",
            "number": "1111111111",
            "number_type": "tollfree",
            "region": "United States",
            "resource_uri": "/v1/Account/MAXXXXXXXX/Number/1111111111/",
            "sms_enabled": true,
            "sms_rate": "0.00370",
            "sub_account": null,
            "type": "tollfree",
            "verification_info": [],
            "voice_enabled": true,
            "voice_rate": "0.01270"
        },
        {
            "active": true,
            "added_on": "2019-10-19",
            "alias": "",
            "application": "/v1/Account/MAXXXXXXXX/Application/77543232947799695/",
            "carrier": "Plivo",
            "city": null,
            "compliance_application_id": null,
            "compliance_status": null,
            "country": "United States",
            "mms_enabled": true,
            "mms_rate": "0.00400",
            "monthly_rental_rate": "0.18000",
            "number": "2222222222",
            "number_type": "local",
            "region": "California, UNITED STATES",
            "resource_uri": "/v1/Account/MAMDC1NTE3ZGQ4NWNJNM/Number/2222222222/",
            "sms_enabled": true,
            "sms_rate": "0.00000",
            "sub_account": null,
            "type": "local",
            "verification_info": [],
            "voice_enabled": true,
            "voice_rate": "0.00300"
        },
        {
            "active": true,
            "added_on": "2020-05-21",
            "alias": "Test",
            "application": null,
            "carrier": "Plivo",
            "city": null,
            "compliance_application_id": null,
            "compliance_status": null,
            "country": "United States",
            "mms_enabled": true,
            "mms_rate": "0.00400",
            "monthly_rental_rate": "0.18000",
            "number": "3333333333333",
            "number_type": "local",
            "region": "United States",
            "resource_uri": "/v1/Account/MAXXXXXXXX/Number/3333333333333/",
            "sms_enabled": true,
            "sms_rate": "0.00000",
            "sub_account": null,
            "type": "local",
            "verification_info": [],
            "voice_enabled": true,
            "voice_rate": "0.00300"
        },
        {
            "active": true,
            "added_on": "2020-11-20",
            "alias": null,
            "application": null,
            "carrier": "Plivo",
            "city": "Bayview",
            "compliance_application_id": null,
            "compliance_status": null,
            "country": "United States",
            "mms_enabled": true,
            "mms_rate": "0.00400",
            "monthly_rental_rate": "0.18000",
            "number": "44444444444",
            "number_type": "local",
            "region": "Idaho, UNITED STATES",
            "resource_uri": "/v1/Account/MAXXXXXXXX/Number/44444444444/",
            "sms_enabled": true,
            "sms_rate": "0.00000",
            "sub_account": null,
            "type": "local",
            "verification_info": [],
            "voice_enabled": true,
            "voice_rate": "0.00300"
        },
        {
            "active": true,
            "added_on": "2020-11-20",
            "alias": null,
            "application": null,
            "carrier": "Plivo",
            "city": "Dauphin Island",
            "compliance_application_id": null,
            "compliance_status": null,
            "country": "United States",
            "mms_enabled": true,
            "mms_rate": "0.00400",
            "monthly_rental_rate": "0.18000",
            "number": "55555555555555",
            "number_type": "local",
            "region": "Alabama, UNITED STATES",
            "resource_uri": "/v1/Account/MAXXXXXXXX/Number/55555555555555/",
            "sms_enabled": true,
            "sms_rate": "0.00000",
            "sub_account": null,
            "type": "local",
            "verification_info": [],
            "voice_enabled": true,
            "voice_rate": "0.00300"
        }
    ]
}
*/


/*
Get details of a number
Sample Output
{
  "added_on": "2014-02-14",
  "alias": null,
  "api_id": "88625e5e-1c92-11e4-80aa-12313f048015",
  "application": "/v1/Account/MAXXXXXXXXXXXXXXXXXX/Application/29986316244302815/",
  "carrier": "Plivo",
  "monthly_rental_rate": "0.80000",
  "number": "17609915566",
  "number_type": "local",
  "region": "California, UNITED STATES",
  "resource_uri": "/v1/Account/MAXXXXXXXXXXXXXXXXXX/Number/17609915566/",
  "sms_enabled": true,
  "sms_rate": "0.00000",
  "sub_account": null,
  "voice_enabled": true,
  "voice_rate": "0.00850"
}
*/
