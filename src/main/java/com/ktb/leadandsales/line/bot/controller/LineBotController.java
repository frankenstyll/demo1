package com.ktb.leadandsales.line.bot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktb.leadandsales.line.bot.service.LineMessageService;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.UnfollowEvent;
import com.linecorp.bot.model.event.message.AudioMessageContent;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import com.linecorp.bot.model.event.message.LocationMessageContent;
import com.linecorp.bot.model.event.message.StickerMessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.event.message.VideoMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@LineMessageHandler
public class LineBotController {

	private static final Logger log = LoggerFactory.getLogger(LineBotController.class);
	
    @Autowired
    LineMessageService service;

    @EventMapping
    public Message handleFollow(FollowEvent event) {
    	System.out.println("[handleFollow][START]handleFollow");
        System.out.println("User Id : " + event.getSource().getUserId());
        System.out.println("[handleFollow][END]handleFollow");
        String messageResponse = "UserId : "+event.getSource().getUserId() 
        		+ ", replyToken : " + event.getReplyToken();
        
        return new TextMessage(messageResponse);
    }
    
	@EventMapping
	public Message handleTextMessage(MessageEvent<TextMessageContent> e) {
		System.out.println("[BotController][START]handleTextMessage");
		System.out.println("[BotController]" + e);
        TextMessageContent message = e.getMessage();
        System.out.println("[BotController][END]handleTextMessage");
        
        String messageResponse = "";
        try {
        	String userId = e.getSource().getUserId();
        	String replyToken = e.getReplyToken();
        	
        	messageResponse += "UserId : " + userId + ",";
        	messageResponse += "replyToken : " + replyToken;
        	
    	}catch(Exception ex) {
			ex.printStackTrace();
		}
        
        return new TextMessage("[BotController]"+ messageResponse);
	
	}

    @EventMapping
    public void handleUnfollow(UnfollowEvent event) {
        System.out.println("User Id : " + event.getSource().getUserId());
    }

    @EventMapping
    public void handleStickerMessage(MessageEvent<StickerMessageContent> event) {
        StickerMessageContent message = event.getMessage();
        System.out.println("Sender Id : " + event.getSource().getSenderId());
        System.out.println("User Id : " + event.getSource().getUserId());
        service.handleStickerContent(event.getReplyToken(), event, message);
    }

    @EventMapping
    public void handleLocationMessage(MessageEvent<LocationMessageContent> event) {
        LocationMessageContent message = event.getMessage();
        System.out.println("Sender Id : " + event.getSource().getSenderId());
        System.out.println("User Id : " + event.getSource().getUserId());
        service.handleLocationContent(event.getReplyToken(), event, message);
    }

    @EventMapping
    public void handleImageMessage(MessageEvent<ImageMessageContent> event) {
        ImageMessageContent message = event.getMessage();
        System.out.println("Sender Id : " + event.getSource().getSenderId());
        System.out.println("User Id : " + event.getSource().getUserId());
        service.handleImageContent(event.getReplyToken(), event, message);
    }

    @EventMapping
    public void handleVideoMessage(MessageEvent<VideoMessageContent> event) {
        VideoMessageContent message = event.getMessage();
        System.out.println("Sender Id : " + event.getSource().getSenderId());
        System.out.println("User Id : " + event.getSource().getUserId());
        service.handleVideoContent(event.getReplyToken(), event, message);
    }

    @EventMapping
    public void handleAudioMessage(MessageEvent<AudioMessageContent> event) {
        AudioMessageContent message = event.getMessage();
        System.out.println("Sender Id : " + event.getSource().getSenderId());
        System.out.println("User Id : " + event.getSource().getUserId());
        service.handleAudioContent(event.getReplyToken(), event, message);
    }

    /*
    @EventMapping
    public void handleTextMessage(MessageEvent<TextMessageContent> event) {
        TextMessageContent message = event.getMessage();
        System.out.println("Sender Id : " + event.getSource().getSenderId());
        System.out.println("User Id : " + event.getSource().getUserId());
        service.handleTextContent(event.getReplyToken(), event, message.getText());
    }*/



}
