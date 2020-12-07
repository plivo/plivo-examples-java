// voicemail.java
import com.plivo.api.exceptions.PlivoXmlException;
import com.plivo.api.xml.Record;
import com.plivo.api.xml.Response;
import com.plivo.api.xml.Speak;
import static spark.Spark.*;


public class Record {
    public static void main(String[] args) {
        get("/voice_mail", (request, response) -> {
          Response resp = new Response()
                .children(
                        new Speak("Leave message. Press star key when done"),
                        new Record("http://foo.com/get_recording/")
                                .transcriptionUrl("http://foo.com/transcription_url/")
                                .transcriptionMethod("auto")
                                .transcriptionMethod("GET")
                                .maxLength(30),
                        new Speak("Recording not received.")
                );
        return resp.toXmlString();
    }
}

/*
Sample Output
<Response>
    <Speak>Leave your message after the tone</Speak>
    <Record maxLength="30" transcriptionUrl="http://foo.com/transcription_url/"
        action="http://foo.com/get_recording/" transcriptionMethod="GET" method="GET" transcriptionType="auto"/>
</Response>
*/

// recordAction.java
import static spark.Spark.*;

public class Record {
    public static void main(String[] args) {
        get("/get_recording", (request, response) -> {
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

Record URL : http://s3.amazonaws.com/recordings_2013/59581736-bb41-11e4-929e-782bcb0446c4.mp3 Record Duration : 10 Record ID : 59580836-bb41-11e4-929e-782bcb0446c4
*/

// transcription.java
import static spark.Spark.*;

public class Transcripton {
    public static void main(String[] args) {
        get("/transcripton_url", (request, response) -> {
            String transcription = request.queryParams("transcription");
            System.out.println("Transcription is : " + transcription);
            return "Transcripton Done";
        });
    }
}

/*
Sample Output

Transcription is : Hello
*/
