package com.innovez.learn.greet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Sample of controller which can handle message send via websocket.
 * 
 * @author zakyalvan
 */
@Controller
public class GreetingController {
	private static final Logger LOGGER = LoggerFactory.getLogger(GreetingController.class);
	
	@MessageMapping("/hello")
	//@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage helloMessage) throws Exception {
		LOGGER.debug("Handle greeting with hello message from '{}'", helloMessage.getName());
		Thread.sleep(3000);
		return new Greeting(String.format("Hello, %s", helloMessage.getName()));
	}
}
