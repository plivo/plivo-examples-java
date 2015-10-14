package sending_sms.sending_sms;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;

import com.plivo.helper.api.client.*;
import com.plivo.helper.api.response.call.CDRFactory;
import com.plivo.helper.exception.PlivoException;

public class App {
    public static void main(String[] args) throws IllegalAccessException {

        String auth_id = "Your AUTH_ID";
        String auth_token = "Your AUTH_TOKEN";
        
        RestAPI api = new RestAPI(authId, authToken, "v1");

        // Without filters
        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>(); 
            
        try {
            CDRFactory cdr = api.getCDRs(parameters);
            System.out.println(cdr);
        } catch (PlivoException e) {
            System.out.println(e.getLocalizedMessage());
        }

        // Filtering the response

        RestAPI apis = new RestAPI(authId, authToken, "v1");

        LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
        params.put("call_direction", "outbound");
        params.put("from_number", "1111111111");
        params.put("to_number", "2222222222");
        params.put("limit", "2");
        params.put("offset", "0");
        parameters.put("end_time__gte","2015-07-26 11:47" ); // Filter out calls according to the time of completion.
            
        try {
            // Send the message
            CDRFactory cdrs = apis.getCDRs(params);
            System.out.println(cdrs);
        } catch (PlivoException e) {
            System.out.println(e.getLocalizedMessage());
        }

    } 
}

/*
Sample Output
Without filters

API ID : 804a4d08-b805-11e4-9107-22000afaaa90
Meta : 
previous=null
totalCount=55
offset=0
limit=20
next=/v1/Account/XXXXXXXXXXXXXXX/Call/?limit=20&offset=20

Objects : 
serverCode=null
billDuration=3
billedDuration=60
callDuration=3
totalAmount=0.00300
parentCallUUID=null
callDirection=outbound
toNumber=sip:test150108095716@phone.plivo.com
totalRate=0.00300
fromNumber=+1111111111
endTime=2015-02-17 22:46:37+05:30
callUUID=b4b71302-b6c8-11e4-95c7-fb0a4c731172
resourceUri=/v1/Account/XXXXXXXXXXXXXXX/Call/b4b71302-b6c8-11e4-95c7-fb0a4c731172/
error=null
apiId=null

serverCode=null
billDuration=0
billedDuration=0
callDuration=0
totalAmount=0.00000
parentCallUUID=null
callDirection=outbound
toNumber=sip:test150108095716@phone.plivo.com
totalRate=0.00300
fromNumber=+1111111111
endTime=2015-02-17 22:43:48+05:30
callUUID=4f489504-b6c8-11e4-9485-fb0a4c731172
resourceUri=/v1/Account/XXXXXXXXXXXXXXX/Call/4f489504-b6c8-11e4-9485-fb0a4c731172/
error=null
apiId=null

Filtering the response

API ID : 816e6b56-b805-11e4-9107-22000afaaa90
Meta : 
previous=null
totalCount=6
offset=0
limit=2
next=/v1/Account/XXXXXXXXXXXXXXX/Call/?from_number=1111111111&limit=2&offset=2&call_direction=outbound&to_number=919663489033

Objects : 
serverCode=null
billDuration=0
billedDuration=0
callDuration=0
totalAmount=0.00000
parentCallUUID=null
callDirection=outbound
toNumber=919663489033
totalRate=0.03570
fromNumber=+1111111111
endTime=2015-02-16 23:15:06+05:30
callUUID=7887466a-b603-11e4-8687-c73b3246dc2a
resourceUri=/v1/Account/XXXXXXXXXXXXXXX/Call/7887466a-b603-11e4-8687-c73b3246dc2a/
error=null
apiId=null

serverCode=null
billDuration=5
billedDuration=60
callDuration=5
totalAmount=0.03570
parentCallUUID=null
callDirection=outbound
toNumber=919663489033
totalRate=0.03570
fromNumber=+1111111111
endTime=2015-02-16 23:10:22+05:30
callUUID=d7187ee8-b602-11e4-a131-377ffe01233f
resourceUri=/v1/Account/XXXXXXXXXXXXXXX/Call/d7187ee8-b602-11e4-a131-377ffe01233f/
error=null
apiId=null


*/