package com.qaiware.messagingserver.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.qaiware.messagingserver.model.Message;

@Repository
public interface MessageDAO extends CrudRepository<Message, Long> {
}
