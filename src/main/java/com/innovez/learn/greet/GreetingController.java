package com.innovez.learn.greet;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {
	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage helloMessage) throws Exception {
		Thread.sleep(3000);
		return new Greeting(String.format("Hello, %s", helloMessage.getName()));
	}
}
