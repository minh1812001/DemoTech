package com.example.demo;

import com.example.demo.kafka.KafkaProducerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kafka")
public class KafkaController {
    private final KafkaProducerService producerService;

    public KafkaController(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping("/publish")
    public String publish(@RequestParam("msg") String message) {
        producerService.sendMessage(message);
        return "Gửi thành công!";
    }
}
