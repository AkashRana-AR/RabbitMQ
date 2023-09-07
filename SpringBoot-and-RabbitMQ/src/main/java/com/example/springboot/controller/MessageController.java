package com.example.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.producer.MessageProducer;

@RestController
public class MessageController {

	@Autowired
	private MessageProducer messageProducer;

	@GetMapping("akash/publish")
	public ResponseEntity<String> sendMessage(@RequestParam("message") String message) {
		messageProducer.sendMessage(message);
		messageProducer.sendMessage1(message);
		return ResponseEntity.ok("Message sent to RabbitMQ");
	}
}
