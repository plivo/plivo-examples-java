package controllers;

import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;

import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.xml.elements.Message;
import com.plivo.helper.xml.elements.PlivoXML;

public class Sms extends Controller {

	/**
	 * Receive message from plivo and log it.
	 * 
	 * @param To
	 *            destination number
	 * @param From
	 *            sender number
	 * @param Text
	 *            message text
	 * @param MessageUUID
	 *            message UUID
	 * @param Type
	 *            message type
	 * @return
	 */
	public static Result receive(String To, String From, String Text,
			String MessageUUID, String Type) {
		Logger.debug("Message Received.");
		Logger.debug("\tFrom = " + From + ". To = " + To);
		Logger.debug("\tMessage UUID = " + MessageUUID + ". Type = " + Type);
		Logger.debug("\ttext = " + Text);
		return ok();
	}

	/**
	 * Receive message from plivo and give reply with same content.
	 * 
	 * @param To
	 *            destination number
	 * @param From
	 *            sender number
	 * @param Text
	 *            message text
	 * @param MessageUUID
	 *            message UUID
	 * @param Type
	 *            message type
	 * @return
	 */
	public static Result reply(String To, String From, String Text,
			String MessageUUID, String Type) {
		Logger.debug("Message Received. We will reply.");
		Logger.debug("\tFrom = " + From + ". To = " + To);
		Logger.debug("\tMessage UUID = " + MessageUUID + ". Type = " + Type);
		Logger.debug("\ttext = " + Text);

		PlivoXML plivoXML = new PlivoXML();
		Message m = new Message(Text, To, From);
		m.setType("sms");
		String str;
		try {
			plivoXML.append(m);
			str = plivoXML.toXML();
		} catch (PlivoException pe) {
			str = "Failed to generate plivo XML";
		}
		Logger.debug("Our response=" + str);
		return ok(str).as("text/xml");
	}

	public static Result callback() {
		return ok();
	}

}
