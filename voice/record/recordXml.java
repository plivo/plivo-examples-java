import static spark.Spark.*;

public class Record {
    public static void main(String[] args) {
        get("/Record", (request, response) -> {
          // Generate a Record XML
          // The recorded file will be sent to the 'action' URL
          Response resp = new Response()
              .children(
                      new Record("http://foo.com/action_url/") // action_url
                        .method("GET")
                        .callbackUrl("http://foo.com/callback_url/")
                        .callbackMethod("GET")
              );
      return resp.toXmlString());
    }
}

/*
Sample Output
<Response>
<Record callbackUrl="http://foo.com/callback_url/" callbackMethod="GET"
    action="http://foo.com/action_url/" method="GET"/>
</Response>
*/

// recordAction.java
import static spark.Spark.*;

public class Record {
    public static void main(String[] args) {
        get("/action_url", (request, response) -> {
            // Action URL example
            String record_url = request.queryParams("RecordUrl");
            String record_duration = request.queryParams("RecordingDuration");
            String record_id = request.queryParams("RecordingID");
            System.out.println("Record URL : " + record_url + " Record Duration : " + record_duration + " Record ID : " + record_id);
            return "Recording Received";
        });
    }
}
/*
Sample Output
Record URL : http://s3.amazonaws.com/recordings_2013/55556666-7777-11e4-a4c8-782bcb5bb8af.mp3 Recording Duration : 27 Recording ID : daddbf04-9585-11e4-a4c8-782bcb5bb8af
*/

//recordingCallback.java
import static spark.Spark.*;

public class Record {
    public static void main(String[] args) {
        get("/callback_url", (request, response) -> {

            // The Callback URL of record api will return the B Leg record details.
            String record_url = request.queryParams("RecordUrl");
            String record_duration = request.queryParams("RecordingDuration");
            String record_id = request.queryParams("RecordingID");
            System.out.println("Record URL : " + record_url + " Record Duration : " + record_duration + " Record ID : " + record_id);
            return "Recording Received";
        });
    }
}

/*
Sample Output
Record URL : http://s3.amazonaws.com/recordings_2013/11112222-4444-11e4-a4c8-782bcb5bb8af.mp3 Recording Duration : 22 Recording ID : 693e61fd-8150-4091-a0f8-561d4a434288
*/
