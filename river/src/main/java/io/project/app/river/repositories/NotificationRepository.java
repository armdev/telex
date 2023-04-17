package io.project.app.river.repositories;

import io.project.app.river.domains.Notification;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface NotificationRepository extends ReactiveMongoRepository<Notification, String> {

    Flux<Notification> findByTop10ByStatusAndReceiverId(String status, Long receiverId);

}
