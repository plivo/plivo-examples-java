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
            System.out.println(resp);
        }catch (PlivoException e){  
            System.out.println(e.getLocalizedMessage());
        }  
        
        // Buy a phone number
        LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
        params.put("number","12105030864");
        
        try {
            NumberResponse resp = api.buyPhoneNumber(params);
            System.out.println(resp);
        }catch (PlivoException e){  
            System.out.println(e.getLocalizedMessage());
        }

        // Modify a number
        LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
        params.put("number","12105030864");
        params.put("alias","test");
        
        try {
            GenericResponse resp = api.editNumber(params);
            System.out.println(resp);
        }catch (PlivoException e){  
            System.out.println(e.getLocalizedMessage());
        }
        
        // Unrent a phone number
        LinkedHashMap<String, String> param = new LinkedHashMap<String, String>();
        param.put("number","12105030864");
        
        try {
            GenericResponse resp = api.unRentNumber(param);
            System.out.println(resp);
        }catch (PlivoException e){  
            System.out.println(e.getLocalizedMessage());
        }
        
    }
}

/*
Sample Output
PhoneNumberSearchFactory [
    meta=NumberMeta [
        previous=null, 
        totalCount=100, 
        offset=0, limit=20, 
        next=/v1/Account/MAYMFHYZJKMJG0NJG4OG/PhoneNumber/?limit=20&country_iso=US&pattern=210&region=Texas&offset=20&type=local
    ], 
    apiId=3779cd70-c722-11e4-9107-22000afaaa90, 
    error=null, 
    numberList=[
        PhoneNumber [
            country=UNITED STATES,  
            lata=566, 
            monthlyRentalRrate=0.80000, 
            number=12105030741, 
            type=fixed, 
            numberPrefix=210, 
            rateCenter=SANANTONIO, 
            region=Texas, UNITED STATES, 
            resourceURI=/v1/Account/MAYMFHYZJKMJG0NJG4OG/PhoneNumber/12105030741/, 
            restriction=null, 
            restriction_text=null, 
            setupRate=0.00000, 
            isVoiceEnabled=true, 
            isSmsEnabled=true, 
            voiceRate=0.00850, 
            smsRate=0.00000
        ], PhoneNumber [
            country=UNITED STATES, 
            lata=566, 
            monthlyRentalRrate=0.80000, 
            number=12105030864, 
            type=fixed, 
            numberPrefix=210, 
            rateCenter=SANANTONIO, 
            region=Texas, UNITED STATES, 
            resourceURI=/v1/Account/MAYMFHYZJKMJG0NJG4OG/PhoneNumber/12105030864/, 
            restriction=null, 
            restriction_text=null, 
            setupRate=0.00000, 
            isVoiceEnabled=true, 
            isSmsEnabled=true, 
            voiceRate=0.00850, 
            smsRate=0.00000
        ]
    ]
]

Rent a number
NumberResponse [
    numberStatusList=[
        NumberStatus [
            number=12105030864, 
            status=Success
        ]
    ], 
    status=fulfilled, 
    error=null, 
    apiId=fc8554e0-c722-11e4-b932-22000ac50fac
]

Modify a number
GenericResponse [
    serverCode=202, 
    message=changed, 
    error=null, 
    apiId=b52fc844-c724-11e4-ac1f-22000ac51de6
]

Unrent a phone number
GenericResponse [   
    serverCode=204, 
    message=no response, 
    error=null, 
    apiId=unknown
]
*/