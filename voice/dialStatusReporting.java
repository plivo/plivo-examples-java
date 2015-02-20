//dialStatusReporting.java
package plivoexample;

import java.io.IOException;

import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.xml.elements.Dial;
import com.plivo.helper.xml.elements.Number;
import com.plivo.helper.xml.elements.PlivoResponse;
import com.plivo.helper.xml.elements.Speak;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class dialStatusReporting extends HttpServlet {
    private static final long serialVersionUID = 1L;    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        PlivoResponse response = new PlivoResponse();
        Speak speak = new Speak("Connecting your call..");
        Dial dial = new Dial();
        dial.setAction("https://dry-fortress-4047.herokuapp.com/dial_status");
        dial.setMethod("GET");
        Number num = new Number("1111111111");
        
        try {
            response.append(speak);
            dial.append(num);
            response.append(dial);
            System.out.println(response.toXML());
            resp.addHeader("Content-Type", "text/xml");
            resp.getWriter().print(response.toXML());;
        } catch (PlivoException e) {
            e.printStackTrace();
        }       
    }
}

/*
Sample Output
<Response>
    <Speak>Connecting your call..</Speak>
    <Dial action="https://dry-fortress-4047.herokuapp.com/dial_status" method="GET">
            <Number>1111111111</Number>
    </Dial>
</Response>
*/

// dialStatus.java
// After completion of the call, Plivo will report back the status to the action URL in the Dial XML.
package plivoexample;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class dialStatus extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String status = req.getParameter("Status");
        String aleg = req.getParameter("DialALegUUID");
        String bleg = req.getParameter("DialBLegUUID");
        System.out.println("Status : " + status + " ALeg UUID : " + aleg + " BLeg UUID : " + bleg);
        resp.getWriter().print("Status : " + status + " ALeg UUID : " + aleg + " BLeg UUID : " + bleg);
    }
}

/*
Sample Output

Status = completed , Aleg UUID = 36100ddc-aed6-11e4-98c9-075c56ad58a0 , Bleg UUID = 38098730-aed6-11e4-9915-075c56ad58a0
*/