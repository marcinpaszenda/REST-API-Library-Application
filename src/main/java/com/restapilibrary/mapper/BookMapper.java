package com.restapilibrary.mapper;

import com.restapilibrary.domain.Book;
import com.restapilibrary.dto.BookDto;
import com.restapilibrary.repository.BookCopyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookMapper {

    BookCopyRepository bookCopyRepository;

    public Book mapToBook(final BookDto bookDto) {
        return new Book(
                bookDto.getBookId(),
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getPublicationYear()
//                (List<BookCopy>) Optional.ofNullable(
//                        bookCopyRepository.findAllById(bookDto.getBookCopiesId()))
//                        .orElse(Collections.emptyList())
        );
    }

    public BookDto mapToBookDto(final Book book) {
        return new BookDto(
                book.getBookId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublicationYear(),
                book.getBookCopyList().isEmpty()? Collections.emptyList() : book.getBookCopyList().stream()
                                .map(bookCopy -> bookCopy.getBookCopyId())
                                .collect(Collectors.toList())
        );
    }

    public List<BookDto> mapToBookDtoList(final List<Book> bookList) {
        return bookList.isEmpty()? Collections.emptyList() : bookList.stream()
                .map(this::mapToBookDto)
                .collect(Collectors.toList());
    }
}
