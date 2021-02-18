package com.dots.dz.data.repository;

import com.dots.dz.data.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    @Query(nativeQuery = true,
            value = "select * from genre g where upper(g.title) like concat('%',upper(:title),'%')")
    List<Genre> findAllByTitle(@Param("title") String title);
}
