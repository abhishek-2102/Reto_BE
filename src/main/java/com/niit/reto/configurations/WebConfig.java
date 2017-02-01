package com.niit.reto.configurations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@ComponentScan("com.nitt.reto.*")
@Configuration
@EnableWebSocketMessageBroker
public class WebConfig extends AbstractWebSocketMessageBrokerConfigurer{
	
	public void configureMessageBroker(MessageBrokerRegistry broker){
		broker.enableSimpleBroker("/topic","/queue");
		broker.setApplicationDestinationPrefixes("/app");
		
	}
	
	public void registerStompEndpoints(StompEndpointRegistry stomp) {
		stomp.addEndpoint("/chat").withSockJS();
		stomp.addEndpoint("/chat_forum").withSockJS();
	
	}
}
