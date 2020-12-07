import java.io.IOException;
import com.plivo.api.Plivo;
import com.plivo.api.exceptions.PlivoRestException;
import com.plivo.api.models.call.Call;
import com.plivo.api.models.base.ListResponse;

class CallList {
    public static void main(String [] args) {
        Plivo.init("YOUR_AUTH_ID","YOUR_AUTH_TOKEN");
        try {
          // Without Filters
            ListResponse<Call> response = Call.lister()
                    .limit(5)
                    .offset(0)
                    .list();

            System.out.println(response);
        } catch (PlivoRestException | IOException e) {
            e.printStackTrace();
        }

        // With filter
        try {
            ListResponse<Call> response = Call.lister()
                      .callDirection(CallDirection.OUTBOUND)
                      .limit(1)
                      .offset(0)
                      .list();

            System.out.println(response);
            } catch (PlivoRestException | IOException e) {
                  e.printStackTrace();
        }
    }
}

/*
Sample Output
Without filters
{
  "api_id": "0aee26f9-3700-11eb-b66d-0242ac110006",
  "meta": {
    "limit": 5,
    "next": "/v1/Account/MAXXXXXXXXXXXXX/Call/?limit=5\u0026offset=5",
    "offset": 0,
    "previous": null,
    "total_count": 57
  },
  "objects": [
    {
      "answer_time": "2020-12-05 18:02:32+05:30",
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
    },
    {
      "answer_time": "2020-12-04 09:09:03+05:30",
      "bill_duration": 1325,
      "billed_duration": 1335,
      "call_direction": "outbound",
      "call_duration": 1325,
      "call_state": "ANSWER",
      "call_uuid": "653f453f-bbdc-44a3-a254-4798f5059aa3",
      "conference_uuid": "068fb1b4-8100-4e65-bc0e-b358992c39e7",
      "end_time": "2020-12-04 09:31:07+05:30",
      "from_number": "+1111111111",
      "hangup_cause_code": 4000,
      "hangup_cause_name": "Normal Hangup",
      "hangup_source": "Callee",
      "initiation_time": "2020-12-04 09:08:58+05:30",
      "parent_call_uuid": null,
      "resource_uri": "/v1/Account/MAXXXXXXXXXXXXX/Call/653f453f-bbdc-44a3-a254-4798f5059aa3/",
      "to_number": "2222222222",
      "total_amount": "0.60520",
      "total_rate": "0.02720"
    },
    {
      "answer_time": "2020-12-04 09:06:21+05:30",
      "bill_duration": 36,
      "billed_duration": 45,
      "call_direction": "outbound",
      "call_duration": 36,
      "call_state": "ANSWER",
      "call_uuid": "d11a08f9-895c-4949-a835-4c3de4344d98",
      "conference_uuid": "b9a35ea6-eafe-4904-81e4-3635e976d492",
      "end_time": "2020-12-04 09:06:56+05:30",
      "from_number": "+1111111111",
      "hangup_cause_code": 4000,
      "hangup_cause_name": "Normal Hangup",
      "hangup_source": "Callee",
      "initiation_time": "2020-12-04 09:06:14+05:30",
      "parent_call_uuid": null,
      "resource_uri": "/v1/Account/MAXXXXXXXXXXXXX/Call/d11a08f9-895c-4949-a835-4c3de4344d98/",
      "to_number": "2222222222",
      "total_amount": "0.02040",
      "total_rate": "0.02720"
    },
    {
      "answer_time": "2020-12-04 09:04:21+05:30",
      "bill_duration": 42,
      "billed_duration": 45,
      "call_direction": "outbound",
      "call_duration": 42,
      "call_state": "ANSWER",
      "call_uuid": "5af7d420-243a-4066-ac18-ba8b7ec9ef86",
      "conference_uuid": "9957241e-0c0d-44e6-8eb3-0c094a5966d4",
      "end_time": "2020-12-04 09:05:03+05:30",
      "from_number": "+1111111111",
      "hangup_cause_code": 4000,
      "hangup_cause_name": "Normal Hangup",
      "hangup_source": "Callee",
      "initiation_time": "2020-12-04 09:04:17+05:30",
      "parent_call_uuid": null,
      "resource_uri": "/v1/Account/MAXXXXXXXXXXXXX/Call/5af7d420-243a-4066-ac18-ba8b7ec9ef86/",
      "to_number": "2222222222",
      "total_amount": "0.02040",
      "total_rate": "0.02720"
    },
    {
      "answer_time": "2020-12-04 09:00:18+05:30",
      "bill_duration": 34,
      "billed_duration": 45,
      "call_direction": "outbound",
      "call_duration": 34,
      "call_state": "ANSWER",
      "call_uuid": "124e3d1f-f6af-4fcc-8b74-3ec49306f3cf",
      "conference_uuid": "9e9d8e4c-6f34-4992-a63f-4bd06ea54b1d",
      "end_time": "2020-12-04 09:00:51+05:30",
      "from_number": "+1111111111",
      "hangup_cause_code": 4010,
      "hangup_cause_name": "End Of XML Instructions",
      "hangup_source": "Plivo",
      "initiation_time": "2020-12-04 09:00:13+05:30",
      "parent_call_uuid": null,
      "resource_uri": "/v1/Account/MAXXXXXXXXXXXXX/Call/124e3d1f-f6af-4fcc-8b74-3ec49306f3cf/",
      "to_number": "2222222222",
      "total_amount": "0.02040",
      "total_rate": "0.02720"
    }
  ]
}

// With filter
{
  "api_id": "90b64269-3700-11eb-9159-0242ac110007",
  "meta": {
    "limit": 1,
    "next": "/v1/Account/MAXXXXXXXXXXXXX/Call/?call_direction=outbound\u0026limit=1\u0026offset=1",
    "offset": 0,
    "previous": null,
    "total_count": 57
  },
  "objects": [
    {
      "answer_time": "2020-12-05 18:02:32+05:30",
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
  ]
}

*/
