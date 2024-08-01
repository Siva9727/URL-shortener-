package com.siva.shortener.service;

import com.siva.shortener.model.Url;
import com.siva.shortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    public Optional<Url> shortenUrl(String originalUrl){
        // first check the given url is already present in the db
        Url existing = urlRepository.findByOriginalUrl(originalUrl);
        if(existing != null){
            return Optional.of(existing);
        }

        String shortUrl = generateShortUrl();
        while (urlRepository.findByShortUrl(shortUrl) != null){
            shortUrl = generateShortUrl();
        }

        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setShortUrl(shortUrl);

         return Optional.of(urlRepository.save(url));

    }

    private String generateShortUrl() {
        return UUID.randomUUID().toString().substring(0,8);
    }

    public Optional<Url> getOriginalUrl(String shortUrl){
        return Optional.ofNullable(urlRepository.findByShortUrl(shortUrl));
    }
}
