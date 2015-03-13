import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import java.util.LinkedHashMap;

import com.plivo.helper.api.client.*;
import com.plivo.helper.api.response.pricing.PlivoPricing;
import com.plivo.helper.exception.PlivoException;

public class App {

    public static void main(String[] args) throws IllegalAccessException {

        String auth_id = "Your AUTH_ID";
        String auth_token = "Your AUTH_TOKEN";
        
        RestAPI api = new RestAPI(auth_id, auth_token, "v1");

        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("country_iso","GB");

        try {
            PlivoPricing resp = api.getPricing(parameters);
            System.out.println(resp);
        }catch (PlivoException e){  
            System.out.println(e.getLocalizedMessage());
        }   

        /*
        Sample Output
        PlivoPricing [
            serverCode=200, 
            countryCode=44, 
            countryIso=GB, 
            country=United Kingdom, 
            apiId=8fd1f5a4-c71a-11e4-b932-22000ac50fac, 
            phoneNumbers=PhoneNumbers [
                localMonthlyRental=LocalNumberRental [rate=0.80000], 
                tollfreeMonthlyRental=TollFreeNumberRental [rate=1.40000]], 
                voiceRates=VoiceRates [
                    voiceInbound=InboundVoicePricing [
                        localInbound=LocalInboundPricing [rate=0.00500], 
                        tollfreeInbound=TollfreeInboundPricing [rate=0.05000], 
                        ipInboundRate=SipInboundPricing [rate=0.00300]
                    ], 
                    voiceOutbound=OutboundVoicePricing [
                        localOutbound=LocalOutboundPricing [rate=0.01020], 
                        tollfreeOutbound=TollfreeOutboundPricing [rate=null], 
                        ipOutbound=SipOutboundPricing [rate=0.00300], 
                        prefixesRates=[
                            RatesPrefixes [
                                rate=0.01020, 
                                numberPrefixes=[44, 44203, 44207, 44208]
                            ], 
                            RatesPrefixes [
                                rate=0.01700, 
                                numberPrefixes=[443, 44551107, 4455114, 445516, 44555500, 4455551, 4455553, 447989, 447990, 447999]
                            ], 
                            RatesPrefixes [
                                rate=0.16520, 
                                numberPrefixes=[44843, 44844, 44845]
                            ], 
                            RatesPrefixes [
                                rate=0.22350, 
                                numberPrefixes=[44870]
                            ], 
                            RatesPrefixes [
                                rate=0.32010, 
                                numberPrefixes=[44871, 44872, 44873]
                            ], 
                            RatesPrefixes [
                                rate=0.40880, 
                                numberPrefixes=[4478360, 4478361, 4478369]
                            ], 
                            RatesPrefixes [
                                rate=0.42870, 
                                numberPrefixes=[447]
                            ], 
                            RatesPrefixes [
                                rate=0.44030, numberPrefixes=[4470]
                            ]
                        ]
                    ]
                ], 
                smsRates=SmsRates [
                    inboundSms=InboundSmsPricing [rate=0.00000], 
                    outboundSms=OutboundSmsPricing [rate=0.03680]
                ]
            ]
        ]
        */
    }
}