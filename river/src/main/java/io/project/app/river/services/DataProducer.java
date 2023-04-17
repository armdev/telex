package io.project.app.river.services;

import com.github.javafaker.Faker;
import io.project.app.river.domains.Notification;
import io.project.app.river.repositories.NotificationRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.scheduler.Schedulers;

/**
 *
 * @author armena
 */
@Service
@Slf4j
public class DataProducer {

    private final NotificationRepository repository;

    @Autowired
    public DataProducer(NotificationRepository repository) {
        this.repository = repository;
    }

    @Scheduled(fixedDelay = 5000)
    @Transactional  
    public void scheduleFixedDelayTask() {
        Faker faker = new Faker();
        ///log.info("Fixed delay task - " + System.currentTimeMillis() / 5000);
        Notification notification = new Notification();
        long nextLong = 0L;
        String animal="";
        List<Notification> notifications = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            notification = new Notification();
            animal = faker.animal().name();
            nextLong = new Random().nextLong(1, 10);
            notification.setMessage(animal);
            notification.setStatus("UNREAD");
            notification.setReceiverId(nextLong);
            log.info("Save " + notification.toString());
            notifications.add(notification);
        }
        repository.saveAll(notifications)
                .subscribeOn(Schedulers.boundedElastic())
                .blockLast();
    }

}
