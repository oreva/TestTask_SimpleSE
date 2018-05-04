package com.conductor.testtask.searchengine.client.internalapi;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import feign.Feign;
import feign.Logger;
import feign.Response;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import feign.httpclient.ApacheHttpClient;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

@Component
public class InternalApi implements InitializingBean {

    @Value("${searchEngine.access.url}")
    private String searchEngineUrl;

    @Value("${searchEngine.access.username}")
    private String searchEngineAccessUsername;

    @Value("${searchEngine.access.password}")
    private String searchEngineAccessPassword;

    @Autowired
    private HttpClient globalFeignHttpClient;

    private SearchEngineApi searchEngineApi;

    @Override
    public void afterPropertiesSet() throws Exception {
        searchEngineApi = Feign.builder()
            .client(new ApacheHttpClient(globalFeignHttpClient))
            .requestInterceptor(new BasicAuthRequestInterceptor(searchEngineAccessUsername, searchEngineAccessPassword))
            .errorDecoder(new SearchEngineApi.SearchEngineApiErrorDecoder(new ErrorDecoder.Default()))
            .encoder(new JacksonEncoder())
            .decoder(new StringAsStringDecoder(new JacksonDecoder()))
            .logger(new Slf4jLogger(SearchEngineApi.class))
            .logLevel(Logger.Level.FULL)
            .target(SearchEngineApi.class, UriComponentsBuilder
                                        .fromHttpUrl(searchEngineUrl)
                                        .toUriString()
            );
    }

    public SearchEngineApi getSearchEngineApi() {
        return searchEngineApi;
    }

    private static class StringAsStringDecoder implements Decoder {
        final Decoder delegate;

        StringAsStringDecoder(Decoder delegate) {
            this.delegate = delegate;
        }

        @Override
        public Object decode(Response response, Type type) throws IOException {
            if (type == String.class) {
                return CharStreams.toString(new InputStreamReader(
                        response.body().asInputStream(), Charsets.UTF_8));
            }
            return delegate.decode(response, type);
        }
    }
}
