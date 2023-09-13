package com.service.books.Controller;

import com.service.books.Exception.NoRecordFoundException;
import com.service.books.Response.MappedResponse;
import com.service.books.Service.PublicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/publication")
public class PublicationController {

    Logger log = LoggerFactory.getLogger(PublicationController.class);
    @Autowired
    PublicationService publicationService;

    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @PostMapping("/registerPublication")
    public Mono<Map<String, Object>> registerPublication(@RequestBody HashMap<String, Object> request){
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

    @GetMapping("/{publisherId}")
    public Mono<Map<String, Object>> getPublisher(@PathVariable String publisherId){
        return publicationService.getPublisher(publisherId)
                .flatMap(response->{
                    Map<String,Object> responseBody = MappedResponse.responseMap(
                            200,"SUCCESS",response.get("response"));
                    log.info(""+ response.get("response"));
                    return Mono.just(responseBody);
                })
                .onErrorResume(error->{
                    Map<String,Object> errorBody;
                    if(error instanceof NoRecordFoundException){
                        errorBody = MappedResponse.responseMap(404,
                                "FAILED",
                                error.getMessage());
                    }else{
                        errorBody = MappedResponse.responseMap(500,
                                "FAILED",
                                error.getMessage());
                    }
                    return  Mono.just(errorBody);
                });
    }
}
