package com.service.books.DatabaseService;

import com.service.books.Model.BooksDAO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface BookCollectionService {
    Mono<BooksDAO> registerBook(BooksDAO request);

    Flux<BooksDAO> findBooksByAuthor(String authorName, String authorId);
}
