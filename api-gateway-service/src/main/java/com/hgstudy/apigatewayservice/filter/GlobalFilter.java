package com.hgstudy.apigatewayservice.filter;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class GlobalFilter  extends AbstractGatewayFilterFactory<GlobalFilter.Config> {
    public GlobalFilter(){
        super(Config.class);
    }

    @Getter
    @Setter
    public static class Config{
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }

    @Override
    public GatewayFilter apply(GlobalFilter.Config config) {
        return (((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Global Filter BaseMessage : {}", config.getBaseMessage());

            if(config.isPreLogger()){
                log.info("Global Filter Start : request id -> {}", request.getId());
            }

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                if(config.isPostLogger()){
                    log.info("Global Filter End : response code -> {}",response.getStatusCode());
                }
            }));
        }));
    }
}
