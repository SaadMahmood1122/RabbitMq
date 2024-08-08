package com.futurenostics.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqProducer {
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	private Logger logger = LoggerFactory.getLogger(RabbitMqProducer.class);
	@Value("${rabbitmq.exchange.name}")
	private String exchangeName;
	@Value("${rabbitma.routing_key.name}")
	private String routingKey;
	
	public void sendMessage(String message) {
		logger.info(String.format("Message send -> %s", message));
		rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
	}

}
