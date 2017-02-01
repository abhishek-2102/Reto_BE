package com.niit.reto.controller;

import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.niit.reto.model.Message;
import com.niit.reto.model.OutputMessage;

@Controller
public class ChatForumController {
	
	@MessageMapping("/chat_forum")
	@SendTo("/topic/message")
	public OutputMessage sendMessage(Message msg){
		return new OutputMessage(msg,new Date());
	}
	
}
