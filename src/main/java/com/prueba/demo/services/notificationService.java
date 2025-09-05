package com.prueba.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class notificationService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendNotification(String data) {
        // "/topic/notifications" es la ruta a la que los clientes Angular se suscriben
        messagingTemplate.convertAndSend("/topic/notifications", data);
    }
}
