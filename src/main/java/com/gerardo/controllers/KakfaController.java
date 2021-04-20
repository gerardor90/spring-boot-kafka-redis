package com.gerardo.controllers;

import com.gerardo.services.KafkaProducerService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

@RestController
public class KakfaController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @GetMapping("/kafka/{topic}/{value}")
    public String findOne(@PathVariable String topic, @PathVariable String value) {
        kafkaProducerService.sendMessage(topic, value);
        return "Valor " + value + " añadido en el topic " + topic;
    }

}
