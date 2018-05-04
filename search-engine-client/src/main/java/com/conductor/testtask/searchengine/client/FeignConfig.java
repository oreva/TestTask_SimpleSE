package com.conductor.testtask.searchengine.client;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public HttpClient globalFeignHttpClient() {
        return HttpClientBuilder.create()
                .setMaxConnPerRoute(100)
                .setMaxConnTotal(1000)
                .build();
    }
}