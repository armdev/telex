package io.project.app.river.resources;

import io.project.app.river.domains.Notification;
import io.project.app.river.repositories.NotificationRepository;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 *
 * @author armena
 */
@RestController
@RequestMapping("/api/v2")
public class NotificationController {

    private final NotificationRepository repository;

    @Autowired
    public NotificationController(NotificationRepository repository) {
        this.repository = repository;
    }

    /**
     *
     * @param receiverId
     * @param response
     * @return
     *
     * In this controller, we define a GET endpoint at '/notifications' that
     * produces a text event stream. We set the response status code to OK and
     * the content type to text event stream. We then create a Flux that emits
     * an item every second, flatMap it to find notifications by status
     * ('UNREAD'), and flatMap each notification to update its status to 'READ'
     * and save it in the database. We then limit the stream to emit items for
     * 10 minutes and repeat the stream indefinitely.
     *
     * With this implementation, up to 1000 subscribers can connect to the
     * '/notifications' endpoint simultaneously and receive updates for 10
     * minutes. Each subscriber will only receive notifications with a status of
     * 'UNREAD', and once a notification is received, its status will be updated
     * to 'READ' in the database.
     */
    @GetMapping(value = "/notifications", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Notification> streamNotifications(@RequestParam Long receiverId, ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().setContentType(MediaType.TEXT_EVENT_STREAM);
        return Flux.interval(Duration.ofSeconds(1))
                .flatMap(i -> repository.findByTop10ByStatusAndReceiverId("UNREAD", receiverId))
                .flatMap(notification -> {
                    notification.setStatus("READ");
                    return repository.save(notification).thenReturn(notification);
                })
                .take(Duration.ofMinutes(10))//timeout
                .repeat();
    }

}
