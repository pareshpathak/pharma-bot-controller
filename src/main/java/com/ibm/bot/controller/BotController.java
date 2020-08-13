package com.ibm.bot.controller;

import static com.ibm.hello.service.ServiceNameConstants.HELLO_NAME;
import static com.ibm.hello.service.ServiceNameConstants.HOLA_NAME;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.hello.service.DialogService;

import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
public class BotController {

	private final Logger log = LoggerFactory.getLogger(BotController.class);

	@Autowired
	DialogService vaDialogService;

	@PostMapping(path = "/converse", produces = "application/json")
	@ApiResponses(value = {
            @ApiResponse(code = 400, message = "Content body is missing"),
            @ApiResponse(code = 406, message = "Name parameter missing"),
            @ApiResponse(code = 415, message = "Missing content type")
    })
	public String processDialog(@Valid @RequestBody String message,
            @ApiParam(
                    allowableValues = "Dialog",
                    value = "the beanName for the service implementation that should be used to fulfill the request")
            @RequestHeader(name = "serviceName", required = false) final String serviceName
    ) {
		log.debug("processDialog - Entry {}", "");
		String responseJSON="";
		

		try {
			responseJSON = vaDialogService.processDialog(message).toString();
		
			log.debug("after calling process Dialog ConversationSessionDTO response: {}", "");
		} catch (Exception exception) {
			log.debug("Exception in VADialogController" + exception.getStackTrace());
		}
		return responseJSON;
	}

}
