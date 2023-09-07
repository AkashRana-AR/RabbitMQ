package com.example.springboot.producer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MessageProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void sendMessage(String message1) {
		log.info("Message sent from producer to AkashQueue. " + message1);
		Map<String, Object> headers = new HashMap<>();
		headers.put("exchange", "AkashExchange");
		headers.put("routeKey", "AkashRouteKey");
		MessageProperties properties = new MessageProperties();
		properties.getHeaders().putAll(headers);
		Message message = new Message(message1.getBytes(), properties);
		rabbitTemplate.convertAndSend("AkashExchange", "AkashRouteKey", message);
	}

	public void sendMessage1(String message1) {
		log.info("Message sent from producer to AkashQueue1. " + message1);
		Map<String, Object> headers = new HashMap<>();
		headers.put("exchange", "AkashExchange");
		headers.put("routeKey", "AkashRouteKey");
		MessageProperties properties = new MessageProperties();
		properties.getHeaders().putAll(headers);
		Message message = new Message(message1.getBytes(), properties);
		rabbitTemplate.convertAndSend("AkashExchange", "AkashRouteKey1", message);
		;
	}
}
