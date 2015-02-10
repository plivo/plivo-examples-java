import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import com.plivo.helper.api.client.*;
import com.plivo.helper.api.response.account.Account;

public class App {
    public static void main(String[] args) throws IllegalAccessException {
        // TODO Auto-generated method stub
        String auth_id = "Your AUTH_ID";
        String auth_token = "Your AUTH_TOKEN";
        
        RestAPI api = new RestAPI(auth_id, auth_token, "v1");
        
        // Get Details of account
        try {
            Account resp = api.getAccount();
            System.out.print(getFields(resp));
        }catch (PlivoException e){
            System.out.println(e.getLocalizedMessage());
        }

        /*
        Sample Output
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
        */

        // Modify account
        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("name","Test"); // Name of the account holder or business
        parameters.put("city","Test City"); // City of the account holder
        parameters.put("address","Sample address"); // Address of the account holder 
        parameters.put("timezone","Asia/Kolkata"); // Time zone of the account holder

        try {
            GenericResponse resp = api.editAccount(parameters);
            System.out.print(getFields(resp));
        }catch (PlivoException e){
            System.out.println(e.getLocalizedMessage());
        }

        /*
        Sample Output
        serverCode=202
        message=changed
        error=null
        apiId=3b6fb968-b10a-11e4-ac1f-22000ac51de6
        */

        // Create a sub account
        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("name","Testing"); // Name of the subaccount
        parameters.put("enabled","Test City"); // Specify if the subaccount should be enabled or not

        try {
            GenericResponse resp = api.createSubaccount(parameters);
            System.out.print(getFields(resp));
        }catch (PlivoException e){
            System.out.println(e.getLocalizedMessage());
        }

        /*
        Sample Output
        account=null
        error=null
        apiId=93f8affa-b10e-11e4-96e3-22000abcb9af
        authId=SAYWJLYWI1MZU1MWY4YT
        authToken=YzRiZTBmZjRlMjkxZGNhZWM2M2YyNWRlOTQ4YmZh
        newAuthToken=null
        resourceUri=null
        createdOn=null
        isEnabled=null
        lastModifiedOn=null
        name=null
        */

        // Modify a sub account
        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("subauth_id","SAYWJLYWI1MZU1MWY4YT"); // Auth ID of the sub acccount that has to be modified
        parameters.put("name","Testing4"); // Name of the subaccount

        try {
            GenericResponse resp = api.editSubaccount(parameters);
            System.out.print(getFields(resp));
        }catch (PlivoException e){
            System.out.println(e.getLocalizedMessage());
        }

        /*
        Sample Output
        serverCode=201
        message=created
        error=null
        apiId=67b072d2-b110-11e4-b932-22000ac50fac
        */

        // Get details of all sub accounts
        try {
            SubAccountFactory resp = api.getSubaccounts();
            System.out.println("Api ID : " + resp.apiId);
            System.out.println("Meta : ");
            System.out.println(getFields(resp.meta));
            System.out.println("Objects");
            int count = resp.subAccountList.size();
            for (int i = 0 ; i < count; i++){
                System.out.println(getFields(resp.subAccountList.get(i)));
            }     
        }catch (PlivoException e){
            System.out.println(e.getLocalizedMessage());
        }

        /*
        Sample Output
        Api ID : 0a2fd6c8-b112-11e4-96e3-22000abcb9af
        Meta : 
        previous=null
        totalCount=3
        offset=0
        limit=20
        next=null

        Objects
        account=/v1/Account/XXXXXXXXXXXXXXXXX/
        error=null
        apiId=null
        authId=SAYWJLYWI1MZU1MWY4YT
        authToken=YzRiZTBmZjRlMjkxZGNhZWM2M2YyNWRlOTQ4YmZh
        newAuthToken=YzRiZTBmZjRlMjkxZGNhZWM2M2YyNWRlOTQ4YmZh
        resourceUri=/v1/Account/XXXXXXXXXXXXXXXXX/Subaccount/SAYWJLYWI1MZU1MWY4YT/
        createdOn=2015-02-10
        isEnabled=true
        lastModifiedOn=null
        name=Testing2

        account=/v1/Account/XXXXXXXXXXXXXXXXX/
        error=null
        apiId=null
        authId=SAYJY1YTU3YJDMNJLIOT
        authToken=YWY0NDk0ZTIxZjAxYjNhMGYyMjg2NDA1ODIyOGYx
        newAuthToken=YWY0NDk0ZTIxZjAxYjNhMGYyMjg2NDA1ODIyOGYx
        resourceUri=/v1/Account/XXXXXXXXXXXXXXXXX/Subaccount/SAYJY1YTU3YJDMNJLIOT/
        createdOn=2015-02-10
        isEnabled=true
        lastModifiedOn=null
        name=Testing

        account=/v1/Account/XXXXXXXXXXXXXXXXX/
        error=null
        apiId=null
        authId=SAMWJKYJFHZTM2YWE4OW
        authToken=MjI4YzBiMDQ4MWFjODkyYWNkMDY3NDViMDZjZGUz
        newAuthToken=MjI4YzBiMDQ4MWFjODkyYWNkMDY3NDViMDZjZGUz
        resourceUri=/v1/Account/XXXXXXXXXXXXXXXXX/Subaccount/SAMWJKYJFHZTM2YWE4OW/
        createdOn=2014-12-04
        isEnabled=true
        lastModifiedOn=null
        name=Ramya
        */

        //  Print the total number of apps
        System.out.println(resp.meta.totalCount);

        // Sample Output
        // 3

        // Get details of a particular sub acount
        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("subauth_id","SAYJY1YTU3YJDMNJLIOT");
 
        try {
            SubAccount resp = api.getSubaccount(parameters);
            System.out.println(getFields(resp));
        }catch (PlivoException e){
            System.out.println(e.getLocalizedMessage());
        }
        /*
        Sample Output
        account=/v1/Account/XXXXXXXXXXXXXXXXX/
        error=null
        apiId=8c65f654-b112-11e4-b153-22000abcaa64
        authId=SAYJY1YTU3YJDMNJLIOT
        authToken=YWY0NDk0ZTIxZjAxYjNhMGYyMjg2NDA1ODIyOGYx
        newAuthToken=YWY0NDk0ZTIxZjAxYjNhMGYyMjg2NDA1ODIyOGYx
        resourceUri=/v1/Account/XXXXXXXXXXXXXXXXX/Subaccount/SAYJY1YTU3YJDMNJLIOT/
        createdOn=2015-02-10
        isEnabled=true
        lastModifiedOn=null
        name=Testing
        */

        // Delete a sub account
        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("subauth_id","SAYJY1YTU3YJDMNJLIOT");

        try {
            GenericResponse resp = api.deleteSubaccount(parameters);
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