package ru.hardy.client.service.ws_client;

import lombok.Getter;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import ru.hardy.client.dto.WsConfiguratorDto;
import ru.hardy.client.repository.LogLineRepositiry;

import java.util.concurrent.ExecutionException;


@Getter
public class WsClientListener {
    private WsConfiguratorDto wsConfigurator = WsConfiguratorDto.getInstance();
    private LogLineRepositiry logLineRepositiry;
//    private String port ="8082";

    public WsClientListener(LogLineRepositiry logLineRepositiry){
        this.logLineRepositiry = logLineRepositiry;
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter()); //Замена конвертора
//      todo -   stompClient.setMessageConverter(new StringMessageConverter()); //С этим конвертором не хочет работать

        WsClientSessionHandler wsClientSessionHandler = new WsClientSessionHandler(logLineRepositiry);
        ListenableFuture<StompSession> sessionAsync =
                stompClient.connect("ws://localhost:"+wsConfigurator.getPort()+"/websocket-server", wsClientSessionHandler);
        StompSession session = null;
        try {
            session = sessionAsync.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        session.subscribe("/topic/messages", wsClientSessionHandler);
    }


//    public WsClientListener(LogLineRepositiry logLineRepositiry, String port){
//        this.logLineRepositiry = logLineRepositiry;
//        this.port = port;
//        WebSocketClient client = new StandardWebSocketClient();
//
//        WebSocketStompClient stompClient = new WebSocketStompClient(client);
//        stompClient.setMessageConverter(new MappingJackson2MessageConverter()); //Замена конвертора
////        stompClient.setMessageConverter(new StringMessageConverter());    //С этим конвертором не хочет работать
//        WsClientSessionHandler wsClientSessionHandler = new WsClientSessionHandler(logLineRepositiry);
//        ListenableFuture<StompSession> sessionAsync =
//                stompClient.connect("ws://localhost:" + port + "/websocket-server", wsClientSessionHandler);
//        StompSession session = null;
//        try {
//            session = sessionAsync.get();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } catch (ExecutionException e) {
//            throw new RuntimeException(e);
//        }
//        session.subscribe("/topic/messages", wsClientSessionHandler);
//    }



}
