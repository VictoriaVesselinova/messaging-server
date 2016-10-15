package com.qaiware.messagingserver.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qaiware.messagingserver.dao.MessageDAO;
import com.qaiware.messagingserver.model.Message;
import com.qaiware.messagingserver.model.helper.MessageType;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {
	@Autowired
	private MessageDAO messageDAO;

	@Override
	public void save(MessageType type, String payload) {
		Message message = new Message();
		message.setType(type);
		message.setPayload(payload);

		messageDAO.save(message);
	}
}
