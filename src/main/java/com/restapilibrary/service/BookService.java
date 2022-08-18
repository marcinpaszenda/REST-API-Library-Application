package com.restapilibrary.service;

import com.restapilibrary.domain.Book;
import com.restapilibrary.exceptions.BookNotFoundException;
import com.restapilibrary.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Book findBookById(final Long bookId) throws BookNotFoundException {
        return bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(final Book book) throws BookNotFoundException {
        if (bookRepository.existsById(book.getBookId())) {
            bookRepository.save(book);
            return book;
        } else {
            throw new BookNotFoundException();
        }
    }

    public void deleteBook(Long bookId) throws BookNotFoundException {
        if (bookRepository.existsById(bookId)) {
            bookRepository.deleteById(bookId);
        } else {
            throw new BookNotFoundException();
        }
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void fillDb() {
//        saveBook(new Book(1L, "Potop", "Henryk Sienkiewicz", LocalDate.of(1880, 1,1)));
//        saveBook(new Book(2L, "Król", "Szczepan Twardoch", LocalDate.of(2018, 2,22)));
//    }
}
