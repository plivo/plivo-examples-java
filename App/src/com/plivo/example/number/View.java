package com.plivo.example.number;

import com.plivo.example.Config;
import com.plivo.helper.PlivoRestConf;
import com.plivo.helper.exception.APIException;
import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.resource.Number;

public class View {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PlivoRestConf conf = new PlivoRestConf(Config.authId, Config.authToken);
		try {
			Number number = Number.get(Config.myPlivoNumber, conf);
			System.out.println("Number Details:");
			System.out.println("\t number = " + number.getNumber());
			System.out.println("\t voice enabled = "
					+ number.getIsVoiceEnabled());
			System.out.println("\t application : " + number.getApplication());
			System.out.println("\t region = " + number.getRegion());
			System.out.println("\t sub account = " + number.getSubAccount());
			System.out.println("\t alias = " + number.getAlias());
			System.out.println("\t monthly rental rate = "
					+ number.getMonthlyRentalRrate());
			System.out.println("\t carrier = " + number.getCarrier());
			System.out.println("\t sms rate = " + number.getSmsRate());
			System.out.println("\t number type = " + number.getNumberType());
			System.out.println("\t voice rate = " + number.getVoiceRate());
			System.out.println("\t sms enabled = " + number.getIsSmsEnabled());
			System.out.println("\t added on = " + number.getAddedOn());
		} catch (PlivoException e) {
			System.out.println("PlivoException e : " + e.getMessage());
		} catch (APIException e) {
			System.out.println("APIException e : " + e.toString());
		}

	}
}
