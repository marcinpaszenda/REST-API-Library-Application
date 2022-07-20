package com.restapilibrary.controller;

import com.restapilibrary.domain.Book;
import com.restapilibrary.domain.BookDto;
import com.restapilibrary.mapper.BookMapper;
import com.restapilibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/library")
public class LibraryController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @Autowired
    public LibraryController(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @GetMapping("/allBooks")
    public List<BookDto> getBooks() {
        List<Book> books = bookService.findAllBooks();
        return bookMapper.mapToBookDtoList(books);
    }

    @GetMapping("/getBook/{bookId}")
    public BookDto getById(@PathVariable Long bookId) throws BookNotFoundException {
        return bookMapper.mapToBookDto(bookService.findBookById(bookId).
                orElseThrow(BookNotFoundException::new));
    }

    @PostMapping(value = "/newBook", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addBook(@RequestBody BookDto bookDto) {
        Book book = bookMapper.mapToBook(bookDto);
        bookService.saveBook(book);
    }

    @PutMapping(value = "/updateBook/{bookId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BookDto updateBook(@RequestBody BookDto bookDto) {
        Book book = bookMapper.mapToBook(bookDto);
        Book savedBook = bookService.saveBook(book);
        return bookMapper.mapToBookDto(savedBook);
    }

    @DeleteMapping(value = "/deleteBook/{bookId}")
    public void deleteBook(@PathVariable("bookId") Long bookId) throws BookNotFoundException {
        bookService.deleteBook(bookId);
    }
}
