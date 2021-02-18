package com.dots.dz.controller;

import lombok.*;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    private String title;
    private Integer pageCount;
    private String author;
    private String publishDate;
    private String publisher;
    private String genre;
}
