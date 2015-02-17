package com.plivo.test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;

import com.plivo.helper.api.client.*;
import com.plivo.helper.api.response.number.NumberSearchFactory;
import com.plivo.helper.exception.PlivoException;

public class App {
    public static void main(String[] args) throws IllegalAccessException {

        String auth_id = "Your AUTH_ID";
        String auth_token = "Your AUTH_TOKEN";
        
        RestAPI api = new RestAPI(auth_id, auth_token, "v1");

        // Get all numbers
        
        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("limit","10"); // Used to display the number of results per page. 
        parameters.put("offset","0"); // Denotes the number of value items by which the results should be offset. 
        
        try{
            NumberSearchFactory resp = api.getNumbers(parameters);
            System.out.println("API ID : " + resp.apiId);
            System.out.println("Meta : ");
            System.out.println(getFields(resp.meta));
            System.out.println("Objects : ");
            int count = resp.numberList.size();
            for (int i = 0 ; i < count; i++){
                System.out.println(getFields(resp.numberList.get(i)));
            } ;
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

Get all numbers
API ID : 81e15f52-b68f-11e4-b423-22000ac8a2f8
Meta : 
previous=null
totalCount=2
offset=0
limit=10
next=null

Objects : 
serverCode=null
region=UNITED KINGDOM
isVoiceEnabled=true
isSmsEnabled=true
isFaxEnabled=null
number=1111111111
apiId=null
voiceRate=0.00500
application=null
smsRate=0.00000
numberType=local
subAccount=null
addedOn=2014-12-04
resourceUri=/v1/Account/XXXXXXXXXXXX/Number/1111111111/
numberPrefix=null
rentalRate=null
setupRate=null
country=null
lata=null
monthlyRentalRrate=0.80000
error=null
inboundCarrier=Plivo

serverCode=null
region=California, UNITED STATES
isVoiceEnabled=true
isSmsEnabled=true
isFaxEnabled=null
number=2222222222
apiId=null
voiceRate=0.00850
application=/v1/Account/XXXXXXXXXXXX/Application/26469261154421101/
smsRate=0.00000
numberType=local
subAccount=null
addedOn=2014-10-28
resourceUri=/v1/Account/XXXXXXXXXXXX/Number/2222222222/
numberPrefix=null
rentalRate=null
setupRate=null
country=null
lata=null
monthlyRentalRrate=0.80000
error=null
inboundCarrier=Plivo







