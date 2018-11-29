package com.jr.erp.msg.websocket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;
 
@Configuration  
@EnableWebSocket  
public class WebSocketConfig implements WebSocketConfigurer {  
    @Override  
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {  
        registry.addHandler(chatMessageHandler(),"/webSocketServer").addInterceptors(new WebSocketInterceptor());  
        registry.addHandler(chatMessageHandler(), "/sockjs/webSocketServer").addInterceptors(new WebSocketInterceptor()).withSockJS();  
    }  
   
    @Bean  
    public TextWebSocketHandler chatMessageHandler(){  
        return new WebSocketHandler();  
    }  
  
} 