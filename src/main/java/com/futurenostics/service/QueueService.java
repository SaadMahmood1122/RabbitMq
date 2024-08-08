package com.futurenostics.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futurenostics.dto.User;
import com.futurenostics.producer.RabbitMqJsonProducer;
import com.futurenostics.producer.RabbitMqProducer;

@Service
public class QueueService {
	@Autowired
	private RabbitMqProducer producer;
	@Autowired
	private RabbitMqJsonProducer  jsonProducer;
	
	private Logger logger = LoggerFactory.getLogger(QueueService.class);
	
	public void sendMessageToQueue(String message) {
		logger.info(String.format("Sinding message to queue %s", message));
		producer.sendMessage(message);
	}
	
	public void sendMessageToQueue(User user) {
		logger.info(String.format("Sinding message to queue %s", user.toString()));
		jsonProducer.sendJsonMsg(user);
	}
	
	
}
