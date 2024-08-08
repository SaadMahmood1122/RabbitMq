package com.futurenostics.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.futurenostics.dto.User;

@Service
public class RabbitMqConsumer {
	
	private Logger logger = LoggerFactory.getLogger(RabbitMqConsumer.class);
	
	@RabbitListener(queues = "${rabbitmq.queue.name}")
	public void consume(String message) {
		logger.info(String.format("Message Received -> %s ", message));
	}

}
