package io.project.app.river.resources;

import io.project.app.river.domains.Notification;
import io.project.app.river.repositories.NotificationRepository;

import java.time.Duration;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.Scannable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.ParallelFlux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.Many;
import reactor.util.concurrent.Queues;

/**
 *
 * @author armena
 */
@RestController
@RequestMapping("/api/v3")
@Slf4j
public class NotificatioSubscribersController {

    private final NotificationRepository repository;
    private final ConcurrentMap<Long, Integer> subscriberCount;

    private final Many<Integer> sink;

    @Autowired
    public NotificatioSubscribersController(NotificationRepository repository) {
        this.repository = repository;

        this.subscriberCount = new ConcurrentHashMap<>();
        this.sink = Sinks.many().multicast().onBackpressureBuffer(Queues.SMALL_BUFFER_SIZE, false);
    }

    /**
     *
     * @param receiverId
     * @param response
     *
     * In this implementation, we modify the streamNotifications method to take
     * a receiverId parameter instead of using the session ID. We then use the
     * receiverId parameter to compute the subscriber count instead of the
     * session ID.
     *
     * We use the compute method of the ConcurrentHashMap to update the
     * subscriber count for the given receiverId. If the subscriber count does
     * not exist yet, we initialize it to 1. If it already exists, we increment
     * it by 1.
     *
     * We then use the get method of the ConcurrentHashMap to retrieve the
     * subscriber count for the given receiverId, and add it to the response
     * headers under the X-Subscriber-Count key.
     *
     * The rest of the implementation is the same as before, where we use the
     * findByStatusAndReceiverId method of the ReactiveMongoRepository to
     * retrieve unread notifications for the given receiverId, mark them as
     * read, and then save them back to the database. We also use the take
     * operator to limit the duration of the stream to 10 minutes, and the
     * doFinally operator to update the subscriber count when the stream is
     * terminated. Finally, we use the repeat operator to restart the stream
     * once it has completed.
     * @return
     */
    @GetMapping(value = "/notifications", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ParallelFlux<Notification> streamNotifications(@RequestParam Long receiverId, ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().setContentType(MediaType.TEXT_EVENT_STREAM);
        subscriberCount.compute(receiverId, (key, value) -> value == null ? 1 : value + 1);
        sink.tryEmitNext(subscriberCount.get(receiverId));

        response.getHeaders().add("X-Subscriber-Count", Integer.toString(subscriberCount.get(receiverId)));
        return Flux.interval(Duration.ofSeconds(1))
                .flatMap(i -> repository.findTop10ByStatusAndReceiverId("UNREAD", receiverId))
                .flatMap(notification -> {
                    notification.setStatus("READ");
                    return repository.save(notification).thenReturn(notification);
                })
                .take(Duration.ofMinutes(5))
                .onBackpressureDrop()
                .doFinally(signalType -> {
                    subscriberCount.compute(receiverId, (key, value) -> value == 1 ? null : value - 1);
                    sink.tryEmitNext(subscriberCount.get(receiverId));
                })
                .repeat().parallel(500);
    }

    @GetMapping(value = "/subscirbers")
    public int count() {
//        for (Long next : subscriberCount.keySet()) {
//            log.info("Next is " + next);
//            return next;
//        }
        return sink.currentSubscriberCount();
    }

}
