package com.service.books.Repositories;

import com.service.books.Model.PublicationDAO;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PublicationRepository extends ReactiveMongoRepository<PublicationDAO,String> {

    @Query("{}")
    Flux<PublicationDAO> findAllPublications();
    @Query("{_id:?0}")
    Mono<PublicationDAO> findPublisher(String publisherId);
}
