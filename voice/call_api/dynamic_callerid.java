// Set te caller ID using Dial XML

package plivoexample;

import java.io.IOException;

import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.xml.elements.Dial;
import com.plivo.helper.xml.elements.Number;
import com.plivo.helper.xml.elements.PlivoResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class dial extends HttpServlet {
    private static final long serialVersionUID = 1L;    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        PlivoResponse response = new PlivoResponse();
        Dial dial = new Dial();
        dial.setCallerId("1111111111");
        Number num = new Number("2222222222");
        
        try {
            dial.append(num);
            response.append(dial);
            System.out.println(response.toXML());
            resp.addHeader("Content-Type", "text/xml");
            resp.getWriter().print(response.toXML());;
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
        context.addServlet(new ServletHolder(new dial()),"/dial");
        server.start();
        server.join();
    }
}

/*
Sample Output
<Response>
    <Dial callerId="1111111111">
        <Number>2222222222</Number>
    </Dial>
</Response>
*/

// Set the caller ID using Call API

package com.plivo.test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;

import com.plivo.helper.api.client.*;
import com.plivo.helper.api.response.call.Call;
import com.plivo.helper.exception.PlivoException;

public class App {
    public static void main(String[] args) throws IllegalAccessException {

        String auth_id = "Your AUTH_ID";
        String auth_token = "Your AUTH_TOKEN";
        
        RestAPI api = new RestAPI(auth_id, auth_token, "v1");
        

        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("to","2222222222"); // The phone numer to which the all has to be placed
        parameters.put("from","1111111111"); // The phone number to be used as the caller id
        parameters.put("answer_url","https://dry-fortress-4047.herokuapp.com/speak"); // The URL invoked by Plivo when the outbound call is answered
        parameters.put("answer_method","GET"); // method to invoke the answer_url

        try {
           Call resp = api.makeCall(parameters);
           System.out.println(getFields(resp));           
        }catch (PlivoException e){  
           System.out.println(e.getLocalizedMessage());
        }
    }

    public static String getFields(Object obj) throws IllegalAccessException {
        StringBuffer buffer = new StringBuffer();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field f : fields) {
          if (!Modifier.isStatic(f.getModifiers())) {
            f.setAccessible(true);
            Object value = f.get(obj);
            buffer.append(f.getName());
            buffer.append("=");
            buffer.append("" + value);
            buffer.append("\n");
          }
        }
        return buffer.toString();
    }
}

/*
Sample Output
serverCode=201
message=call fired
requestUUID=df0657fb-a275-4c36-bdd3-f82e6a6c3ed0
apiId=14e2c8e2-b602-11e4-af95-22000ac54c79
error=null
*/

