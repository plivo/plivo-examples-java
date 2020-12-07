import static spark.Spark.*;

public class RecordingAction {
    public static void main(String[] args) {
        get("/action_url", (request, response) -> {
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
