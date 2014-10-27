package com.innovez.learn.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.jetty.JettyRequestUpgradeStrategy;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocket
public class BasicWebSocketConfig implements WebSocketConfigurer {
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(helloHandler(), "/native-web-socket")
			.addInterceptors(new HttpSessionHandshakeInterceptor())
			.setHandshakeHandler(handshakeHandler());
	}
	
	@Bean
	public SimpleWebSocketHandler helloHandler() {
		return new SimpleWebSocketHandler();
	}
	
	@Bean
	public DefaultHandshakeHandler handshakeHandler() {
		return new DefaultHandshakeHandler(new JettyRequestUpgradeStrategy());
	}
}