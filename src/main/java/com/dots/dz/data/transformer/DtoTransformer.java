package com.dots.dz.data.transformer;

import com.dots.dz.controller.BookDTO;
import com.dots.dz.controller.GenreDTO;
import com.dots.dz.data.entity.Book;
import com.dots.dz.data.entity.Genre;
import com.dots.dz.data.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_= @Autowired)
public class DtoTransformer {
    private final GenreRepository genreRepository;

    public BookDTO toBookDTO(Book book) {
        BookDTO dto = new BookDTO();

        dto.setAuthor(book.getAuthor());
        dto.setPublishDate(book.getPublishDate());
        dto.setPageCount(book.getPageCount());
        dto.setTitle(book.getTitle());
        dto.setPublisher(book.getPublisher());
        if (book.getGenre() != null) {
            dto.setGenre(book.getGenre().getTitle());
        }

        return dto;
    }

    public Book toBook(BookDTO bookDTO) {
        Book book = new Book();

        book.setAuthor(bookDTO.getAuthor());
        book.setPublishDate(bookDTO.getPublishDate());
        book.setPublisher(bookDTO.getPublisher());
        book.setTitle(bookDTO.getTitle());

        List<Genre> genre = genreRepository.findAllByTitle(bookDTO.getGenre());
        if (!genre.isEmpty()) {
            book.setGenre(genre.get(0));
        }

        return book;
    }

    public GenreDTO toGenreDTO(Genre genre) {
        GenreDTO dto = new GenreDTO();
        dto.setTitle(genre.getTitle());
        return dto;
    }

    public Genre toGenre(GenreDTO dto) {
        Genre genre = new Genre();
        genre.setTitle(dto.getTitle());
        return genre;
    }
}
