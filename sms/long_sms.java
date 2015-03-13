package com.plivo.test;

import java.util.LinkedHashMap;

import com.plivo.helper.api.client.*;

import com.plivo.helper.api.response.message.MessageResponse;
import com.plivo.helper.exception.PlivoException;

public class LongMessage {

    public static void main(String[] args) {
        
        String authId = "Your AUTH_ID";
        String authToken = "Your AUTH_TOKEN";

        RestAPI api = new RestAPI(authId, authToken, "v1");

        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("src", "1111111111"); // Sender's phone number with country code
        parameters.put("dst", "2222222222"); // Receiver's phone number with country code
        parameters.put("text", "This randomly generated text can be used in your layout "
                + "(webdesign , websites, books, posters ... ) for free. This text is entirely free of law. "
                + "Feel free to link to this site by using the image below or"
                + " by making a simple text link"); // Your SMS text message

        /*parameters.put("text", "このランダムに生成されたテキストは、自由のためのあなたのレイアウト（"
                + "ウェブデザイン、ウェブサイト、書籍、ポスター...）で使用することができます。このテキストは、"
                + "法律の完全に無料です。下の画像を使用して、または単純なテキストリンクを作ることで、"
                + "このサイトへのリンクフリーです"); // Your SMS text message - English
        */
        /*parameters.put("text", "Ce texte généré aléatoirement peut-être utilisé dans vos maquettes"
                + " (webdesign, sites internet,livres, affiches...) gratuitement. "
                + "Ce texte est entièrement libre de droit. N'hésitez pas à faire un "
                + "lien sur ce site en utilisant l'image ci-dessous" 
                + "ou en faisant un simple lien texte"); // Your SMS text message - French
        */
        
        try {
            // Send the Message
            MessageResponse msgResponse = api.sendMessage(parameters);

            // Print the response
            System.out.println(msgResponse);
            // Send the Api ID
            System.out.println("Api ID : " + msgResponse.apiId);
            // Send the Response Message
            System.out.println("Message : " + msgResponse.message);
            if (msgResponse.serverCode == 202) {
                // Print the Message UUID
                String uuid = msgResponse.messageUuids.get(0).toString();
                System.out.println("Message UUID : " + uuid);

                RestAPI apis = new RestAPI(authId, authToken, "v1");

                LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
                params.put("record_id", uuid); // Message UUID for which the details have to be retrieved
                
                // Get the details of the sent message
                Message msg = apis.getMessage(params);

                // Print the number of units
                System.out.println("Units : " + msg.units);
            } else {
                System.out.println(msgResponse.error); 
            }
        } catch (PlivoException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}

// Sample Output
/*
MessageResponse [
    serverCode=202, 
    message=message(s) queued, 
    messageUuids=[dfa86ad6-c715-11e4-8672-22000aff09d1], 
    error=null, 
    apiId=df8ede22-c715-11e4-b932-22000ac50fac
]
Api ID : df8ede22-c715-11e4-b932-22000ac50fac
Message : message(s) queued
Message UUID : dfa86ad6-c715-11e4-8672-22000aff09d1

// Output for English
Units : 2

// Output for Japanes
Units : 3

// Output for French
Units : 5
*/