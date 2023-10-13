package lv.vaits.repos;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.vaits.models.Notification;

public interface INotificationRepo extends CrudRepository<Notification, Long>{

	//ArrayList<Notification> retrieveAllNotifications();

}
