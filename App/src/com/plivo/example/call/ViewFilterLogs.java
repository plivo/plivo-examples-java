package com.plivo.example.call;

import java.util.HashMap;
import java.util.Map;

import com.plivo.example.Config;
import com.plivo.helper.PlivoRestConf;
import com.plivo.helper.exception.APIException;
import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.resource.CDR;
import com.plivo.helper.resource.CDRList;
import com.plivo.helper.resource.Call;

public class ViewFilterLogs {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PlivoRestConf conf = new PlivoRestConf(Config.authId, Config.authToken);
		try {
			Map<String, Object> params = new HashMap<String, Object>();

			params.put("call_direction", "inbound");// only inbound call
			params.put("limit", 3);// max 3 logs

			System.out.println("Calling API server....");

			CDRList cdrList = Call.getCDRList(params, conf);
			for (CDR cdr : cdrList.getList()) {
				System.out.println("---CDR -- ");
				System.out.println("\t from = " + cdr.getFromNumber());
				System.out.println("\t to = " + cdr.getToNumber());
				System.out.println("\t direction = " + cdr.getCallDirection());
				System.out.println("\t total amount = " + cdr.getTotalAmount());

			}
		} catch (PlivoException e) {
			System.out.println("PlivoException e : " + e.getMessage());
		} catch (APIException e) {
			System.out.println("APIException e : " + e.toString());
		}

	}
}
