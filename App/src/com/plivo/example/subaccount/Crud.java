package com.plivo.example.subaccount;

import java.util.HashMap;
import java.util.UUID;

import com.plivo.example.Config;
import com.plivo.helper.PlivoRestConf;
import com.plivo.helper.exception.APIException;
import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.resource.SubAccount;

public class Crud {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * Create an endpoint return it's id
	 * 
	 * @return subaccount auth id if successful, null otherwise.
	 */
	public static String create() {
		String subAuthId = null;
		PlivoRestConf conf = new PlivoRestConf(Config.authId, Config.authToken,
				"v1");
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("name", UUID.randomUUID().toString());
		params.put("enabled", "false");

		try {
			subAuthId = SubAccount.create(params, conf);
		} catch (PlivoException e) {
			System.out.println("PlivoException e : " + e.getMessage());
		} catch (APIException e) {
			System.out.println("APIException e : " + e.toString());
		}
		return subAuthId;

	}

	/**
	 * Delete a sub account
	 * 
	 * @param subAuthId
	 *            subaccount auth id
	 */
	public static void delete(String subAuthId) {
		PlivoRestConf conf = new PlivoRestConf(Config.authId, Config.authToken);
		try {
			SubAccount.delete(subAuthId, conf);
		} catch (PlivoException e) {
			System.out.println("PlivoException e : " + e.getMessage());
		} catch (APIException e) {
			System.out.println("APIException e : " + e.toString());
		}
	}

	public static void edit(String subAuthId, boolean enabled) {
		PlivoRestConf conf = new PlivoRestConf(Config.authId, Config.authToken);
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("enabled", enabled);

		try {
			SubAccount.modify(subAuthId, params, conf);
		} catch (PlivoException e) {
			System.out.println("PlivoException e : " + e.getMessage());
		} catch (APIException e) {
			System.out.println("APIException e : " + e.toString());
		}
	}

	public static SubAccount get(String subAuthId) {
		SubAccount sa = null;
		PlivoRestConf conf = new PlivoRestConf(Config.authId, Config.authToken);
		try {
			sa = SubAccount.get("SAODDKMDVLMJCWNDG5OT", conf);
		} catch (PlivoException e) {
			System.out.println("PlivoException e : " + e.getMessage());
		} catch (APIException e) {
			System.out.println("APIException e : " + e.toString());
		}
		return sa;
	}
}
