//dialStatusReporting.java
import static spark.Spark.*;
import com.plivo.api.xml.Dial;
import com.plivo.api.xml.Number;
import com.plivo.api.xml.Response;

public class dialstatus {
    public static void main(String[] args) {
        get("/dialstatus/", (request, response) -> {
            response.type("application/xml");
            Response resp = new Response()
                .children(
                        new Dial()
                            .action("http://foo.com/dialstatus/action/")
                            .method("GET")
                            .redirect(true)
                            .children(
                                    new Number("+15671234567")
                            )
                );
            return resp.toXmlString();
        });
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
        get("/dialstatus/action/", (request, response) -> {
            String status = request.queryParams("Status");
            String aleg = request.queryParams("DialALegUUID");
            String bleg = request.queryParams("DialBLegUUID");
            System.out.println("Status : " + status + " ALeg UUID : " + aleg + " Bleg UUID : " + bleg);
            response.raw().getWriter().print("Status : " + status + " ALeg UUID : " + aleg + " Bleg UUID : " + bleg);
            return "done";
        });
    }
}
/*
Sample Output

Status = completed , Aleg UUID = 36100ddc-aed6-11e4-98c9-075c56ad58a0 , Bleg UUID = 38098730-aed6-11e4-9915-075c56ad58a0
*/
