package com.plivo.example.recording;

import java.util.HashMap;
import java.util.Map;

import com.plivo.example.Config;
import com.plivo.helper.PlivoRestConf;
import com.plivo.helper.exception.APIException;
import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.resource.Recording;
import com.plivo.helper.resource.RecordingList;

public class ViewFilter {
	public static void main(String[] args) {
		PlivoRestConf conf = new PlivoRestConf(Config.authId, Config.authToken);
		try {
			System.out.println("Calling recording get list API...");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("limit", 1);
			RecordingList rl = Recording.getList(params, conf);

			for (Recording r : rl.getList()) {
				System.out.println("Recording : ");
				System.out.println("\t id = " + r.getId());
				System.out.println("\t add time = " + r.getAddTime());
				System.out.println("\t url = " + r.getRecordingURL());
				System.out.println("\t format = " + r.getRecordingFormat());
				System.out.println("\t type = " + r.getRecordingType());
				System.out.println("\t start = " + r.getRecordingStartMs());
				System.out.println("\t end = " + r.getRecordingEndMs());
				System.out.println("\t duration = "
						+ r.getRecordingDurationMs());
			}

		} catch (PlivoException pe) {
			System.out.println(pe.getMessage());
		} catch (APIException ae) {
			System.out.println(ae.toString());
		}

	}
}
