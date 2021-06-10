package com.hgstudy.apigatewayservice.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest; // reactive : rx자바 , 스프링 5 웹 플러긋 기능
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {

   public CustomFilter(){
       super(Config.class);
   }
    public static class Config{
        //Put the configuration properties
    }

    @Override
    public GatewayFilter apply(Config config) {
       //Custom Pre Filter
       return ((exchange, chain) -> {
           ServerHttpRequest request = exchange.getRequest();
           ServerHttpResponse response = exchange.getResponse();

           log.info("Custom Pre Filter : request id -> {}", request.getId());

           //Custom Post Filter
           //Mono : 웹 플럭스에서 지원 , 비동기 방식에서 단위값 전달할 때 사용
           return chain.filter(exchange).then(Mono.fromRunnable(() -> {
               log.info("Custom Post filter : response code -> {}", response.getStatusCode());

           }));
       });
    }
}
