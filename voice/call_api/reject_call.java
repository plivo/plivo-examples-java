// Example for xml - hangup a call after a minute
import com.plivo.api.exceptions.PlivoXmlException;
import com.plivo.api.xml.Hangup;
import com.plivo.api.xml.Response;
import com.plivo.api.xml.Speak;


class HangupACallAfterAMinute {
    public static void main(String[] args) throws PlivoXmlException {
        Response response = new Response()
                .children(
                        new Hangup()
                                .reason("busy")
                                .schedule(60)
                );
        System.out.println(response.toXmlString());
    }
}

/*
Sample Output
<Response>
   <Hangup schedule="60" reason="busy" />
</Response>
*/
