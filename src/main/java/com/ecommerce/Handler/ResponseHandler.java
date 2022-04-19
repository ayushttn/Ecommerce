package com.ecommerce.Handler;

import com.ecommerce.Entities.Seller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status){
        Map<String, Object> map=new HashMap<>();
        map.put("message",message);
        map.put("status",status.value());
        map.put("date", new Date());
        return new ResponseEntity<Object>(map,status);
    }
}