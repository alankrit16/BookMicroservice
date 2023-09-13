package com.service.books.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface BooksService {

    Mono<Map<String,Object>> registerBook(Map<String,Object> request, String publisherId);
    Flux<Map<String,Object>>  findBooksByAuthor(String authorName, String authorId);
}
