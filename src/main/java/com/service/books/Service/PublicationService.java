package com.service.books.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

public interface PublicationService {
    Mono<Map<String,Object>> registerPublication(HashMap<String, Object> request);

    Flux<Map<String,Object>> getAllPublications();

    Mono<Map<String,Object>> getPublisher(String publisherId);
}
