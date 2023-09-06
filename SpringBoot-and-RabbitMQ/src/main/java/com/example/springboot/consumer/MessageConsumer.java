package com.example.springboot.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MessageConsumer {

	@RabbitListener(queues = "AkashQueue")
	public void consumeQueue1(String message) {
		try {
			//To get null pointer exception setting message to null so that it can go to the dead letter queue which we have defined in config file.
			log.info("Receiving message in consumer. " + message);
			message = null;
			int length = message.length();
		} catch (NullPointerException e) {
			throw new NullPointerException(e.getMessage());
		} catch (Exception e) {
			log.info("Exception occur while processing message." + e.getMessage());
		}

	}
	
	@RabbitListener(queues = "deadLetterQueue")
	public void consumeQueue2(String message) {
		try {
			log.info("Receiving message in dead letter consumer. " + message);
			int length = message.length();
		} catch (NullPointerException e) {
			throw new NullPointerException(e.getMessage());
		} catch (Exception e) {
			log.info("Exception occur while processing message." + e.getMessage());
		}

	}
}
