// confCallback.java
package plivoexample;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class confCallback extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String conf_action = req.getParameter("ConferenceAction");
        String conf_name = req.getParameter("ConferenceName");
        String conf_uuid = req.getParameter("ConferenceUUID");
        String conf_mem_id = req.getParameter("ConferenceMemberID");
        String call_uuid = req.getParameter("CallUUID");
        String record_url = req.getParameter("RecordUrl");
        String record_id = req.getParameter("RecordingID");
        System.out.println("Conference Action : " + conf_action + " Conference Name : " + conf_name + " Conference UUID : " + conf_uuid + " Conference Member ID : " + conf_mem_id + 
                            " Call UUID : " + call_uuid + " Record URL : " + record_url + " Record ID : " + record_id);
        resp.getWriter().print("Conference Action : " + conf_action + "Conference Name : " + conf_name + " Conference UUID : " + conf_uuid + " Conference Member ID : " + conf_mem_id + 
                " Call UUID : " + call_uuid + " Record URL : " + record_url + " Record ID : " + record_id);
    }
}