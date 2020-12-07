import java.io.IOException;
import com.plivo.api.Plivo;
import com.plivo.api.exceptions.PlivoRestException;
import com.plivo.api.models.call.Call;

class CallGet {
    public static void main(String [] args) {
        Plivo.init("YOUR_AUTH_ID","YOUR_AUTH_TOKEN");
        try {
            Call response = Call.getter("34a17f97-b594-4018-9615-75b11f427b32")
                    .get();

            System.out.println(response);
        } catch (PlivoRestException | IOException e) {
            e.printStackTrace();
        }
    }
}

/*
Sample Output

{
  "answer_time": "2020-12-05 18:02:32+05:30",
  "api_id": "f75ef01f-3700-11eb-b66d-0242ac110006",
  "bill_duration": 4,
  "billed_duration": 15,
  "call_direction": "outbound",
  "call_duration": 4,
  "call_state": "ANSWER",
  "call_uuid": "34a17f97-b594-4018-9615-75b11f427b32",
  "conference_uuid": null,
  "end_time": "2020-12-05 18:02:36+05:30",
  "from_number": "+1111111111",
  "hangup_cause_code": 4000,
  "hangup_cause_name": "Normal Hangup",
  "hangup_source": "Answer XML",
  "initiation_time": "2020-12-05 18:02:26+05:30",
  "parent_call_uuid": null,
  "resource_uri": "/v1/Account/MAXXXXXXXXXXXXX/Call/34a17f97-b594-4018-9615-75b11f427b32/",
  "to_number": "2222222222",
  "total_amount": "0.00680",
  "total_rate": "0.02720"
}

*/
