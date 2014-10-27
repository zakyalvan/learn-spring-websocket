package com.innovez.learn.greet;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Greeting feature configuration.
 * 
 * @author zakyalvan
 */
@Configuration
@EnableWebSocketMessageBroker
public class GreetWebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/hello").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableStompBrokerRelay("/topic", "/queue")
			.setRelayHost("localhost")
			.setRelayPort(61613)
			.setClientLogin("guest")
			.setClientPasscode("guest")
			.setAutoStartup(true);
		registry.setApplicationDestinationPrefixes("/app");
	}
}
