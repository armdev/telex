package io.project.app.river.resources;

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

/**
 *
 * @author armena
 */
@RestController
@RequestMapping("/api/v2")
@Slf4j
public class NotificationSubscriber {

    @GetMapping(value = "/notifications", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public void subscibe(@RequestParam int numConnections, ServerHttpResponse response) {

        WebClient webClient = WebClient.builder()
                .baseUrl("http://river:2027")
                .build();

        Flux.range(1, numConnections)
                .flatMap(i -> webClient.get()
                .uri("/api/v3/notifications?receiverId=" + new Random().nextLong(1, 1000))
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve()
                .bodyToFlux(String.class)
                .doOnNext(notification -> {
                    System.out.println("Received notification: " + notification);
                })
                )
                .subscribe();

    }

}
