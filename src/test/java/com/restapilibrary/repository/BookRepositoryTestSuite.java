package com.restapilibrary.repository;

import com.restapilibrary.domain.Book;
import com.restapilibrary.domain.BookCopy;
import com.restapilibrary.domain.BookStatus;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTestSuite {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testFindAllBooks() {
        //Given
        Book book1 = new Book();
        Book book2 = new Book();
        //When
        bookRepository.save(book1);
        bookRepository.save(book2);
        //Then
        assertEquals(2, bookRepository.findAll().size());
        //CleanUp
        bookRepository.deleteById(book1.getBookId());
        bookRepository.deleteById(book2.getBookId());
    }

    @Test
    public void testFindBookById() {
        //Given
        Book book1 = new Book();
        Book book2 = new Book();
        //When
        bookRepository.save(book1);
        bookRepository.save(book2);
        Long book1Id = book1.getBookId();
        Long book2Id = book2.getBookId();
        Optional<Book> foundBook1 = bookRepository.findById(book1Id);
        Optional<Book> foundBook2 = bookRepository.findById(book2Id);
        //Then
        assertNotNull(foundBook1);
        assertNotNull(foundBook2);
        assertEquals(book1Id, foundBook1.get().getBookId());
        assertEquals(book2Id, foundBook2.get().getBookId());
        //CleanUp
        bookRepository.deleteById(book1.getBookId());
        bookRepository.deleteById(book2.getBookId());
    }

    @Test
    public void testRelationsWithBook() {
        //Given
        Book bookOne = new Book("Król", "Szczepan Twardoch", LocalDate.of(2018, 12, 02));
        Book bookTwo = new Book("Ogniem i mieczem", "Henryk Sienkiewicz", LocalDate.of(1884, 05, 22));

        BookCopy bookCopyOne = new BookCopy(BookStatus.AVAILABLE);
        BookCopy bookCopyTwo = new BookCopy(BookStatus.DESTROYED);
        BookCopy bookCopyThree = new BookCopy(BookStatus.BORROWED);
        BookCopy bookCopyFour = new BookCopy(BookStatus.LOST);

        List<BookCopy> bookCopyListOne = new ArrayList<>();
        bookCopyListOne.add(bookCopyOne);
        bookCopyListOne.add(bookCopyTwo);

        List<BookCopy> bookCopyListTwo = new ArrayList<>();
        bookCopyListTwo.add(bookCopyThree);
        bookCopyListTwo.add(bookCopyFour);

        //When
        bookOne.setBookCopyList(bookCopyListOne);
        bookTwo.setBookCopyList(bookCopyListTwo);

        //Then
        assertEquals(2, bookOne.getBookCopyList().size());
        assertEquals(2, bookTwo.getBookCopyList().size());
        assertEquals("Król", bookOne.getTitle());
        assertEquals("Henryk Sienkiewicz", bookTwo.getAuthor());
        assertEquals(LocalDate.of(2018, 12, 02), bookOne.getPublicationYear());

        //CleanUp
        bookRepository.deleteAll();
    }

}
