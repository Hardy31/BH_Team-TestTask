package ru.hardy.client.service.ws_client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class OutgoingMessage {
    private String content;

    public OutgoingMessage(String content) {
        this.content = content;
    }

    public OutgoingMessage() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
