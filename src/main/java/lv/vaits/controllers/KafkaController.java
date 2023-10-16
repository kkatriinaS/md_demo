package lv.vaits.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lv.vaits.kafka.KafkaProducer;
import lv.vaits.repos.INotificationRepo;


@RestController
@RequestMapping("/api/v1/kafka")
public class KafkaController {
	
	@Autowired
	private KafkaProducer kafkaProducer;
	
    public KafkaController(KafkaProducer kafkaProducer, INotificationRepo notificationRepo) {
        this.kafkaProducer = kafkaProducer;
    }

    // localhost:8080/api/v1/kafka/publish?notification=hello world
    @GetMapping("/publish")
    public ResponseEntity<String> publish(@RequestParam("notification") String notification) {
        kafkaProducer.sendNotification(notification);
        return ResponseEntity.ok("Message sent to the topic");
    }

}
