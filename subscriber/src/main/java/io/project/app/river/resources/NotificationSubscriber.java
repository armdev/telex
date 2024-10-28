package io.project.app.river.resources;

import java.time.Duration;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 * @author armena
 */
@RestController
@RequestMapping("/api/v2")
@Slf4j
public class NotificationSubscriber {

    @GetMapping(value = "/notifications", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> subscribe(@RequestParam int numConnections) {

        WebClient webClient = WebClient.builder()
                .baseUrl("http://river:2027")
                .build();

        return Flux.range(1, numConnections)
                .flatMap(i -> webClient.get()
                .uri("/api/v3/notifications?receiverId=" + new Random().nextLong(1, 100))
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve()
                .bodyToFlux(String.class)
                .take(Duration.ofMinutes(10))
                .doFinally(signalType -> {
                    log.info("Finally " + signalType.name());
                })
                .doOnCancel(() -> {
                    log.error("Client canceled ");
                })
                .doOnComplete(() -> {
                    log.info("Client completed ");
                })
                .onErrorResume(e -> {
                    if (e instanceof Exception) {
                        log.warn("Failed to get myStuff " + e.getMessage());
                    } else {
                        log.error("Failed to get myStuff, other");
                    }
                    return Mono.just("Encountered an exception");
                })
                .doOnNext(notification -> {
                    log.info("Received notification: " + notification);
                })
                );
    }

}
