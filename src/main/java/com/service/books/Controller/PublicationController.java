package com.service.books.Controller;

import com.service.books.Service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/publication")
public class PublicationController {

    @Autowired
    PublicationService publicationService;

    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @PostMapping("/registerPublication")
    public Mono<Map<String, Object>> registerPublication(@RequestBody HashMap<String, Object> request) throws Exception {
        return publicationService.registerPublication(request)
                .flatMap(response->{
                    Map<String,Object> responseBody = new HashMap<>();
                    responseBody.put("status",201);
                    responseBody.put("message","SUCCESS");
                    responseBody.put("data", response.get("response"));
                    return Mono.just(responseBody);
                })
                .onErrorResume(error->{
                    Map<String,Object> errorBody = new HashMap<>();
                    errorBody.put("status",500);
                    errorBody.put("message","FAILED");
                    errorBody.put("error",error.getMessage());
                    return  Mono.just(errorBody);
                });
    }

    @GetMapping("/getAllPublications")
    public Flux<Map<String, Object>> getAllPublications(){
        return publicationService.getAllPublications()
                .flatMap(response->{
                    Map<String,Object> responseBody = new HashMap<>();
                    responseBody.put("status",200);
                    responseBody.put("message","SUCCESS");
                    responseBody.put("data", response.get("response"));
                    return Flux.just(responseBody);
                })
                .onErrorResume(error->{
                    Map<String,Object> errorBody = new HashMap<>();
                    errorBody.put("status",500);
                    errorBody.put("message","FAILED");
                    errorBody.put("error",error.getMessage());
                    return Flux.just(errorBody);
                });
    }
}
