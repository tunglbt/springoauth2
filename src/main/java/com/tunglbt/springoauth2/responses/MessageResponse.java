package com.tunglbt.springoauth2.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MessageResponse {
    private String type;
    private List<String> message = new ArrayList<>();

    public MessageResponse(String type, List<String> message) {
        this.type = type;
        this.message = message;
    }

    public MessageResponse(String type, String message) {
        this.type = type;
        this.message.add(message);
    }
}
