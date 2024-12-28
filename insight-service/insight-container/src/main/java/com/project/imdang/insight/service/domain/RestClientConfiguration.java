package com.project.imdang.insight.service.domain;

import com.project.imdang.insight.service.application.client.ApartmentComplexApiRestClient;
import com.project.imdang.insight.service.application.client.ExternalApiException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Configuration
public class RestClientConfiguration {

    private final String API_KEY = "api-key";

    // TODO - CHECK : VS 국토교통부
    // 한국부동산원
    @Bean
    public ApartmentComplexApiRestClient apartmentComplexApiRestClient() {

        String baseUrl = "https://api.odcloud.kr/api";

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectionRequestTimeout(Duration.ofSeconds(5L));
        requestFactory.setConnectTimeout(Duration.ofSeconds(10L));

//        DefaultUriBuilderFactory uriBuilderFactory = new DefaultUriBuilderFactory(baseUrl);
//        uriBuilderFactory.setDefaultUriVariables(Map.of("serviceKey", API_KEY));
        RestClient restClient = RestClient.builder()
                .requestFactory(requestFactory)
                .defaultHeader("Authorization", API_KEY)
//                .uriBuilderFactory(uriBuilderFactory)
                .baseUrl(baseUrl)
                .defaultStatusHandler(HttpStatusCode::is4xxClientError, getDefaultErrorHandler())
                .defaultStatusHandler(HttpStatusCode::is5xxServerError, getDefaultErrorHandler())
                .build();
        return HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build()
                .createClient(ApartmentComplexApiRestClient.class);
    }

    private RestClient.ResponseSpec.ErrorHandler getDefaultErrorHandler() {
        return (request, response) -> {
            String responseMessage = new String(response.getBody().readAllBytes(), StandardCharsets.UTF_8);
            System.out.println(">>>>>>>>>>>>>>>>>>>>> " + responseMessage);
            throw new ExternalApiException(responseMessage, response.getStatusCode().value());
        };
    }
}
