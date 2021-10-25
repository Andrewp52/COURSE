package com.geekbrains.geekmarketwinter.utils;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitReceiver {
    @RabbitListener(queues = "Shop-queue")
    public void receiveRabbitMessage(String message){
        System.out.println("Message from Shop-queue: " + message);
    }
}
