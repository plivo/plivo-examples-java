import java.io.IOException;
import com.plivo.api.Plivo;
import com.plivo.api.exceptions.PlivoRestException;
import com.plivo.api.models.number.NumberType;
import com.plivo.api.models.number.PhoneNumber;
import com.plivo.api.models.base.ListResponse;

class PhoneNumberList {
    public static void main(String [] args) {
        Plivo.init("YOUR_AUTH_ID","YOUR_AUTH_TOKEN");
        // Search for a number
        try {
            ListResponse<PhoneNumber> response = PhoneNumber.lister("US")
                    .type(NumberType.LOCAL)
                    .pattern("210")
                    .region("Texas")
                    .list();

            System.out.println(response);
        } catch (PlivoRestException | IOException e) {
            e.printStackTrace();
        }

        // Buy a phone number
        try {
            PhoneNumberCreateResponse response = PhoneNumber.creator("121091234466")
                .create();

            System.out.println(response);
        } catch (PlivoRestException | IOException e) {
            e.printStackTrace();
        }

        // Modify a number
        try {
            NumberUpdateResponse response = Number.updater("121091234466")
                .alias("Updated Alias")
                .update();

            System.out.println(response);
        } catch (PlivoRestException | IOException e) {
            e.printStackTrace();
        }

        // Unrent a phone number
        try {
            Number.deleter("121091234466")
                .delete();

            System.out.println("Deleted successfully.");
        } catch (PlivoRestException | IOException e) {
            e.printStackTrace();
        }
    }
}


/*
Sample Output
{
  "api_id": "cffc8358-3602-11eb-a011-0242ac110003",
  "meta": {
    "limit": 20,
    "next": "/v1/Account/MAXXXXXXXXXX/PhoneNumber/?limit=20&offset=20&pattern=210&region=Texas&type=local&country_iso=US",
    "offset": 0,
    "previous": null,
    "total_count": 50
  },
  "objects": [
    {
      "city": "San Antonio",
      "compliance_requirement": {
        "business": null,
        "individual": null
      },
      "country": "UNITED STATES",
      "lata": 566,
      "mms_enabled": true,
      "mms_rate": "0.00400",
      "monthly_rental_rate": "0.18000",
      "number": "121091234466",
      "prefix": "210",
      "prerequisites": [],
      "rate_center": "San Antonio",
      "region": "Texas",
      "resource_uri": "/v1/Account/MAXXXXXXXXXX/PhoneNumber/121091234466/",
      "restriction": "",
      "restriction_text": "",
      "setup_rate": "0.00000",
      "sms_enabled": true,
      "sms_rate": "0.00000",
      "sub_type": "local",
      "type": "fixed",
      "voice_enabled": true,
      "voice_rate": "0.00300"
    },
    {
      "city": "San Antonio",
      "compliance_requirement": {
        "business": null,
        "individual": null
      },
      "country": "UNITED STATES",
      "lata": 566,
      "mms_enabled": true,
      "mms_rate": "0.00400",
      "monthly_rental_rate": "0.18000",
      "number": "121091234466",
      "prefix": "210",
      "prerequisites": [],
      "rate_center": "San Antonio",
      "region": "Texas",
      "resource_uri": "/v1/Account/MAXXXXXXXXXX/PhoneNumber/121091234466/",
      "restriction": "",
      "restriction_text": "",
      "setup_rate": "0.00000",
      "sms_enabled": true,
      "sms_rate": "0.00000",
      "sub_type": "local",
      "type": "fixed",
      "voice_enabled": true,
      "voice_rate": "0.00300"
    },
  ]
}

Rent a number
{
    "api_id": "aa52882c-1c88-11e4-bd8a-12313f016a39",
    "message": "created",
    "numbers": [
        {
            "number": "121091234466",
            "status": "Success"
        }
    ],
    "status": "fulfilled"
}

Modify a number
{
  "message": "changed",
  "api_id": "5a9fcb68-582d-11e1-86da-6ff39efcb949"
}

Unrent a phone number
GenericResponse [
    serverCode=204,
    message=no response,
    error=null,
    apiId=unknown
]
*/
