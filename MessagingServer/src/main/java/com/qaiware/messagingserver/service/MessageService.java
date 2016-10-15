package com.qaiware.messagingserver.service;

import com.qaiware.messagingserver.model.helper.MessageType;

public interface MessageService {
	void save(MessageType type, String payload);
}
