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
            System.out.println(resp);
        }catch (PlivoException e){  
            System.out.println(e.getLocalizedMessage());            
        }
        
        // Get details of a number
        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("number","12105030864"); // Phone number for which the details have to be retrieved 
        
        try{
            Number resp = api.getNumber(parameters);
            System.out.println(resp);
        }catch (PlivoException e){  
            System.out.println(e.getLocalizedMessage());            
        }
    }
}

/*
Sample Output
NumberSearchFactory [
    serverCode=200, 
    meta=NumberMeta [
        previous=null, 
        totalCount=3, 
        offset=0, 
        limit=10, 
        next=null
    ], 
    apiId=c5ecb7fa-c71f-11e4-b932-22000ac50fac, 
    error=null, 
    numberList=[
        Number [
            serverCode=null, 
            region=California, UNITED STATES, 
            isVoiceEnabled=true, 
            isSmsEnabled=true, 
            isFaxEnabled=null, 
            number=18583650866, 
            apiId=null, 
            voiceRate=0.00850, 
            application=/v1/Account/XXXXXXXXXXXXXXXXX/Application/16634980296193768/,
            smsRate=0.00000, 
            numberType=local, 
            subAccount=null, 
            addedOn=2014-10-28, 
            resourceUri=/v1/Account/XXXXXXXXXXXXXXXXX/Number/18582650866/, 
            numberPrefix=null, 
            rentalRate=null, 
            setupRate=null, 
            country=null, 
            lata=null, 
            monthlyRentalRrate=0.80000, 
            error=null, 
            inboundCarrier=Plivo
        ], Number [
            serverCode=null, 
            region=UNITED KINGDOM, 
            isVoiceEnabled=true, 
            isSmsEnabled=true, 
            isFaxEnabled=null, 
            number=447441906862, 
            apiId=null, 
            voiceRate=0.00500, 
            application=null, 
            smsRate=0.00000, 
            numberType=local, 
            subAccount=null, 
            addedOn=2015-02-17, 
            resourceUri=/v1/Account/XXXXXXXXXXXXXXXXX/Number/447440906862/, 
            numberPrefix=null, 
            rentalRate=null, 
            setupRate=null, 
            country=null, 
            lata=null, 
            monthlyRentalRrate=0.80000, 
            error=null, 
            inboundCarrier=Plivo
        ]
    ]
]

Get details of a number

Number [
    serverCode=200, 
    region=Texas, UNITED STATES, 
    isVoiceEnabled=true, 
    isSmsEnabled=true, 
    isFaxEnabled=null, 
    number=12105030864, 
    apiId=5b3830ce-c724-11e4-af95-22000ac54c79, 
    voiceRate=0.00850, 
    application=null, 
    smsRate=0.00000, 
    numberType=local, 
    subAccount=null, 
    addedOn=2015-03-10, 
    resourceUri=/v1/Account/MAYMFHYZJKMJG0NJG4OG/Number/12105030864/, 
    numberPrefix=null, 
    rentalRate=null, 
    setupRate=null, 
    country=null, 
    lata=null, 
    monthlyRentalRrate=0.80000, 
    error=null, 
    inboundCarrier=Plivo
]







