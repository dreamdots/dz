package com.dots.dz.data.entity;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false, length = 1000)
    private String title;
    @Column(nullable = false, length = 1000)
    private String author;
    @Column(name = "publish_date")
    private String publishDate;
    @Column(length = 2000)
    private String publisher;
    @Column(name = "page_count")
    private Integer pageCount;
    @ManyToOne
    private Genre genre;
}
