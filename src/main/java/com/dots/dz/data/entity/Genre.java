package com.dots.dz.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@ToString
@Entity
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false, length = 1000)
    private String title;
    @OneToMany
    private List<Book> book;
}
