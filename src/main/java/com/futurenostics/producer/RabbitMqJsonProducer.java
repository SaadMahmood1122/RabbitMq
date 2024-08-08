package com.futurenostics.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.futurenostics.dto.User;

@Service
public class RabbitMqJsonProducer {
	
	@Value("${rabbitmq.exchange.name}")
	private String exchange;
	@Value("${rabbitma.json.routing_key.name}")
	private String json_routing_key;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqJsonProducer.class); 
	public void sendJsonMsg(User user) {
		LOGGER.info(String.format("Message send -> %s", user.toString()));
		
		// sending object queue
		rabbitTemplate.convertAndSend(exchange, json_routing_key, user);
		
	}

}
