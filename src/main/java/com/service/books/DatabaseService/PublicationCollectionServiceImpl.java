package com.service.books.DatabaseService;

import com.service.books.Model.PublicationDAO;
import com.service.books.Repositories.PublicationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
@Service
public class PublicationCollectionServiceImpl implements PublicationCollectionService {
    Logger log = LoggerFactory.getLogger(PublicationCollectionServiceImpl.class);
    PublicationRepository publicationRepository;
    @Autowired
    public PublicationCollectionServiceImpl(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    @Override
    public Mono<PublicationDAO> registerPublication(PublicationDAO publicationDAO) {
        return publicationRepository.save(publicationDAO).flatMap(object->{
            log.info(object.toString());
            return Mono.just(object);
                })
                .onErrorResume(Mono::error);
    }

    @Override
    public Flux<PublicationDAO> getAllPublications() {
        return publicationRepository.findAllPublications()
                .flatMap(responseObj->{
            log.info(responseObj.toString());
            return Flux.just(responseObj);
        })
                .onErrorResume(Mono::error);
    }
}
