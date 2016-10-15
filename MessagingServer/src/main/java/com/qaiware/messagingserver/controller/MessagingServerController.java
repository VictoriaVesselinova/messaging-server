package com.qaiware.messagingserver.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qaiware.messagingserver.exception.ValidationException;
import com.qaiware.messagingserver.model.helper.MessageType;
import com.qaiware.messagingserver.service.MessageService;

@RestController
public class MessagingServerController {
	@Autowired
	private MessageService messageService;

	@RequestMapping(method = RequestMethod.POST, value = "/messages/{type}")
	public void saveMessage(@PathVariable MessageType type, @RequestBody String payload, HttpServletResponse response) {
		validatePayload(type, payload);
		messageService.save(type, payload);
		response.setStatus(HttpServletResponse.SC_CREATED);
	}

	private void validatePayload(MessageType type, String payload) {
		String regexContainsNumbers = ".*[0-9].*";

		switch (type) {
		case send_text:
			if (payload.length() < 1 || payload.length() > 160) {
				throw new ValidationException();
			}
			break;

		case send_emotion:
			if (payload.length() < 2 || payload.length() > 10 || payload.matches(regexContainsNumbers)) {
				throw new ValidationException();
			}
			break;
		}
	}

	@ResponseStatus(value = HttpStatus.PAYLOAD_TOO_LARGE)
	@ExceptionHandler(ValidationException.class)
	public void validationExceptionHandler() {
	}
}