package com.futurenostics.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
	
	@Value("${rabbitmq.queue.name}")
	private String queueName;
	@Value("${rabbitmq.exchange.name}")
	private String exchangeName;
	@Value("${rabbitma.routing_key.name}")
	private String routingKey;
	
	

	@Value("${rabbitmq.json.queue.name}")
	private String jsonQueueName;
	@Value("${rabbitma.json.routing_key.name}")
	private String jsonRoutingKey;
	
	
	// Bean for rabbitmq Queue
	@Bean
	public Queue queue() {
		return new Queue(queueName);
	}

	//Bean for rabbitmq exchange
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(exchangeName);
	}
	
	//Binding queue with exchange using routing key
	@Bean
	public Binding binding() {
		return BindingBuilder
				.bind(queue())
				.to(exchange())
				.with(routingKey);
	}
	
	
	//=====================================================
	
	// Creating Queue to store json object
	@Bean
	public Queue jsonQueue() {
		
		return new Queue(jsonQueueName);
	}
	@Bean
	public Binding jsonBinding() {
		return BindingBuilder
				.bind(jsonQueue())
				.to(exchange())
				.with(jsonRoutingKey);
		
	}
	
	//Creating bean for json converter
	// this Bean only use for when we want to send object to queue
	@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}
	
	// while sending object we need to set MessageConverter to RabbitTemplate
	@Bean
	public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(converter());
		return rabbitTemplate;
	}
	

}
