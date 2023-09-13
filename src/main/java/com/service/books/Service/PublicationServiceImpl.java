package com.service.books.Service;

import com.service.books.DatabaseService.PublicationCollectionService;
import com.service.books.Exception.BadRequestException;
import com.service.books.Exception.NoRecordFoundException;
import com.service.books.Model.PublicationDAO;
import com.service.books.Validations.Validators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
@Service
public class PublicationServiceImpl implements PublicationService{
    Logger log = LoggerFactory.getLogger(PublicationServiceImpl.class);
    @Autowired
    PublicationCollectionService publicationCollectionService;

    public PublicationServiceImpl(PublicationCollectionService publicationCollectionService) {
        this.publicationCollectionService = publicationCollectionService;
    }

    @Override
    public Mono<Map<String, Object>> registerPublication(HashMap<String, Object> request){
        try {
            Validators.validateRegisterPublicationRequest(request);
        } catch (RuntimeException e) {
            return Mono.error(new BadRequestException(e.getMessage()));
        }
        PublicationDAO publicationDAO = new PublicationDAO();
        publicationDAO.setPublisherId((String) request.get("publisherId"));
        publicationDAO.setPublisherName((String) request.get("publisherName"));
        return publicationCollectionService.registerPublication(publicationDAO)
                .flatMap(responseObject->{
                    Map<String,Object> response = new HashMap<>();
                    response.put("response",responseObject);
                    return Mono.just(response);
                })
                .onErrorResume(Mono::error);
    }

    @Override
    public Flux<Map<String, Object>> getAllPublications() {
        return publicationCollectionService.getAllPublications().flatMap(responseObject->{
            Map<String,Object> response = new HashMap<>();
            response.put("response",responseObject);
            return Flux.just(response);
        }).onErrorResume(Flux::error);
    }

    @Override
    public Mono<Map<String, Object>> getPublisher(String publisherId) {
        return publicationCollectionService.getPublisher(publisherId)
                .flatMap(responseObj->{
                    Map<String,Object> response = new HashMap<>();
                    response.put("response",responseObj);
                    return Mono.just(response);
                })
                .onErrorResume(Mono::error);
    }


}
