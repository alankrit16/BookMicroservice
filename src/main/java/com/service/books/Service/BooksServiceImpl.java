package com.service.books.Service;

import com.service.books.DatabaseService.BookCollectionService;
import com.service.books.DatabaseService.PublicationCollectionService;
import com.service.books.Model.BooksDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
@Service
public class BooksServiceImpl implements BooksService{
    BookCollectionService bookCollectionService;
    PublicationCollectionService publicationCollectionService;

    @Autowired
    public BooksServiceImpl(BookCollectionService bookCollectionService, PublicationCollectionService publicationCollectionService) {
        this.bookCollectionService = bookCollectionService;
        this.publicationCollectionService = publicationCollectionService;
    }

    @Override
    public Mono<Map<String, Object>> registerBook(Map<String, Object> request, String publisherId) {
        return publicationCollectionService.getPublisher(publisherId)
                .flatMap(publicationObj->{
                    BooksDAO booksDAO = new BooksDAO();
                    booksDAO.setPublisherName(publicationObj.getPublisherName());
                    booksDAO.setAuthorId(request.get("authorId").toString());
                    booksDAO.setAuthorName(request.get("authorName").toString());
                    booksDAO.setBookId(request.get("bookId").toString());
                    booksDAO.setBookName(request.get("bookName").toString());
                    return bookCollectionService.registerBook(booksDAO)
                            .flatMap(responseObj->{
                                Map<String,Object> response = new HashMap<>();
                                response.put("response",responseObj);
                                return Mono.just(response);
                            })
                            .onErrorResume(Mono::error);
                }).onErrorResume(Mono::error);
    }

    @Override
    public Flux<Map<String,Object>> findBooksByAuthor(String authorName, String authorId){
        return bookCollectionService.findBooksByAuthor(authorName,authorId)
                .flatMap(responseObj->{
                    Map<String,Object> response = new HashMap<>();
                    response.put("response",responseObj);
                    return Flux.just(response);
                }).onErrorResume(Flux::error);
    }
}
