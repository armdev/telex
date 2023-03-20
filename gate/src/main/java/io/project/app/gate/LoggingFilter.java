package io.project.app.gate;

import java.nio.charset.Charset;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class LoggingFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        /// log.info("LoggingFilter: runing ");
        logRequest(exchange.getRequest());

        return chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {
                    // Log the response
                    logResponse(exchange.getResponse());
                }));
    }

    private void logRequest(ServerHttpRequest request) {
        String ipAddress = request.getHeaders().getFirst("X-FORWARDED-FOR");
        // Log the request
       /// log.info("Request: " + request.getMethodValue() + " " + request.getURI() + " ipAddress " + ipAddress);
        var headers = request.getHeaders().entrySet();
       // log.info("Request:  Headers: {}", headers);
        var params = request.getQueryParams();
      ////  log.info("Request:  Params: {}", params);
      

        request.getBody();

    }

    private void logResponse(ServerHttpResponse response) {
        // Log the response
        //log.warn("Response code: {}", response.getStatusCode());
       /// log.info("Response: " + response.bufferFactory());
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
