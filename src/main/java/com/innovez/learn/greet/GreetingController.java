package com.innovez.learn.greet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Sample of controller which can handle message send via websocket.
 * 
 * @author zakyalvan
 */
@Controller
public class GreetingController {
	private static final Logger LOGGER = LoggerFactory.getLogger(GreetingController.class);
	
	@Autowired
	private SimpMessagingTemplate template;
	
	@MessageMapping("/hello")
	//@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage helloMessage) throws Exception {
		LOGGER.debug("Handle greeting with hello message from '{}'", helloMessage.getName());
		Thread.sleep(3000);
		return new Greeting(String.format("Hello, %s", helloMessage.getName()));
	}
	
	@RequestMapping(value="/broadcast", method=RequestMethod.GET)
	public void broadcast() throws Exception {
		LOGGER.debug("Broadcast messgae");
		for(int i = 0; i < 1000; i++) {
			Thread.sleep(500);
			template.convertAndSend("/topic/hello", new Greeting("Message #" + i));	
		}
	}
}
