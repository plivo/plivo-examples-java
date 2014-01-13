package com.plivo.example.endpoint;

import java.util.HashMap;
import java.util.Map;

import com.plivo.example.Config;
import com.plivo.helper.PlivoRestConf;
import com.plivo.helper.exception.APIException;
import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.resource.Endpoint;
import com.plivo.helper.response.EndpointCreateResponse;

public class Crud {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * Sample of creating and endpoint
	 * 
	 * @return
	 */
	public static EndpointCreateResponse create() {
		EndpointCreateResponse er = null;
		Map<String, Object> params = new HashMap<String, Object>();
		PlivoRestConf conf = new PlivoRestConf(Config.authId, Config.authToken);

		params.put("username", "good_username"); // endpoint username
		params.put("password", "weak_password"); // endpoint password
		params.put("alias", "unittest"); // endpoint alias

		try {
			er = Endpoint.create(params, conf);
		} catch (PlivoException e) {
			System.out.println("PlivoException e : " + e.getMessage());
		} catch (APIException e) {
			System.out.println("APIException e : " + e.toString());
		}
		return er;
	}

	/**
	 * Delete an endpoint, given it's endpoint id.
	 * 
	 * @param endpointId
	 *            endpoint id
	 * @return true if successful
	 */
	public static boolean delete(String endpointId) {
		PlivoRestConf conf = new PlivoRestConf(Config.authId, Config.authToken);
		try {
			Endpoint.delete(endpointId, conf);
			return true;
		} catch (PlivoException e) {
			System.out.println("PlivoException e : " + e.getMessage());
		} catch (APIException e) {
			System.out.println("APIException e : " + e.toString());
		}
		return false;
	}

	/**
	 * Get an endpoint object, given it's endpoint id
	 * 
	 * @param endpointId
	 *            endpoint id
	 * @return Endpoint object if successful. null otherwise.
	 */
	public static Endpoint get(String endpointId) {
		PlivoRestConf conf = new PlivoRestConf(Config.authId, Config.authToken);
		Endpoint ep = null;
		try {
			ep = Endpoint.get(endpointId, conf);
		} catch (PlivoException e) {
			System.out.println("PlivoException e : " + e.getMessage());
		} catch (APIException e) {
			System.out.println("APIException e : " + e.toString());
		}
		return ep;
	}

	/**
	 * Sample to change alias of endpoint
	 * 
	 * @param endpointId
	 *            endpoint ID
	 * @param newAlias
	 *            new alias
	 */
	public static void edit(String endpointId, String newAlias) {
		Map<String, Object> params = new HashMap<String, Object>();
		PlivoRestConf conf = new PlivoRestConf(Config.authId, Config.authToken);

		params.put("alias", "newAlias"); // endpoint alias
		try {
			Endpoint.modify(endpointId, params, conf);
		} catch (PlivoException e) {
			System.out.println("PlivoException e : " + e.getMessage());
		} catch (APIException e) {
			System.out.println("APIException e : " + e.toString());
		}
	}
}
