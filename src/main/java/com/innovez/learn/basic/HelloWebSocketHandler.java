package com.innovez.learn.basic;

import org.apache.log4j.Logger;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class HelloWebSocketHandler extends TextWebSocketHandler {
	private Logger logger = Logger.getLogger(HelloWebSocketHandler.class);
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		logger.debug(message.getPayload());
		session.sendMessage(new TextMessage("Your message with payload '" + message.getPayload() + "' received!"));
	}
}
