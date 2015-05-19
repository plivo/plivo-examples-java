package plivoexample;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.xml.elements.Message;
import com.plivo.helper.xml.elements.PlivoResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.plivo.helper.util.*;

public class validateSignature extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String auth_token = "Your AUTH TOKEN";
        String signature = req.getHeader("X-Plivo-Signature");
        String url = req.getRequestURL().toString();
        String query_string = req.getQueryString();
        
        LinkedHashMap<String, String> post_params = new LinkedHashMap<String, String>();
        Map<String, Object> map = req.getParameterMap();
        for (String key: map.keySet()) {        
            String value = ((String[])map.get(key))[0];
            post_params.put(key, value);
        }
                
        LinkedHashMap<String, String> query_pairs = new LinkedHashMap<String, String>();
        if (query_string != null){
            String[] pairs = query_string.split("&");
            for (String pair : pairs) {
                int idx = pair.indexOf("=");
                query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
            }
            post_params.putAll(query_pairs);
        }
        try {
            Boolean isValid = XPlivoSignature.verify(url, post_params, signature, auth_token);
            System.out.println("Valid : " + isValid);
        } catch (PlivoException e) {
            e.printStackTrace();
        }
        
        String from_number = req.getParameter("From");
        String to_number = req.getParameter("To");
        String text = req.getParameter("Text");
        System.out.println("From : " + from_number + " To : " + to_number + " Text : " + text);       
    }

    public static void main(String[] args) throws Exception {
        String port = System.getenv("PORT");
        if(port==null)
            port ="8080";
        Server server = new Server(Integer.valueOf(port));
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new validateSignature()),"/receive_sms");
        server.start();
        server.join();
    }
}
