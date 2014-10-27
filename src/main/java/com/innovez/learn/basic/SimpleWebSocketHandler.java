package com.innovez.learn.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Just a simple spring based web socket handler, handling text message received
 * and send back response to client.
 * 
 * @author zakyalvan
 */
public class SimpleWebSocketHandler extends TextWebSocketHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleWebSocketHandler.class);
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		LOGGER.debug("Handle message with payload {}", message.getPayload());
		session.sendMessage(new TextMessage("Your message with payload '" + message.getPayload() + "' received!"));
	}
}
