package com.siva.shortener.controller;

import com.siva.shortener.model.Url;
import com.siva.shortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/url")
public class UrlController {

    @Autowired
    private UrlService service;
    // Modifications needed
    // can use redis

    @PostMapping("/shorten")
    public Optional<Url> shortenUrl( @RequestBody Url longUrl){
        return service.shortenUrl(longUrl.getOriginalUrl());
    }

    @GetMapping("/original/{shortUrl}")
    public Optional<Url> getOriginal(@PathVariable String shortUrl){
        return service.getOriginalUrl(shortUrl);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Url>> getAll(){
        return new ResponseEntity<>(service.getAllUrl(), HttpStatus.OK);
    }
}
