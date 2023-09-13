package com.service.books.Repositories;

import com.service.books.Model.BooksDAO;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;

public interface BookRepository extends ReactiveMongoRepository<BooksDAO,String> {

    @Query("{'authorName':?0, 'authorId':?1}")
    Flux<BooksDAO> findBooksByAuthor(String authorName,String authorId);
}
