// basicWait.java
import com.plivo.api.xml.Response;
import com.plivo.api.xml.Speak;
import com.plivo.api.xml.Wait;
import static spark.Spark.*;

public class Wait {
    public static void main(String[] args) {
        get("/wait", (request, response) -> {
            Response resp = new Response()
                    .children(
                            new Speak("I will wait 6 seconds starting now."),
                            new Wait()
                                    .length(6),
                            new Speak("I just waited 6 seconds.")
                    );
            return resp.toXmlString();
        });
    }
}
/*
Sample Output
<Response>
   <Speak>I will wait 6 seconds starting now.</Speak>
   <Wait length="6" />
   <Speak>I just waited 6 seconds.</Speak>
</Response>
*/

// delayedWait.java
import com.plivo.api.xml.Response;
import com.plivo.api.xml.Speak;
import com.plivo.api.xml.Wait;

import static spark.Spark.*;

public class DelayedWait {
    public static void main(String[] args) {
        get("/delayed_wait", (request, response) -> {
            Response resp = new Response()
                    .children(
                            new Wait()
                                    .length(10),
                            new Speak("Hello after 10 seconds")

                    );
            return resp.toXmlString();
        });
    }
}

/*
Sample Output
<Response>
   <Wait length="10" />
   <Speak>Hello after 10 seconds</Speak>
</Response>
*/

// beepDetection.java
import com.plivo.api.xml.Response;
import com.plivo.api.xml.Speak;
import com.plivo.api.xml.Wait;
import static spark.Spark.*;

public class BeepDetection {
    public static void main(String[] args) {
        get("/beep_detection", (request, response) -> {
            Response resp = new Response()
                    .children(
                            new Wait()
                                    .beep(true)
                                    .length(10),
                            new Speak("Hello, welcome to Plivo")
                    );
            return resp.toXmlString();
        });
    }
}

/*
Sample Output
<Response>
   <Wait length="10" beep="true" />
   <Speak>Hello, welcome to Plivo</Speak>
</Response>
*/
