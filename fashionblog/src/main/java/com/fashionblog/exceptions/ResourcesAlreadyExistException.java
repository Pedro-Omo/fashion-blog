package com.fashionblog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourcesAlreadyExistException extends RuntimeException {

    public ResourcesAlreadyExistException(String message) {
        super(message);
    }
}




//package com.fashionblog.exceptions;
//
//public class ResourcesAlreadyExistException {
//}
