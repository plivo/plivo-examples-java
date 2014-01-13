package com.plivo.example.number;

import java.util.HashMap;

import com.plivo.example.Config;
import com.plivo.helper.PlivoRestConf;
import com.plivo.helper.exception.APIException;
import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.resource.AvailableNumberGroup;
import com.plivo.helper.response.NumberStatus;
import com.plivo.helper.response.RentNumberGroupResponse;

public class Rent {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PlivoRestConf conf = new PlivoRestConf(Config.authId, Config.authToken);
		try {
			String groupId = "27071044970273";
			RentNumberGroupResponse rr = AvailableNumberGroup.rent(groupId,
					new HashMap<String, Object>(), conf);
			System.out.println("status = " + rr.getStatus());
			System.out.println("number status list:");
			for (NumberStatus ns : rr.getNumberStatusList()) {
				System.out.println("Number status:");
				System.out.println("\t number = " + ns.getNumber());
				System.out.println("\t status = " + ns.getStatus());
			}
		} catch (PlivoException e) {
			System.out.println("PlivoException e : " + e.getMessage());
		} catch (APIException e) {
			System.out.println("APIException e : " + e.toString());
		}

	}

}
