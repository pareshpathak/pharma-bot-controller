package com.ibm.hello.service;

import java.io.IOException;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;


import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.hello.config.Constants;
import com.ibm.watson.assistant.v1.Assistant;
import com.ibm.watson.assistant.v1.model.Context;
import com.ibm.watson.assistant.v1.model.MessageInput;
import com.ibm.watson.assistant.v1.model.MessageOptions;
import com.ibm.watson.assistant.v1.model.MessageResponse;
import com.ibm.watson.assistant.v1.model.RuntimeResponseGeneric;

@Service
public class DialogService {

		private final Logger log = LoggerFactory.getLogger(DialogService.class);
	private static Map<String, Context> userContextMap = new HashMap<>();

	public JSONObject processDialog(String message)
			throws IOException {
		LogManager.getLogManager().reset();
		String watsonOutput = "";
		JSONObject jsonObject = new JSONObject(message);
		String userID = jsonObject.get("userName").toString();
		JSONObject jsonResponse = new JSONObject();
		JSONObject jsonWatson = new JSONObject();

		// Set up Assistant service.
		Authenticator authenticator = new IamAuthenticator(Constants.APIKEY);
		Assistant service = new Assistant(Constants.VERSION, authenticator);
		String assistantId = "af9e1a39-90d5-47f8-a102-a5b79699d63e";//assistant skill id
		log.debug("assistantId-------------------------->" + assistantId);

		Context context = new Context();
		if (userContextMap.size() > 0 && null != userContextMap.get(userID)) {
			context = userContextMap.get(userID);
			context.removeProperty("action");
			context.removeProperty("user_feedback");
		}

		MessageInput input = new MessageInput();
		input.setText(jsonObject.get("message").toString());
		MessageOptions messageOptions = new MessageOptions.Builder().context(context).workspaceId(assistantId)
				.input(input).build();
		Date request_time = new Date();
		MessageResponse response = service.message(messageOptions).execute().getResult();
		Date reponse_time = new Date();
		userContextMap.put(userID, response.getContext());
		log.debug("reponse of watson is --> " + response);

		List<RuntimeResponseGeneric> responseGeneric = response.getOutput().getGeneric();

		if (responseGeneric != null && responseGeneric.size() > 0) {
			watsonOutput = responseGeneric.toString();
			jsonWatson.put("responseType", Constants.RESPONSE_FROM_ASSISTANT);
			jsonWatson.put("response", watsonOutput);
			jsonResponse.put("jsonWatson", jsonWatson);
		}
		return jsonResponse;
	}

}