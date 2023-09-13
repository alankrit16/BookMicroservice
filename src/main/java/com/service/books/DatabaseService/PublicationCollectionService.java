package com.service.books.DatabaseService;

import com.service.books.Model.PublicationDAO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface PublicationCollectionService {

    Mono<PublicationDAO> registerPublication(PublicationDAO publicationDAO);

    Flux<PublicationDAO> getAllPublications();

    Mono<PublicationDAO> getPublisher(String publisherId);
}
