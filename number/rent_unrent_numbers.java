package sending_sms.sending_sms;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;

import com.plivo.helper.api.client.*;
import com.plivo.helper.api.response.number.NumberResponse;
import com.plivo.helper.api.response.number.PhoneNumberSearchFactory;
import com.plivo.helper.api.response.response.GenericResponse;
import com.plivo.helper.exception.PlivoException;

public class App {
    public static void main(String[] args) throws IllegalAccessException {

        String auth_id = "Your AUTH_ID";
        String auth_token = "Your AUTH_TOKEN";
        
        RestAPI api = new RestAPI(auth_id, auth_token, "v1");
        
        // Search for a phone number
        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("country_iso","US");
        parameters.put("type","local");
        parameters.put("pattern","210");
        parameters.put("region","Texas");

        try {
            PhoneNumberSearchFactory resp = api.searchPhoneNumber(parameters);
            System.out.println("API ID : " + resp.apiId);
            System.out.println("Meta :");
            System.out.println(getFields(resp.meta));
            System.out.println("Number List :");
            int count = resp.numberList.size();
            for (int i = 0 ; i < count; i++){
                System.out.println(getFields(resp.numberList.get(i)));
            }
        }catch (PlivoException e){  
            System.out.println(e.getLocalizedMessage());
        }  
        
        // Buy a phone number
        LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
        params.put("number","12109206499");
        
        try {
            NumberResponse resp = api.buyPhoneNumber(params);
            System.out.println(getFields(resp));
        }catch (PlivoException e){  
            System.out.println(e.getLocalizedMessage());
        }
        
        // Unrent a phone number
        LinkedHashMap<String, String> param = new LinkedHashMap<String, String>();
        param.put("number","12109206499");
        
        try {
            GenericResponse resp = api.unRentNumber(param);
            System.out.println(getFields(resp));
        }catch (PlivoException e){  
            System.out.println(e.getLocalizedMessage());
        }
        
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

/*
Sample Output
API ID : 3c0d2ec8-b6a0-11e4-af95-22000ac54c79
Meta :
previous=null
totalCount=98
offset=0
limit=20
next=/v1/Account/XXXXXXXXXXXX/PhoneNumber/?limit=20&country_iso=US&pattern=210&region=Texas&offset=20&type=local

Number List :
country=UNITED STATES
lata=566
monthlyRentalRrate=0.80000
number=12109206500
type=fixed
numberPrefix=210
rateCenter=SANANTONIO
region=Texas, UNITED STATES
resourceURI=/v1/Account/XXXXXXXXXXXX/PhoneNumber/12109206500/
restriction=null
restriction_text=null
setupRate=0.00000
isVoiceEnabled=true
isSmsEnabled=true
voiceRate=0.00850
smsRate=0.00000

country=UNITED STATES
lata=566
monthlyRentalRrate=0.80000
number=12109206501
type=fixed
numberPrefix=210
rateCenter=SANANTONIO
region=Texas, UNITED STATES
resourceURI=/v1/Account/XXXXXXXXXXXX/PhoneNumber/12109206501/
restriction=null
restriction_text=null
setupRate=0.00000
isVoiceEnabled=true
isSmsEnabled=true
voiceRate=0.00850
smsRate=0.00000

Rent a number
API ID : bce9914e-b6a0-11e4-b932-22000ac50fac
Status : fulfilled
number=12109206499
status=Success

Unrent a phone number
serverCode=204
message=no response
error=null
apiId=unknown
*/