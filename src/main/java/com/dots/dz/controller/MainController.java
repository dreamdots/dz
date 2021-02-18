package com.dots.dz.controller;

import com.dots.dz.data.entity.Book;
import com.dots.dz.data.repository.BookRepository;
import com.dots.dz.data.repository.GenreRepository;
import com.dots.dz.data.transformer.DtoTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(path = "api")
//@CrossOrigin(originPatterns = "http://localhost:63342/")
@RequiredArgsConstructor(onConstructor_= @Autowired)
public class MainController {
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final DtoTransformer dtoTransformer;

    private final EntityManager em;

    @CrossOrigin
    @GetMapping(path = "/book/findAll")
    public List<BookDTO> findAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(dtoTransformer::toBookDTO)
                .collect(Collectors.toUnmodifiableList());
    }

    @CrossOrigin
    @GetMapping(path = "/genre/findAll")
    public List<GenreDTO> findAllGenres() {
        return genreRepository.findAll()
                .stream()
                .map(dtoTransformer::toGenreDTO)
                .collect(Collectors.toUnmodifiableList());
    }

    @CrossOrigin
    @PostMapping(path = "/book/add")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addBook(@RequestBody BookDTO dto) {
        log.info("Save book -> " + dto.toString());
        bookRepository.save(dtoTransformer.toBook(dto));
    }

    @CrossOrigin
    @PostMapping(path = "/genre/add")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addGenre(@RequestBody GenreDTO dto) {
        log.info("Save genre -> " + dto.toString());
        genreRepository.save(dtoTransformer.toGenre(dto));
    }

    @CrossOrigin
    @SuppressWarnings("unchecked")
    @PostMapping(path = "/book/find")
    public List<BookDTO> findBook(@RequestBody FilterDTO filterDTO) {
        log.info("Find book by params -> " + filterDTO.toString());
        List<Book> entities = em.createNativeQuery(buildFilterQuery(filterDTO), Book.class).getResultList();
        return entities
                .stream()
                .map(dtoTransformer::toBookDTO)
                .collect(Collectors.toUnmodifiableList());
    }

    private String buildFilterQuery(FilterDTO filterDTO) {
        String select = " select * ";
        String from = " from book b ";
        String join = "";
        String where = " where 1=1 ";

        if (filterDTO.getGenre() != null && !filterDTO.getGenre().isEmpty()) {
            join = " join genre_book gb on b.id = gb.book_id " +
                    " join genre g on b.genre_id = g.id ";
            where += " and upper(g.title) like upper('%" + filterDTO.getGenre() + "%') ";
        }

        if (filterDTO.getTitle() != null && !filterDTO.getTitle().isEmpty()) {
            where += " and upper(b.title) like upper('%" + filterDTO.getTitle() + "%') ";
        }

        if (filterDTO.getPageCount() != null && filterDTO.getPageCount() != 0) {
            where += " and page_count = " + filterDTO.getPageCount();
        }

        if (filterDTO.getAuthor() != null && !filterDTO.getAuthor().isEmpty()) {
            where += " and upper(author) like upper('%" + filterDTO.getAuthor() + "%') ";
        }

        if (filterDTO.getPublisher() != null && !filterDTO.getPublishDate().isEmpty()) {
            where += " and upper(publisher) like upper('%" + filterDTO.getPublisher() + "%') ";
        }

        if (filterDTO.getPublishDate() != null && !filterDTO.getPublishDate().isEmpty()) {
            where += " and publish_date like ('%" + filterDTO.getPublisher() + "%') ";
        }

        return select + from + join + where;
    }

    @CrossOrigin
    @PostMapping(path = "/genre/find")
    public List<GenreDTO> findGenre(@RequestBody FilterDTO filterDTO) {
        log.info("Find genre by params -> " + filterDTO.toString());
        if (filterDTO.getTitle() != null) {
            return genreRepository.findAllByTitle(filterDTO.getTitle())
                    .stream()
                    .map(dtoTransformer::toGenreDTO)
                    .collect(Collectors.toUnmodifiableList());
        } else return Collections.emptyList();
    }
}
