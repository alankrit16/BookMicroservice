package com.service.books.DatabaseService;

import com.service.books.Exception.NoRecordFoundException;
import com.service.books.Model.BooksDAO;
import com.service.books.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class BookCollectionServiceImpl implements BookCollectionService{

    BookRepository bookRepository;
    @Autowired
    public BookCollectionServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Mono<BooksDAO> registerBook(BooksDAO request) {
        return bookRepository.save(request)
                .onErrorResume(Mono::error);
    }
    @Override
    public Flux<BooksDAO> findBooksByAuthor(String authorName,String authorId){
        return bookRepository.findBooksByAuthor(authorName,authorId)
                .switchIfEmpty(Flux.error(new NoRecordFoundException("No Books Registered By this Author")))
                .onErrorResume(Flux::error);

    }
}
