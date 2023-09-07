package com.example.springboot.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {

	// Normal queue for which we have enabled retry, initial-interval, max-attempts,
	// max-interval, multiplier
	@Bean
	public Queue queue1() {
		return QueueBuilder.durable("AkashQueue").withArgument("x-dead-letter-exchange", "AkashExchange")
				.withArgument("x-dead-letter-routing-key", "AkashDLQKey").build();
	}

	// Normal queue for which we have enabled retry, initial-interval, max-attempts,
	// max-interval, multiplier
	@Bean
	public Queue queue2() {
		return QueueBuilder.durable("AkashQueue1").withArgument("x-dead-letter-exchange", "AkashExchange")
				.withArgument("x-dead-letter-routing-key", "AkashDLQKey").build();
	}

	// Dead letter queue
	@Bean
	public Queue dlq() {
		return new Queue("deadLetterQueue");
	}

	// Using same exchange for both normal queue and dead letter queue
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange("AkashExchange");
	}

	@Bean
	public Binding binding1(Queue queue1, TopicExchange exchange) {
		return BindingBuilder.bind(queue1).to(exchange).with("AkashRouteKey");
	}

	@Bean
	public Binding binding3(Queue queue2, TopicExchange exchange) {
		return BindingBuilder.bind(queue2).to(exchange).with("AkashRouteKey1");
	}

	@Bean
	public Binding binding2(Queue dlq, TopicExchange exchange) {
		return BindingBuilder.bind(dlq).to(exchange).with("AkashDLQKey");
	}
}
