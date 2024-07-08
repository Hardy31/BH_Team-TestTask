package ru.hardy.client.stomp_server;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurationSupport;

@Configuration
public class WebSocketConfig extends WebSocketMessageBrokerConfigurationSupport {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic", "/queue", "/getLL");
        config.setApplicationDestinationPrefixes("/client");
    }

    @Override
    protected void registerStompEndpoints(StompEndpointRegistry registry) {

    }
}
