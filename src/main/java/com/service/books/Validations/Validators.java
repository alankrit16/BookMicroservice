package com.service.books.Validations;

import com.service.books.Exception.BadRequestException;

import java.util.HashMap;

public class Validators {
    public static void validateRegisterPublicationRequest(HashMap<String, Object> request) throws Exception{
        if(!request.containsKey("publisherId") || request.get("publisherId").toString().isBlank()){
            throw new Exception("Bad request, no publisher id given");
        }
        else if(!request.containsKey("publisherName") || request.get("publisherName").toString().isBlank()){
            throw new Exception("Bad request, no publisher name given");
        }
    }
}
