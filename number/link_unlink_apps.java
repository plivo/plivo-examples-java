import java.io.IOException;
import com.plivo.api.Plivo;
import com.plivo.api.exceptions.PlivoRestException;
import com.plivo.api.models.number.Number;
import com.plivo.api.models.number.NumberUpdateResponse;

class NumberUpdate {
    public static void main(String [] args) {
        Plivo.init("YOUR_AUTH_ID","YOUR_AUTH_TOKEN")
        // Link an application to a number.
        try {
            NumberUpdateResponse response = Number.updater("1111111111")
                    .appId("17371468466407823")
                    .update();

            System.out.println(response);
        } catch (PlivoRestException | IOException e) {
            e.printStackTrace();
        }
        // Unlink an application from a number.
        try {
            NumberUpdateResponse response = Number.updater("1111111111")
                    .appId("17371468466407823")
                    .update();

            System.out.println(response);
        } catch (PlivoRestException | IOException e) {
            e.printStackTrace();
        }
    }
}


/*
Link an application to a number.
Sample Output
{
  "api_id": "7163f484-35fd-11eb-9b3c-0242ac110003",
  "message": "changed"
}
*/



/*
Unlink an application from a number.
Sample Output
{
  "api_id": "7163f484-35fd-11eb-9b3c-0242ac110003",
  "message": "changed"
}
*/
