import java.io.IOException;
import com.plivo.api.Plivo;
import com.plivo.api.exceptions.PlivoRestException;
import com.plivo.api.models.message.Message;
import com.plivo.api.models.base.ListResponse;


class GetAllMessageList
{
    public static void main(String [] args)
    {
        Plivo.init("YOUR_AUTH_ID", "YOUR_AUTH_TOKEN");
        try
        {
            ListResponse<Message> response = Message.lister()
                    .limit(5)
                    .offset(0)
                    .list();
            System.out.println(response);
            // Prints only the message_uuid
            System.out.println(response.getMessageUuid());
        }
        catch (PlivoRestException | IOException e)
        {
            e.printStackTrace();
        }
    }
}

// Filtering the records
class GetAllMessageLists
      {
        public static void main(String [] args)
          {
              Plivo.init("YOUR_AUTH_ID","YOUR_AUTH_TOKEN");
                try
                {
                    ListResponse<Message> response = Message.lister()
                            .messageState(MessageState.DELIVERED)
                            .limit(5)
                            .offset(0)
                            .list();
                    System.out.println(response);
                }
                catch (PlivoRestException | IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

// Sample Output without filter
/*
{
  "api_id": "61c9103e-355a-11eb-bc7f-0242ac110004",
  "meta": {
    "limit": 5,
    "next": "/v1/Account/MAXXXXXXXXXXX/Message/?limit=5&offset=5",
    "offset": 0,
    "previous": null,
    "total_count": 89
  },
  "objects": [
    {
      "error_code": "450",
      "from_number": "111111111111",
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
    },
    {
      "error_code": "450",
      "from_number": "111111111111",
      "message_direction": "outbound",
      "message_state": "failed",
      "message_time": "2020-12-03 16:42:26.700284+05:30",
      "message_type": "sms",
      "message_uuid": "6d006e9a-3558-11eb-af78-0242ac110007",
      "resource_uri": "/v1/Account/MAXXXXXXXXXXX/Message/87aeb954-3558-11eb-8618-0242ac110003/",
      "to_number": "2222222222",
      "total_amount": "0.00000",
      "total_rate": "0.00000",
      "units": 1
    },
    {
      "error_code": "450",
      "from_number": "111111111111",
      "message_direction": "outbound",
      "message_state": "failed",
      "message_time": "2020-12-03 16:42:26.700284+05:30",
      "message_type": "sms",
      "message_uuid": "6cff70da-3558-11eb-af78-0242ac110007",
      "resource_uri": "/v1/Account/MAXXXXXXXXXXX/Message/87aeb954-3558-11eb-8618-0242ac110003/",
      "to_number": "2222222222",
      "total_amount": "0.00000",
      "total_rate": "0.00000",
      "units": 1
    },
    {
      "error_code": "450",
      "from_number": "111111111111",
      "message_direction": "outbound",
      "message_state": "failed",
      "message_time": "2020-12-03 01:20:04.724769+05:30",
      "message_type": "sms",
      "message_uuid": "929c145a-34d7-11eb-b447-0242ac110004",
      "resource_uri": "/v1/Account/MAXXXXXXXXXXX/Message/87aeb954-3558-11eb-8618-0242ac110003/",
      "to_number": "2222222222",
      "total_amount": "0.00000",
      "total_rate": "0.00000",
      "units": 1
    },
    {
      "error_code": "450",
      "from_number": "111111111111",
      "message_direction": "outbound",
      "message_state": "failed",
      "message_time": "2020-12-03 01:18:15.957191+05:30",
      "message_type": "sms",
      "message_uuid": "51c77690-34d7-11eb-a029-0242ac110004",
      "resource_uri": "/v1/Account/MAXXXXXXXXXXX/Message/87aeb954-3558-11eb-8618-0242ac110003/",
      "to_number": "2222222222",
      "total_amount": "0.00000",
      "total_rate": "0.00000",
      "units": 1
    }
  ]
}

Sample Ouput with filter

INFO: {
  "api_id": "f78b91a6-3559-11eb-9aed-0242ac110003",
  "meta": {
    "limit": 5,
    "next": null,
    "offset": 0,
    "previous": null,
    "total_count": 3
  },
  "objects": [
    {
      "error_code": "000",
      "from_number": "111111111111",
      "message_direction": "outbound",
      "message_state": "delivered",
      "message_time": "2020-12-03 01:17:14.458719+05:30",
      "message_type": "sms",
      "message_uuid": "2d1f8aee-34d7-11eb-b447-0242ac110004",
      "resource_uri": "/v1/Account/MAXXXXXXXXXXX/Message/65cf8142-34d6-11eb-9129-0242ac110003/",
      "to_number": "2222222222",
      "total_amount": "0.00901",
      "total_rate": "0.00901",
      "units": 1
    },
    {
      "error_code": "000",
      "from_number": "111111111111",
      "message_direction": "outbound",
      "message_state": "delivered",
      "message_time": "2020-12-03 01:13:38.306355+05:30",
      "message_type": "sms",
      "message_uuid": "ac494f04-34d6-11eb-a029-0242ac110004",
      "resource_uri": "/v1/Account/MAXXXXXXXXXXX/Message/65cf8142-34d6-11eb-9129-0242ac110003/",
      "to_number": "2222222222",
      "total_amount": "0.00901",
      "total_rate": "0.00901",
      "units": 1
    },
    {
      "error_code": "000",
      "from_number": "111111111111",
      "message_direction": "outbound",
      "message_state": "delivered",
      "message_time": "2020-12-03 01:11:40.067586+05:30",
      "message_type": "sms",
      "message_uuid": "65cf8142-34d6-11eb-9129-0242ac110003",
      "resource_uri": "/v1/Account/MAXXXXXXXXXXX/Message/65cf8142-34d6-11eb-9129-0242ac110003/",
      "to_number": "2222222222",
      "total_amount": "0.00901",
      "total_rate": "0.00901",
      "units": 1
    }
  ]
}
*/
