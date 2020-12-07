// confAction.java
import static spark.Spark.*;

public class ConferenceAction {
    public static void main(String[] args) {
        get("/conf_action_url", (request, response) -> {
            // Unique name of the conference
            String conference_name = request.queryParams("ConferenceName");
            // Unique UUID of the conference
            String conference_uuid = request.queryParams("ConferenceUUID");
            // Member ID of the user entered into the conference
            String conference_member_id = request.queryParams("ConferenceMemberID");
            // Recorded URL of the conference
            String record_url = request.queryParams("RecordUrl");
            // Recording ID of the conference
            String record_id = request.queryParams("RecordingID");
            // Print the message
            System.out.println("Conference Name : " + conference_name + " Conference UUID : " + conference_uuid + " Conference Member ID : " + conference_member_id + " Record URL : " + record_url + " Record ID : " + record_id);
        });
    }
}
