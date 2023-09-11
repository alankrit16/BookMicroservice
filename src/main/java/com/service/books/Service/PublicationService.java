package com.service.books.Service;

import io.netty.handler.codec.http2.Http2LifecycleManager;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

public interface PublicationService {
    Mono<Map<String,Object>> registerPublication(HashMap<String, Object> request) throws Exception;

    Flux<Map<String,Object>> getAllPublications();
}
