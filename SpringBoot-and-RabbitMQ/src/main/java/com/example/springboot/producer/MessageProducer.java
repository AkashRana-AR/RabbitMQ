package com.example.springboot.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MessageProducer {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void sendMessage(String message) {
		log.info("Message sent -> %s"+ message);
		rabbitTemplate.convertAndSend("AkashExchange", "AkashRouteKey", message);;
	}
}
