package plivoexample;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class recordingCallback extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        // The Callback URL of record api will return the B Leg record details.

        String record_url = req.getParameter("RecordUrl");
        String record_duration = req.getParameter("RecordingDuration");
        String record_id = req.getParameter("RecordingID");
        System.out.println("Record URL : " + record_url + " Record Duration : " + record_duration + " Record ID : " + record_id);
        resp.getWriter().print("Record URL : " + record_url + " Record Duration : " + record_duration + " Record ID : " + record_id);
    }
}

/*
Sample Output
Record URL : http://s3.amazonaws.com/recordings_2013/11112222-4444-11e4-a4c8-782bcb5bb8af.mp3 Recording Duration : 22 Recording ID : 693e61fd-8150-4091-a0f8-561d4a434288 
*/