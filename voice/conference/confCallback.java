// confCallback.java
import static spark.Spark.*;

public class ConferenceCallback {
    public static void main(String[] args) {
        get("/conf_callback_url", (request, response) -> {
            // Unique name of the conference
            String conference_name = request.queryParams("ConferenceName");
            // The action occured within conference
            String conference_action = req.getParameter("ConferenceAction");
            // Unique UUID of the conference
            String conference_uuid = request.queryParams("ConferenceUUID");
            // Member ID of the user entered into the conference
            String conference_member_id = request.queryParams("ConferenceMemberID");
            // Recorded URL of the conference
            String record_url = request.queryParams("RecordUrl");
            // Recording ID of the conference
            String record_id = request.queryParams("RecordingID");
            // Duration of recording in seconds.
            String record_duration = request.queryParams("RecordingDuration");
            // Duration of recording in Milliseconds
            String record_duration_ms = request.queryParams("RecordingDurationMs");
            // When the recording started (epoch time UTC) in milliseconds
            String record_start_ms = request.queryParams("RecordingStartMs");
            // When the recording ended (epoch time UTC) in milliseconds.
            String record_end_ms = request.queryParams("RecordingEndMs");
            // Print the message
            System.out.println(
            "Conference Name : " + conference_name + "Conference Action : " + conference_action +
            " Conference UUID : " + conference_uuid + " Conference Member ID : " + conference_member_id +
            " Record URL : " + record_url + " Record ID : " + record_id+
            " Record Duration : " + record_duration+ " Record Duration(ms) : " + record_duration_ms+
            " Record start duration(ms) : " + record_start_ms+ " Record end duration(ms) : " + record_end_ms);
        });
    }
}
