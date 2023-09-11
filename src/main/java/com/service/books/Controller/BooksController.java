package com.service.books.Controller;

import com.service.books.Model.BooksDAO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;

@RestController
@RequestMapping("/books")
public class BooksController {
    @PostMapping("/registerBook")
    public Mono<HashMap<String, Object>> registerBook(@RequestBody HashMap<String, Object> request){
        return Mono.empty();
    }
}
