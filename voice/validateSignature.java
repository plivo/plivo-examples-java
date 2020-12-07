import com.plivo.api.util.Utils;
import com.plivo.api.xml.Speak;
import com.plivo.api.xml.Response;
import static spark.Spark.*;

public class SignatureValidation {
    public static void main(String[] args) {
        get("/signature_validation", (request, response) -> {
            String auth_token = "AUTH_TOKEN";
            String signature = request.headers("X-Plivo-Signature-V3");
            String nonce = request.headers("X-Plivo-Signature-V3-Nonce");
            String url = request.url();
            String method =request.requestMethod();

            Boolean isValid = Utils.validateSignatureV3(url, nonce, signature, auth_token, method);
            System.out.println("Valid : " + isValid);

            if (isValid) {
                Response resp = new Response()
                        .children(
                                new Speak("Hello, Welcome to Plivo")
                        );
                return resp.toXmlString();
            }
            else
            {
                return "validation failed";
            }
        });

    }
}
/*
Sample Output
<Response>
    <Speak>Hello, Welcome to Plivo</Speak>
</Response>
*/
