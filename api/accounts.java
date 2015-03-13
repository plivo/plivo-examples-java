import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import java.util.LinkedHashMap;

import com.plivo.helper.api.client.*;
import com.plivo.helper.api.response.account.Account;
import com.plivo.helper.api.response.account.SubAccount;
import com.plivo.helper.api.response.account.SubAccountFactory;
import com.plivo.helper.api.response.response.GenericResponse;
import com.plivo.helper.exception.PlivoException;

public class App {
    public static void main(String[] args) throws IllegalAccessException {

        String auth_id = "Your AUTH_ID";
        String auth_token = "Your AUTH_TOKEN";
        
        RestAPI api = new RestAPI(auth_id, auth_token, "v1");
        
        // Get Details of account
        try {
            Account resp = api.getAccount();
            System.out.print(resp);
        }catch (PlivoException e){
            System.out.println(e.getLocalizedMessage());
        }

        /*
        Sample Output
        Account [
            city=Sample City
            name=Sample
            cashCredits=78.02225
            created=null
            enabled=null
            modified=null
            error=null
            apiId=d2576d1e-b108-11e4-96e3-22000abcb9af
            postpaid=null
            state=
            address=Testig address
            timezone=Asia/Kolkata
            authID=XXXXXXXXXXXXXXXXX
            resourceURI=/v1/Account/XXXXXXXXXXXXXXXXX/
        ]
        */

        // Modify account
        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("name","Test"); // Name of the account holder or business
        parameters.put("city","Test City"); // City of the account holder
        parameters.put("address","Sample address"); // Address of the account holder 
        parameters.put("timezone","Asia/Kolkata"); // Time zone of the account holder

        try {
            GenericResponse resp = api.editAccount(parameters);
            System.out.print(resp);
        }catch (PlivoException e){
            System.out.println(e.getLocalizedMessage());
        }

        /*
        Sample Output
        GenericResponse [
            serverCode=202
            message=changed
            error=null
            apiId=3b6fb968-b10a-11e4-ac1f-22000ac51de6
        ]   
        */

        // Create a sub account
        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("name","Testing"); // Name of the subaccount
        parameters.put("enabled","Test City"); // Specify if the subaccount should be enabled or not

        try {
            SubAccount resp = api.createSubaccount(parameters);
            System.out.print(resp);
        }catch (PlivoException e){
            System.out.println(e.getLocalizedMessage());
        }

        /*
        Sample Output
        SubAccount [
            account=null, 
            error=null, 
            apiId=46be5f96-c725-11e4-af95-22000ac54c79, 
            authId=SANJEYZGU1NWQ4YMQ1Y2, 
            authToken=ZmYxY2YxZmZjNzQxYTVjYTQzODUwY2QwZDhlY2I0, 
            newAuthToken=null, 
            resourceUri=null, 
            createdOn=null, 
            isEnabled=null, 
            lastModifiedOn=null, 
            name=null, 
            message=created
        ]
        */

        // Modify a sub account
        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("subauth_id","SAYWJLYWI1MZU1MWY4YT"); // Auth ID of the sub acccount that has to be modified
        parameters.put("name","Testing4"); // Name of the subaccount

        try {
            GenericResponse resp = api.editSubaccount(parameters);
            System.out.print(resp);
        }catch (PlivoException e){
            System.out.println(e.getLocalizedMessage());
        }

        /*
        Sample Output
        GenericResponse [
            serverCode=201, 
            message=created, 
            error=null, 
            apiId=8e2f7e1e-c725-11e4-8ccf-22000afb14f7
        ]
        */

        // Get details of all sub accounts
        try {
            SubAccountFactory resp = api.getSubaccounts();
            System.out.println(resp);  
            //  Print the total number of apps
            System.out.println("Total count : " + resp.meta.totalCount);
        }catch (PlivoException e){
            System.out.println(e.getLocalizedMessage());
        }

        /*
        Sample Output
        SubAccountFactory [
            serverCode=200, 
            meta=SubAccountMeta [
                previous=null, 
                totalCount=2, 
                offset=0, 
                limit=20, 
                next=null
            ], 
            error=null, 
            apiId=e19cb51c-c725-11e4-9107-22000afaaa90, 
            subAccountList=[
                SubAccount [
                    account=/v1/Account/XXXXXXXXXXXXXXXXX/, 
                    error=null, apiId=null, 
                    authId=SAM2IYMJUWODK2ZGMWOW, 
                    authToken=YjllZWNhYzk5ZDhjZjhmMTRkMjJlOTY1ZDJjYmQx, 
                    newAuthToken=YjllZWNhYzk5ZDhjZjhmMTRkMjJlOTY1ZDJjYmQx, 
                    resourceUri=/v1/Account/XXXXXXXXXXXXXXXXX/Subaccount/SAM2IYMJUWODK2ZGMWOW/, 
                    createdOn=2015-03-10, 
                    isEnabled=false, 
                    lastModifiedOn=null, 
                    name=Testing001, 
                    message=null
                ], SubAccount [
                    account=/v1/Account/XXXXXXXXXXXXXXXXX/, 
                    error=null, 
                    apiId=null, 
                    authId=SANTNKM2M4ZMFJOGVKYZ, 
                    authToken=YTYxMjY0YTlhNDMzMjMwYmVkZmQ4ZDk1ODdlZTI1, 
                    newAuthToken=YTYxMjY0YTlhNDMzMjMwYmVkZmQ4ZDk1ODdlZTI1, 
                    resourceUri=/v1/Account/XXXXXXXXXXXXXXXXX/Subaccount/SANTNKM2M4ZMFJOGVKYZ/, 
                    createdOn=2015-03-10, 
                    isEnabled=true, 
                    lastModifiedOn=null, 
                    name=Testing, 
                    message=null
                ]
            ]
        ]
        Total count : 2
        */

        // Get details of a particular sub acount
        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("subauth_id","SANJEYZGU1NWQ4YMQ1Y2");
 
        try {
            SubAccount resp = api.getSubaccount(parameters);
            System.out.println(resp);
        }catch (PlivoException e){
            System.out.println(e.getLocalizedMessage());
        }
        /*
        Sample Output
        SubAccount [
            account=/v1/Account/XXXXXXXXXXXXXXXXX/, 
            error=null, 
            apiId=580f06b4-c726-11e4-8ccf-22000afb14f7, 
            authId=SANJEYZGU1NWQ4YMQ1Y2, 
            authToken=YjllZWNhYzk5ZDhjZjhmMTRkMjJlOTY1ZDJjYmQx, 
            newAuthToken=YjllZWNhYzk5ZDhjZjhmMTRkMjJlOTY1ZDJjYmQx, 
            resourceUri=/v1/Account/XXXXXXXXXXXXXXXXX/Subaccount/SANJEYZGU1NWQ4YMQ1Y2/, 
            createdOn=2015-03-10, 
            isEnabled=false, 
            lastModifiedOn=null, 
            name=Testing001,
            message=null
        ]
        */

        // Delete a sub account
        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("subauth_id","SANJEYZGU1NWQ4YMQ1Y2");

        try {
            GenericResponse resp = api.deleteSubaccount(parameters);
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
            apiId=a293684c-c726-11e4-b423-22000ac8a2f8
        ]
        */
    }
}