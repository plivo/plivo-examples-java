package com.plivo.example.number;

import java.util.HashMap;
import java.util.Map;

import com.plivo.example.Config;
import com.plivo.helper.PlivoRestConf;
import com.plivo.helper.exception.APIException;
import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.resource.AvailableNumberGroup;
import com.plivo.helper.resource.AvailableNumberGroupList;

public class Search {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PlivoRestConf conf = new PlivoRestConf(Config.authId, Config.authToken);
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("country_iso", "US");
			params.put("limit", 5);
			AvailableNumberGroupList angl = AvailableNumberGroup.search(params,
					conf);
			for (AvailableNumberGroup ang : angl.getList()) {
				System.out.println("---Available Number group---");
				System.out.println("\t Group ID = " + ang.getGroupId());
				System.out.println("\t Number Type = " + ang.getNumberType());
				System.out.println("\t Prefix = " + ang.getNumberPrefix());
				System.out.println("\t Region = " + ang.getRegion());
				System.out.println("\t Rental rate = " + ang.getRentalRate());
				System.out.println("\t setup rate = " + ang.getSetupRate());
				System.out.println("\t sms rate = " + ang.getSmsRate());
				System.out.println("\t SMS Enabled = " + ang.getIsSmsEnabled());
				System.out.println("\t stock = " + ang.getStock());
				System.out.println("\t voice enabled = "
						+ ang.getIsVoiceEnabled());
				System.out.println("\t voice rate = " + ang.getVoiceRate());

			}
		} catch (PlivoException e) {
			System.out.println("PlivoException e : " + e.getMessage());
		} catch (APIException e) {
			System.out.println("APIException e : " + e.toString());
		}

	}

}
