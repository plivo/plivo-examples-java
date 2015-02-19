// ivrTree.java

package plivoexample;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.xml.elements.PlivoResponse;
import com.plivo.helper.xml.elements.Speak;

public class ivrTree extends HttpServlet{
    private static final long serialVersionUID = 1L;
    
    // This is the message that Plivo reads when the caller inputs a wrong number.
    String WRONG_INPUT_MESSAGE = "Sorry, it's wrong input.";
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String digit = req.getParameter("Digits");
        PlivoResponse response = new PlivoResponse();
        
        if (digit == "1")
        {
            // Read out text in English
            Speak speak = new Speak("This message is being read out in English");
            speak.setLanguage("en-GB");
            try{
                response.append(speak);
            } catch (PlivoException e) {
                e.printStackTrace();
            }
        }
        else if (digit == "2") 
        {
            // Read out text in French
            Speak speak = new Speak("Ce message est lu en français");
            speak.setLanguage("fr-FR");
            try{
                response.append(speak);
            } catch (PlivoException e) {
                e.printStackTrace();
            }
        }
        else if(digit == "3")
        {
            // Read out text in Russian
            Speak speak = new Speak("Это сообщение было прочитано в России");
            speak.setLanguage("ru-RU");
            try{
                response.append(speak);
            } catch (PlivoException e) {
                e.printStackTrace();
            }
        }
        else
        {
            // Wrong input
            Speak speak = new Speak(WRONG_INPUT_MESSAGE);
            try {
                response.append(speak);
            } catch (PlivoException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println(response.toXML());
        resp.addHeader("Content-Type", "text/xml");
        resp.getWriter().print(response.toXML());
    }
}