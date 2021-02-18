package com.dots.dz.controller;

import lombok.*;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class FilterDTO {

    private String title;
    private String publishDate;
    private String publisher;
    private String author;
    private String genre;
    private Integer pageCount;
}
