package com.restapilibrary.service;

import com.restapilibrary.domain.Book;
import com.restapilibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public Optional<Book> findBookById(Long bookId) {
        return bookRepository.findById(bookId);
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDb() {
        saveBook(new Book(1L, "Potop", "Henryk Sienkiewicz", LocalDate.of(1880, 1,1)));
        saveBook(new Book(2L, "Król", "Szczepan Twardoch", LocalDate.of(2018, 2,22)));
    }




}
