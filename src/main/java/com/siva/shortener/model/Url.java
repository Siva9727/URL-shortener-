package com.siva.shortener.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "url_mapping",indexes = {@Index(columnList = "originalUrl"),
@Index(columnList = "shortUrl")})
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,unique = true)
    private String originalUrl;

    @Column(nullable = false,unique = true)
    private String shortUrl;
}
