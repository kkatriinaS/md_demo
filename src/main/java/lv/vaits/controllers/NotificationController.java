package lv.vaits.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lv.vaits.kafka.KafkaProducer;
import lv.vaits.models.Notification;
import lv.vaits.repos.INotificationRepo;
import lv.vaits.services.INotificationServices;


@RestController
@RequestMapping("/api/v1/kafka")
public class NotificationController {
	
	@Autowired
	private INotificationServices notificationServices;
	
    private KafkaProducer kafkaProducer;
    private INotificationRepo notificationRepo; // Inject the repository

    public NotificationController(KafkaProducer kafkaProducer, INotificationRepo notificationRepo) {
        this.kafkaProducer = kafkaProducer;
        this.notificationRepo = notificationRepo;
    }

    // localhost:8080/api/v1/kafka/notifications/showAll
    @GetMapping("/notifications/showAll")
    public String getNotificationsFunc(Model model) {
    	model.addAttribute("allNotifications", notificationServices.retrieveAllNotifications());
        return "all-notifications-page" ;// Use the repository instance to fetch messages
    }

    // localhost:8080/api/v1/kafka/publish?notification=hello world
    @GetMapping("/publish")
    public ResponseEntity<String> publish(@RequestParam("notification") String notification) {
        kafkaProducer.sendNotification(notification);
        return ResponseEntity.ok("Message sent to the topic");
    }

}
