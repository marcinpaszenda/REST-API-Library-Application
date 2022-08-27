package com.restapilibrary.controller;

import com.restapilibrary.domain.Book;
import com.restapilibrary.dto.BookDto;
import com.restapilibrary.exceptions.BookNotFoundException;
import com.restapilibrary.mapper.BookMapper;
import com.restapilibrary.repository.BookCopyRepository;
import com.restapilibrary.repository.BookRepository;
import com.restapilibrary.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/books")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;
    private final BookCopyRepository bookCopyRepository;
    private final BookRepository bookRepository;

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<Book> books = bookService.findAllBooks();
        return ResponseEntity.ok(bookMapper.mapToBookDtoList(books));
    }

    @GetMapping("{bookId}")
    public ResponseEntity<BookDto> getBook(@PathVariable Long bookId) throws BookNotFoundException {
        return ResponseEntity.ok(bookMapper.mapToBookDto(bookService.findBookById(bookId)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> addBook(@RequestBody BookDto bookDto) {
        Book book = bookMapper.mapToBook(bookDto);
        bookService.saveBook(book);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(book);

        //         uriComponents zwraca w headers nagłówek location

//        UriComponents uriComponents = UriComponentsBuilder
//                .fromHttpUrl("http://Localhost:8080/books/{bookId}")
//                .buildAndExpand(book.getBookId());
//        return ResponseEntity.created(uriComponents.toUri())
//                .body(book);

    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto) throws BookNotFoundException {
        Book book = bookMapper.mapToBook(bookDto);
        Book updatedBook = bookService.updateBook(book);
        bookMapper.mapToBookDto(updatedBook);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long bookId) throws BookNotFoundException {
        bookService.deleteBook(bookId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
