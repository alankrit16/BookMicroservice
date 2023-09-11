package com.service.books.Repositories;

import com.service.books.Model.BooksDAO;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BookRepository extends ReactiveMongoRepository<BooksDAO,String> {
}
