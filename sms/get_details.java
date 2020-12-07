import java.io.IOException;
import com.plivo.api.Plivo;
import com.plivo.api.exceptions.PlivoRestException;
import com.plivo.api.models.message.Message;

class MessageGet
{
    public static void main(String [] args)
    {
        Plivo.init("YOUR_AUTH_ID","YOUR_AUTH_TOKEN");
        try
        {
            Message response = Message.getter("87aeb954-3558-11eb-8618-0242ac110003") // Message UUID of the details to be fetched
                    .get();
            System.out.println(response);
        }
        catch (PlivoRestException | IOException e)
        {
            e.printStackTrace();
        }
    }
}
// Sample Output
/*
{
  "api_id": "c02284c6-355a-11eb-b46a-0242ac110004",
  "error_code": "450",
  "from_number": "11111111111",
  "message_direction": "outbound",
  "message_state": "failed",
  "message_time": "2020-12-03 16:43:11.470139+05:30",
  "message_type": "sms",
  "message_uuid": "87aeb954-3558-11eb-8618-0242ac110003",
  "resource_uri": "/v1/Account/MAXXXXXXXXXXX/Message/87aeb954-3558-11eb-8618-0242ac110003/",
  "to_number": "2222222222",
  "total_amount": "0.00000",
  "total_rate": "0.00000",
  "units": 1
}
*/
