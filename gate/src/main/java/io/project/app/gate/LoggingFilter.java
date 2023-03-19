package io.project.app.gate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class LoggingFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // Log the request
         System.out.println("!filter: runing ");
        log.info("filter: runing ");
        logRequest(exchange.getRequest());

        return chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {
                    // Log the response
                    logResponse(exchange.getResponse());
                }));
    }

    private void logRequest(ServerHttpRequest request) {
        // Log the request
        System.out.println("Request: " + request.getMethodValue() + " " + request.getURI());
    }

    private void logResponse(ServerHttpResponse response) {
        // Log the response
        log.info("Response: " + response.getStatusCode() + " " + response.bufferFactory().toString());
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
