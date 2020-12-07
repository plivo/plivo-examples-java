package com.plivo.api.samples.call;

import java.io.IOException;
import com.plivo.api.Plivo;
import com.plivo.api.exceptions.PlivoRestException;
import com.plivo.api.models.call.Call;

class CallDelete {
    public static void main(String [] args) {
        Plivo.init("YOUR_AUTH_ID","YOUR_AUTH_TOKEN");
        try {
            Call.deleter("eba53b9e-8fbd-45c1-9444-696d2172fbc8") // UUID of the call to hangup
                .delete();

            System.out.println("Deleted successfully.");
        } catch (PlivoRestException | IOException e) {
            e.printStackTrace();
        }
    }
}

/*
Sample Output
serverCode=204
message=no response
error=null
apiId=unknown
*/
