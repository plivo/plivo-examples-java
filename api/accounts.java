import java.io.IOException;
import com.plivo.api.Plivo;
import com.plivo.api.exceptions.PlivoRestException;
import com.plivo.api.models.account.Account;
import com.plivo.api.models.account.Subaccount;
import com.plivo.api.models.account.SubaccountCreateResponse;
import com.plivo.api.models.base.ListResponse;

public class App {
    public static void main(String[] args) {

        String auth_id = "YOUR_AUTH_ID";
        String auth_token = "YOUR_AUTH_TOKEN";

        Plivo.init("YOUR_AUTH_ID", "YOUR_AUTH_TOKEN");

        try {
            Account response = Account.getter()
                .get();

            System.out.println(response);
        } catch (PlivoRestException | IOException e) {
            e.printStackTrace();
        }

        /*
        Sample Output
        {
            "account_type": "standard",
            "address": "Test Address",
            "api_id": "5ec94bc2-3395-11eb-9574-0242ac110002",
            "auth_id": "MAMDC1NXXXXXXXXXXX",
            "auto_recharge": false,
            "billing_mode": "prepaid",
            "cash_credits": "2.98629",
            "city": "Austin",
            "name": "Test Account",
            "resource_uri": "/v1/Account/MAMDC1NXXXXXXXXXXX/",
            "state": "Karnataka",
            "timezone": "Asia/Kolkata"
        }
        */

        // Modify account
        try {
            AccountUpdateResponse response = Account.updater()
                .name("Test Account")
                .city("Austin")
                .address("Test Address")
                .update();

            System.out.println(response);
        } catch (PlivoRestException | IOException e) {
            e.printStackTrace();
        }

        /*
        Sample Output
       {
           "api_id": "d8a39cc8-338a-11eb-9434-0242ac110003",
           "message": "changed"
        }
        */

        // Create a sub account
        try {
            SubaccountCreateResponse response = Subaccount.creator("Test Subaccount") // Name of the Subaccount
                .enabled(true) // Specify if the Subaccount should be enabled or not.
                .create();

            System.out.println(response);
        } catch (PlivoRestException | IOException e) {
            e.printStackTrace();
        }

        /*
        Sample Output
        {
            "api_id": "bf7c1b20-3395-11eb-9574-0242ac110002",
            "auth_id": "SAMZHIOWQ2Y2XXXXXXXX",
            "auth_token": "XXXXXXXXXXXXXXXXXXXXXXXXXXXX",
            "message": "created"
        }
        */

        // Modify a sub account
        try {
            SubaccountUpdateResponse response = Subaccount.updater(
                    "SAXXXXXXXXXXXXXXXXXX", // Auth ID of the sub acccount that has to be modified
                    "Updated Subaccount Name" // Name of the subaccount
                )
                .update();

            System.out.println(response);
        } catch (PlivoRestException | IOException e) {
            e.printStackTrace();
        }
        /*
        Sample Output
        {
            "api_id": "efe633cc-3395-11eb-9434-0242ac110003",
            "message": "changed
        }
        */

        // Get details of all sub accounts
        try {
            ListResponse < Subaccount > response = Subaccount.lister()
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
           "api_id": "a8f72696-3396-11eb-8d38-0242ac110002",
           "meta": {
               "limit": 5,
                "next": null,
                "offset": 0,
                "previous": null,
                "total_count": 4
            },
            "objects": [{
                "account": "/v1/Account/MXXXXXXXXXXXXXXXX/",
                "auth_id": "SAXXXXXXXXXXXXXXXXXX",
                "auth_token": "OGXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
                "created": "2020-12-01",
                "enabled": true,
                "modified": "2020-12-01",
                "name": "Updated Subaccount Name",
                "new_auth_token": "OGXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
                "resource_uri": "/v1/Account/MXXXXXXXXXXXXXXXX/Subaccount/SAXXXXXXXXXXXXXXXXXX/"
            },
            {
                "account": "/v1/Account/MXXXXXXXXXXXXXXXX/",
                "auth_id": "SAXXXXXXXXXXXXXXXXXX",
                "auth_token": "OGXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
                "created": "2020-02-01",
                "enabled": false,
                "modified": "2020-10-02",
                "name": "Test 2",
                "new_auth_token": "OGXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
                "resource_uri": "/v1/Account/MXXXXXXXXXXXXXXXX/Subaccount/SAXXXXXXXXXXXXXXXXXX/"
            },
            {
                "account": "/v1/Account/MXXXXXXXXXXXXXXXX/",
                "auth_id": "SAXXXXXXXXXXXXXXXXXX",
                "auth_token": "OGXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
                "created": "2019-07-05",
                "enabled": false,
                "modified": "2020-09-29",
                "name": "account1",
                "new_auth_token": "OGXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
                "resource_uri": "/v1/Account/MXXXXXXXXXXXXXXXX/Subaccount/SAXXXXXXXXXXXXXXXXXX/"
            },
            {
                "account": "/v1/Account/MXXXXXXXXXXXXXXXX/",
                "auth_id": "SAXXXXXXXXXXXXXXXXXX",
                "auth_token": "OGXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
                "created": "2020-02-01",
                "enabled": true,
                "modified": null,
                "name": "suprabha",
                "new_auth_token": "OGXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
                "resource_uri": "/v1/Account/MXXXXXXXXXXXXXXXX/Subaccount/SAXXXXXXXXXXXXXXXXXX/"
            }
            ]
        }
        */

        // Get details of a particular sub acount
        try {
            Subaccount response = Subaccount.getter("SAXXXXXXXXXXXXXXXXXX") // Auth_id of the subaccount to be retrieved.
                .get();

            System.out.println(response);
        } catch (PlivoRestException | IOException e) {
            e.printStackTrace();
        }
        /*
        Sample Output
        {
            "account": "/v1/Account/MAXXXXXXXXXXXXXXXX/",
            "api_id": "ba559962-3397-11eb-9434-0242ac110003",
            "auth_id": "SAXXXXXXXXXXXXXXXXXX",
            "auth_token": "OGxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",
            "created": "2020-12-01",
            "enabled": true,
            "modified": "2020-12-01",
            "name": "Updated Subaccount Name",
            "new_auth_token": "OGxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",
            "resource_uri": "/v1/Account/MAXXXXXXXXXXXXXXXX/Subaccount/SAXXXXXXXXXXXXXXXXXX/"
        }
        */

        // Delete a sub account
        try {
            Subaccount.deleter("SAXXXXXXXXXXXXXXXXXX").cascade(true)
                .delete();

            System.out.println("Deleted successfully.");
        } catch (PlivoRestException | IOException e) {
            e.printStackTrace();
        }

        /*
        Sample Output
            No response

        Unsuccessful Output
            message: invalid subaccount ID
        */
    }
}
