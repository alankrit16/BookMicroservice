package com.service.books.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Publications")
public class PublicationDAO {
    @Id
    private String publisherId;
    private String publisherName;

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    @Override
    public String toString() {
        return "PublicationDAO{" +
                "publisherId='" + publisherId + '\'' +
                ", publisherName='" + publisherName + '\'' +
                '}';
    }
}
