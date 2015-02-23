package plivoexample;

import java.io.IOException;

import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.xml.elements.PlivoResponse;
import com.plivo.helper.xml.elements.Speak;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class textToSpeech extends HttpServlet {
    private static final long serialVersionUID = 1L;    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        PlivoResponse response = new PlivoResponse();
        Speak spk1 = new Speak("Hello, Welcome to Plivo");
        spk1.setLanguage("en-GB");
        spk1.setVoice("MAN");
        Speak spk2 = new Speak("Ce texte généré aléatoirement peut-être utilisé dans vos maquettes");
        spk2.setLanguage("fr-FR");
        spk1.setVoice("WOMAN");
        Speak spk3 = new Speak("Это случайно сгенерированный текст может быть использован в макете");
        spk3.setLanguage("ru-RU");
        spk1.setVoice("WOMAN");
        
        try {
            response.append(spk1);
            response.append(spk2);
            response.append(spk3);
            System.out.println(response.toXML());
            resp.addHeader("Content-Type", "text/xml");
            resp.getWriter().print(response.toXML());
        } catch (PlivoException e) {
            e.printStackTrace();
        }       
    }

    public static void main(String[] args) throws Exception {
        String port = System.getenv("PORT");
        if(port==null)
            port ="8000";
        Server server = new Server(Integer.valueOf(port));
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new textToSpeech()),"/speech");
        server.start();
        server.join();
    }
}

/*
Sample Output
<Response>
    <Speak voice="WOMAN" language="en-GB">Hello, Welcome to Plivo</Speak>
    <Speak language="fr-FR">Ce texte généré aléatoirement peut-être utilisé dans vos maquettes</Speak>
    <Speak language="ru-RU">Это случайно сгенерированный текст может быть использован в макете</Speak>
</Response>
*/