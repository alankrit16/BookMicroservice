package com.service.books.Controller;

import com.service.books.Exception.NoRecordFoundException;
import com.service.books.Response.MappedResponse;
import com.service.books.Service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BooksController {

    @Autowired
    BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }
    @PostMapping("/registerBook/{publisherId}")
    public Mono<Map<String, Object>> registerBook(@RequestBody HashMap<String, Object> request, @PathVariable String publisherId){
        return booksService.registerBook(request,publisherId)
                .flatMap(
                response->{
                    Map<String,Object> responseBody = MappedResponse.responseMap(
                            201,"SUCCESS",response.get("response"));
                    return Mono.just(responseBody);
                })
                .onErrorResume(error->{
            Map<String,Object> errorBody = MappedResponse.responseMap(
                    500,"FAILED",error.getMessage());
            return  Mono.just(errorBody);
        });
    }

    @GetMapping("/findBookByAuthor")
    public Flux<Map<String,Object>> findBookByAuthor(@RequestParam(name="authorName", required = true)
                                                         String authorName,
                                                     @RequestParam(name="authorId")
                                                     String authorId){
        return booksService.findBooksByAuthor(authorName,authorId)
                .flatMap(response->{
                    return Flux.just(MappedResponse.responseMap(
                            200,"SUCCESS",response.get("response")));})
                .onErrorResume(error->{
                    if(error instanceof NoRecordFoundException)
                        return Flux.just(MappedResponse.responseMap(404,"FAILED",error.getMessage()));
                    return Flux.just(MappedResponse.responseMap(500,"FAILED",error.getMessage()));
                });
    }


}
