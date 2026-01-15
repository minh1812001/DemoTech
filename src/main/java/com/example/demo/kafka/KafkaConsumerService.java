package com.example.demo.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "test-topic", groupId = "my-group-id")
    public void listen(String message) {
        System.out.println("Nhận được tin nhắn mới: " + message);
    }
}
