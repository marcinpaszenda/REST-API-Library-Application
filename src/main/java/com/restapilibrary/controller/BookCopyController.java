package com.restapilibrary.controller;

import com.restapilibrary.domain.Book;
import com.restapilibrary.domain.BookCopy;
import com.restapilibrary.dto.BookCopyDto;
import com.restapilibrary.exceptions.BookCopyNotFoundException;
import com.restapilibrary.mapper.BookCopyMapper;
import com.restapilibrary.repository.BookCopyRepository;
import com.restapilibrary.repository.BookRepository;
import com.restapilibrary.service.BookCopyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/bookCopies")
public class BookCopyController {

    private final BookCopyService bookCopyService;
    private final BookCopyMapper bookCopyMapper;
    private final BookCopyRepository bookCopyRepository;
    private final BookRepository bookRepository;

    @GetMapping
    public ResponseEntity<List<BookCopyDto>> getAllBookCopies() {
        List<BookCopy> bookCopies = bookCopyService.findAllBookCopies();
        return ResponseEntity.ok(bookCopyMapper.mapToBookCopyDtoList(bookCopies));
    }

    @GetMapping("{bookCopyId}")
    public ResponseEntity<BookCopyDto> getBookCopy(@PathVariable Long bookCopyId) throws BookCopyNotFoundException {
        return ResponseEntity.ok(bookCopyMapper.mapToBookCopyDto(bookCopyService.findBookCopyById(bookCopyId)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookCopy> addBookCopy(@RequestBody BookCopyDto bookCopyDto) {
        BookCopy bookCopy = bookCopyMapper.mapToBookCopy(bookCopyDto);
        bookCopyService.saveBookCopy(bookCopy);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookCopy);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookCopyDto> updateBookCopy(@RequestBody BookCopyDto bookCopyDto) throws BookCopyNotFoundException {
        BookCopy bookCopy = bookCopyMapper.mapToBookCopy(bookCopyDto);
        BookCopy updatedBookCopy = bookCopyService.updateBookCopy(bookCopy);
        bookCopyMapper.mapToBookCopyDto(updatedBookCopy);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{bookCopyId}/books/{bookId}")
    BookCopy assignBookToBookCopy(@PathVariable Long bookCopyId, @PathVariable Long bookId) {
        BookCopy bookCopy = bookCopyRepository.findById(bookCopyId).get();
        Book book = bookRepository.findById(bookId).get();
        bookCopy.setBook(book);
        return bookCopyRepository.save(bookCopy);
    }

    @DeleteMapping(value = "{bookCopyId}")
    public ResponseEntity<Void> deleteBookCopy(@PathVariable Long bookCopyId) throws BookCopyNotFoundException {
        bookCopyService.deleteBookCopy(bookCopyId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
