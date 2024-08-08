package com.futurenostics.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.futurenostics.dto.User;

@Service
public class RabbitMqJsonConsumer {
	
	private Logger logger = LoggerFactory.getLogger(RabbitMqJsonConsumer.class);
	
	@RabbitListener(queues = "${rabbitmq.json.queue.name}")
	public void consume(User user) {
		logger.info(String.format("Message Received -> %s ", user.toString()));
	}

}
