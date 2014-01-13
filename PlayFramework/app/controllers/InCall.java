package controllers;

import java.util.HashMap;

import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;

import com.plivo.helper.PlivoRestConf;
import com.plivo.helper.exception.APIException;
import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.resource.Call;
import com.plivo.helper.response.CallRecordResponse;
import com.plivo.helper.xml.elements.Conference;
import com.plivo.helper.xml.elements.Dial;
import com.plivo.helper.xml.elements.GetDigits;
import com.plivo.helper.xml.elements.Number;
import com.plivo.helper.xml.elements.Play;
import com.plivo.helper.xml.elements.PlivoXML;
import com.plivo.helper.xml.elements.Record;
import com.plivo.helper.xml.elements.Speak;
import com.plivo.helper.xml.elements.User;

public class InCall extends Controller {
	private static String generatePlayMp3Xml() {
		String xml;
		PlivoXML plivoXML = new PlivoXML();
		Play play = new Play("https://s3.amazonaws.com/plivocloud/Trumpet.mp3");
		play.setLoop(5);
		try {
			plivoXML.append(play);
			xml = plivoXML.toXML();
		} catch (PlivoException pe) {
			Logger.error("Failed while generating play mp3 xml:"
					+ pe.getMessage());
			xml = "Failed to generate plivo XML";
		}
		return xml;
	}

	/**
	 * Give response to play mp3 file
	 * 
	 * @return
	 */
	public static Result playMp3() {
		String resp = InCall.generatePlayMp3Xml();
		Logger.debug("response=" + resp);
		return ok(resp).as("text/xml");
	}

	/**
	 * Play some text and choose language
	 * 
	 * @return
	 */
	public static Result chooseLang() {
		String resp;
		PlivoXML plivoXML = new PlivoXML();

		GetDigits gd = new GetDigits();

		gd.setAction("http://safe-stream-4972.herokuapp.com/incall/chooselang/getdigits");
		gd.setMethod("GET");
		gd.setTimeout(20);
		gd.setNumDigits(1);

		Speak speak = new Speak(
				"Welcome to Plivo Demo app. Press 1 to hear welcome in english, press 2 to hear it in french");

		Speak timeout = new Speak(
				"Sorry, I didn't catch that. Please hangup the call and try again later.");
		try {
			gd.append(speak);
			plivoXML.append(gd);
			plivoXML.append(timeout);
			resp = plivoXML.toXML();
		} catch (PlivoException pe) {
			resp = "<response></response>";
		}
		Logger.debug("response = " + resp);
		return ok(resp).as("text/xml");
	}

	/**
	 * Generate Speak element with language option based on input digits.
	 * 
	 * @param Digits
	 * @return
	 */
	public static Result chooseLangGetDigits(String Digits) {
		String resp;
		PlivoXML plivoXML;
		Speak s;

		if ("1".equals(Digits)) {
			s = new Speak("Welcome");// by default it will use english
		} else if ("2".equals(Digits)) {
			s = new Speak("bienvenu");
			s.setLanguage("fr-FR");
		} else {
			s = new Speak("Invalid digits. Only 1 and 2 are valid digits");
		}

		plivoXML = new PlivoXML();
		try {
			plivoXML.append(s);
			resp = plivoXML.toXML();
		} catch (PlivoException pe) {
			resp = "<response></response>";
		}
		Logger.debug("response = " + resp);
		return ok(resp).as("text/xml");
	}

	/**
	 * Forward call to another plivo endpoint.
	 * 
	 * @return
	 */
	public static Result forward() {
		String resp;
		PlivoXML plivoXML;
		Dial dial;
		User user;

		plivoXML = new PlivoXML();
		dial = new Dial();
		user = new User("sip:ch130329160035@phone.plivo.com");
		try {
			dial.append(user);
			plivoXML.append(dial);
			resp = plivoXML.toXML();
		} catch (PlivoException pe) {
			resp = "<response></response>";
		}
		Logger.debug("response = " + resp);
		return ok(resp).as("text/xml");
	}

	/**
	 * Voice mail record demo
	 * 
	 * @return
	 */
	public static Result recVoiceMail() {
		String resp;
		PlivoXML plivoXML;
		Speak s;
		Record rec;

		plivoXML = new PlivoXML();
		s = new Speak(
				"Please leave a message after the beep. Press the star key when done.");

		rec = new Record();
		rec.setCallbackUrl("http://safe-stream-4972.herokuapp.com/incall/recvoicemail/callback");
		rec.setCallbackMethod("GET");
		rec.setTranscriptionUrl("http://safe-stream-4972.herokuapp.com/incall/recvoicemail/transcript");
		rec.setTranscriptionMethod("GET");

		try {
			plivoXML.append(s);
			plivoXML.append(rec);
			resp = plivoXML.toXML();
		} catch (PlivoException pe) {
			resp = "<response></response>";
		}
		Logger.debug("response = " + resp);
		return ok(resp).as("text/xml");
	}

	/**
	 * 
	 * @param RecordUrl
	 * @param RecordingID
	 * @return
	 */
	public static Result recVoiceMailCallback(String RecordUrl,
			String RecordingID) {
		Logger.debug("Record Voice Mail Action:");
		Logger.debug("\t\tRecordUrl = " + RecordUrl);
		Logger.debug("\t\tRecordingID = " + RecordingID);
		return ok();
	}

	/**
	 * Record voice mail transcript
	 * 
	 * @param transcription
	 *            voice mail transcription
	 * @return
	 */
	public static Result recVoiceMailTranscript(String transcription) {
		Logger.debug("recVoiceMailTranscript");
		Logger.debug("\t\ttranscription = " + transcription);
		return ok();
	}

	public static Result record() {
		String resp;
		PlivoXML plivoXML;
		Record rec;
		Dial dial;
		User user;

		plivoXML = new PlivoXML();

		rec = new Record();
		rec.setCallbackUrl("http://safe-stream-4972.herokuapp.com/incall/record/callback");
		rec.setCallbackMethod("GET");
		rec.setRedirect(false);// continue to next elemen, in this case dial
		rec.setStartOnDialAnswer(true);// start record after the caller answer
										// the call.

		dial = new Dial();
		user = new User("sip:ch130329160035@phone.plivo.com");

		try {
			plivoXML.append(rec);

			dial.append(user);

			plivoXML.append(dial);
			resp = plivoXML.toXML();
		} catch (PlivoException pe) {
			resp = "<response></response>";
		}
		Logger.debug("response = " + resp);
		return ok(resp).as("text/xml");
	}

	public static Result recordCallback(String RecordUrl, String RecordingID) {
		Logger.debug("Record Call Callback:");
		Logger.debug("\t\tRecordUrl = " + RecordUrl);
		Logger.debug("\t\tRecordingID = " + RecordingID);
		return ok();
	}

	/**
	 * Play music while waiting for a call to be answered
	 * 
	 * @return
	 */
	public static Result playDialMusic() {
		String resp;
		PlivoXML plivoXML;
		Dial dial;
		User user;

		plivoXML = new PlivoXML();
		dial = new Dial();
		dial.setDialMusic("https://s3.amazonaws.com/plivocloud/Trumpet.mp3");
		user = new User("sip:ch130329160035@phone.plivo.com");
		try {
			dial.append(user);
			plivoXML.append(dial);
			resp = plivoXML.toXML();
		} catch (PlivoException pe) {
			resp = "<response></response>";
		}
		Logger.debug("response = " + resp);
		return ok(resp).as("text/xml");
	}

	private static String generateIvrForwardXml() {
		String xml;
		PlivoXML plivoXML = new PlivoXML();

		GetDigits gd = new GetDigits();

		gd.setAction("http://safe-stream-4972.herokuapp.com/incall/ivrforward/getdigits");
		gd.setMethod("GET");
		gd.setTimeout(20);
		gd.setNumDigits(1);

		Speak speak = new Speak(
				"Welcome to Plivo Demo app. Press 1 to call my SIP number. Press 2 to call my mobile number");

		Speak timeout = new Speak(
				"Sorry, I didn't catch that. Please hangup the call and try again later.");
		try {
			gd.append(speak);
			plivoXML.append(gd);
			plivoXML.append(timeout);
			xml = plivoXML.toXML();
		} catch (PlivoException pe) {
			Logger.error("PlivoException while generating ivr forward xml : "
					+ pe.getMessage());
			xml = "<response></response>";
		}
		return xml;

	}

	/**
	 * Present an IVR. Based on input digits, forward to respective number.
	 * 
	 * @return
	 */
	public static Result ivrForward() {
		String resp = InCall.generateIvrForwardXml();
		Logger.debug("response = " + resp);
		return ok(resp).as("text/xml");
	}

	/**
	 * ivrForward get digits handler
	 * 
	 * @param Digits
	 * @return
	 */
	public static Result ivrForwardGetDigits(String Digits) {
		String resp;
		PlivoXML plivoXML;
		Dial dial;

		plivoXML = new PlivoXML();
		dial = new Dial();

		try {
			if ("1".equals(Digits)) {
				User user = new User("sip:ch130329160035@phone.plivo.com");
				dial.append(user);
				plivoXML.append(dial);
			} else if ("2".equals(Digits)) {
				Number num = new Number("12345678");
				dial.append(num);
				plivoXML.append(dial);
			} else {
				Speak s = new Speak(
						"Invalid digits. Only 1 and 2 are valid digits");
				plivoXML.append(s);
			}
			resp = plivoXML.toXML();
		} catch (PlivoException pe) {
			Logger.error("PlivoException :" + pe.getMessage());
			resp = "<response></response>";
		}
		Logger.debug("response = " + resp);
		return ok(resp).as("text/xml");
	}

	/**
	 * Create a conference bridge
	 * 
	 * @return
	 */
	public static Result confBridge() {
		String resp;
		PlivoXML plivoXML = new PlivoXML();
		Conference conf = new Conference("conference bridge");

		conf.setBeep(false);
		conf.setStartConferenceOnEnter(true);
		conf.setWaitSound("http://safe-stream-4972.herokuapp.com/incall/playmp3");
		conf.setEndConferenceOnExit(true);
		try {
			plivoXML.append(conf);
			resp = plivoXML.toXML();
		} catch (PlivoException pe) {
			resp = "<response></response>";
		}
		Logger.debug("response = " + resp);
		return ok(resp).as("text/xml");
	}

	/**
	 * Incoming caller in a call queue (use conf bridge)
	 * 
	 * @return
	 */
	public static Result incCallQueue() {
		String resp;
		PlivoXML plivoXML = new PlivoXML();
		Conference conf = new Conference("Waiting room");

		conf.setBeep(false);
		conf.setWaitSound("http://safe-stream-4972.herokuapp.com/incall/playmp3");

		try {
			plivoXML.append(conf);
			resp = plivoXML.toXML();
		} catch (PlivoException pe) {
			resp = "<response></response>";
		}
		Logger.debug("response = " + resp);
		return ok(resp).as("text/xml");
	}

	public static Result amd(String Machine) {
		Logger.debug("AMD");
		String resp;
		if ("true".equals(Machine)) {
			resp = InCall.generatePlayMp3Xml();
		} else {
			resp = InCall.generateIvrForwardXml();
		}
		return ok(resp).as("text/xml");
	}

	public static Result confBridgeRecord() {
		String resp;
		PlivoXML plivoXML = new PlivoXML();
		Conference conf = new Conference("conference bridge");

		conf.setBeep(false);
		conf.setStartConferenceOnEnter(true);
		conf.setWaitSound("http://safe-stream-4972.herokuapp.com/incall/playmp3");
		conf.setEndConferenceOnExit(true);
		conf.setRecord(true);
		try {
			plivoXML.append(conf);
			resp = plivoXML.toXML();
		} catch (PlivoException pe) {
			resp = "<response></response>";
		}
		Logger.debug("response = " + resp);
		return ok(resp).as("text/xml");
	}

	/**
	 * XML for call 2 numbers, bridge it, and record both legs
	 * 
	 * @return
	 */
	public static Result callBridge() {
		String resp;
		PlivoXML plivoXML = new PlivoXML();
		Conference conf = new Conference("conference bridge");

		conf.setBeep(false);
		conf.setStartConferenceOnEnter(true);
		conf.setWaitSound("http://safe-stream-4972.herokuapp.com/incall/playmp3");
		conf.setEndConferenceOnExit(true);

		conf.setCallbackUrl("http://safe-stream-4972.herokuapp.com/callbridge/callback");
		conf.setCallbackMethod("GET");
		try {
			plivoXML.append(conf);
			resp = plivoXML.toXML();
		} catch (PlivoException pe) {
			Logger.error("plivo exceptio : " + pe.getMessage());
			resp = "<response></response>";
		}
		Logger.debug("response = " + resp);
		return ok(resp).as("text/xml");
	}

	/**
	 * Record all legs
	 * 
	 * @param CallUUID
	 * @return
	 */
	public static Result callBridgeCallback(String CallUUID) {
		String plivoAuthId = play.Play.application().configuration()
				.getString("plivo_auth_id");
		String plivoAuthToken = play.Play.application().configuration()
				.getString("plivo_auth_token");
		PlivoRestConf conf = new PlivoRestConf(plivoAuthId, plivoAuthToken,
				"v1");
		try {
			// record the call
			CallRecordResponse r = Call.record(CallUUID,
					new HashMap<String, Object>(), conf);
			Logger.info("call record--");
			Logger.info("\t\t url = " + r.getUrl());
			Logger.info("\t\t message = " + r.getMessage());
		} catch (PlivoException e) {
			Logger.error("plivo exception :" + e.getMessage());
		} catch (APIException e) {
			Logger.error("plivo api exception : " + e.toString());
		}
		return ok();
	}

	/**
	 * XML for call 2 numbers, bridge it, and record one legs
	 * 
	 * @return
	 */
	public static Result callBridgeRecOne() {
		String resp;
		PlivoXML plivoXML = new PlivoXML();
		Conference conf = new Conference("conference bridge");

		conf.setBeep(false);
		conf.setStartConferenceOnEnter(true);
		conf.setWaitSound("http://safe-stream-4972.herokuapp.com/incall/playmp3");
		conf.setEndConferenceOnExit(true);

		conf.setCallbackUrl("http://safe-stream-4972.herokuapp.com/callbridgerecone/callback");
		conf.setCallbackMethod("GET");
		try {
			plivoXML.append(conf);
			resp = plivoXML.toXML();
		} catch (PlivoException pe) {
			Logger.error("plivo exceptio : " + pe.getMessage());
			resp = "<response></response>";
		}
		Logger.debug("response = " + resp);
		return ok(resp).as("text/xml");
	}

	/**
	 * Record one leg
	 * 
	 * @param CallUUID
	 * @return
	 */
	public static Result callBridgeRecOneCallback(String CallUUID) {
		String plivoAuthId = play.Play.application().configuration()
				.getString("plivo_auth_id");
		String plivoAuthToken = play.Play.application().configuration()
				.getString("plivo_auth_token");
		PlivoRestConf conf = new PlivoRestConf(plivoAuthId, plivoAuthToken,
				"v1");
		try {
			// get call details
			Call call = Call.getLive(CallUUID, conf);
			Logger.info("\t\t call to = " + call.getTo());
			Logger.info("\t\t call from = " + call.getFrom());

			// record legs if matched our criteria
			if (call.getTo().equals(
					play.Play.application().configuration()
							.getString("plivo_leg_to_record"))) {
				CallRecordResponse r = Call.record(CallUUID,
						new HashMap<String, Object>(), conf);
				Logger.info("call record--");
				Logger.info("\t\t url = " + r.getUrl());
				Logger.info("\t\t message = " + r.getMessage());
			}
		} catch (PlivoException e) {
			Logger.error("plivo exception :" + e.getMessage());
		} catch (APIException e) {
			Logger.error("plivo api exception : " + e.toString());
		}
		return ok();
	}

}