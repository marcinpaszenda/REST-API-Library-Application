package com.restapilibrary.repository;

import com.restapilibrary.domain.*;
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
public class BookCopyRepositoryTestSuite {

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Test
    public void testFindAllBookCopies() {
        //Given
        BookCopy bookCopy1 = new BookCopy();
        BookCopy bookCopy2 = new BookCopy();
        BookCopy bookCopy3 = new BookCopy();

        //When
        bookCopyRepository.save(bookCopy1);
        bookCopyRepository.save(bookCopy2);
        bookCopyRepository.save(bookCopy3);

        //Then
        assertEquals(3, bookCopyRepository.findAll().size());
        //CleanUp
        bookCopyRepository.deleteById(bookCopy1.getBookCopyId());
        bookCopyRepository.deleteById(bookCopy2.getBookCopyId());
        bookCopyRepository.deleteById(bookCopy3.getBookCopyId());
    }

    @Test
    public void testFindBookCopiesById() {
        //Given
        BookCopy bookCopyOne = new BookCopy();
        BookCopy bookCopyTwo = new BookCopy();
        BookCopy bookCopyThree= new BookCopy();

        //When
        bookCopyRepository.save(bookCopyOne);
        bookCopyRepository.save(bookCopyTwo);
        bookCopyRepository.save(bookCopyThree);

        Long bookCopyOneId = bookCopyOne.getBookCopyId();
        Long bookCopyTwoId = bookCopyTwo.getBookCopyId();
        Long bookCopyThreeId = bookCopyThree.getBookCopyId();

        Optional<BookCopy> foundBookCopyOne = bookCopyRepository.findById(bookCopyOneId);
        Optional<BookCopy> foundBookCopyTwo = bookCopyRepository.findById(bookCopyTwoId);
        Optional<BookCopy> foundBookCopyThree = bookCopyRepository.findById(bookCopyThreeId);

        //Then
        assertNotNull(foundBookCopyOne);
        assertNotNull(foundBookCopyTwo);
        assertNotNull(foundBookCopyThree);

        assertEquals(bookCopyOneId, foundBookCopyOne.get().getBookCopyId());
        assertEquals(bookCopyTwoId, foundBookCopyTwo.get().getBookCopyId());
        assertEquals(bookCopyThreeId, foundBookCopyThree.get().getBookCopyId());

        //CleanUp
        bookCopyRepository.deleteById(bookCopyOne.getBookCopyId());
        bookCopyRepository.deleteById(bookCopyTwo.getBookCopyId());
        bookCopyRepository.deleteById(bookCopyThree.getBookCopyId());
    }

    @Test
    public void testRelationsWithBookCopy() {
        //Given
        Book bookOne = new Book("Król", "Szczepan Twardoch", LocalDate.of(2018, 12, 02));
        Book bookTwo = new Book("Ogniem i mieczem", "Henryk Sienkiewicz", LocalDate.of(1884, 05, 22));

        BookCopy bookCopyOne = new BookCopy(bookOne, BookStatus.AVAILABLE);
        BookCopy bookCopyTwo = new BookCopy(bookOne, BookStatus.DESTROYED);
        BookCopy bookCopyThree = new BookCopy(bookTwo, BookStatus.BORROWED);
        BookCopy bookCopyFour = new BookCopy(bookTwo, BookStatus.LOST);

        Reader readerOne = new Reader("Tomasz", "Nowak", LocalDate.of(2021, 11, 11));
        Reader readerTwo = new Reader("Marcin", "Kowalski", LocalDate.of(2022, 02, 24));

        Borrowing borrowingOne = new Borrowing(LocalDate.of(2022, 02, 03), LocalDate.of(2022, 02, 23), readerOne);
        Borrowing borrowingTwo = new Borrowing(LocalDate.of(2022, 05, 11), LocalDate.of(2022, 07, 01), readerTwo);

        List<BookCopy> bookCopyListOne = new ArrayList<>();
        bookCopyListOne.add(bookCopyOne);
        bookCopyListOne.add(bookCopyTwo);

        List<BookCopy> bookCopyListTwo = new ArrayList<>();
        bookCopyListTwo.add(bookCopyOne);
        bookCopyListTwo.add(bookCopyTwo);
        bookCopyListTwo.add(bookCopyThree);
        bookCopyListTwo.add(bookCopyFour);

        List<Reader> readerList = new ArrayList<>();
        readerList.add(readerOne);
        readerList.add(readerTwo);

        List<Borrowing> borrowingList = new ArrayList<>();
        borrowingList.add(borrowingOne);
        borrowingList.add(borrowingTwo);

        //When
        bookCopyOne.setReaderList(readerList);

        bookCopyThree.setBorrowingList(borrowingList);

        readerOne.setBookCopyList(bookCopyListOne);
        readerTwo.setBookCopyList(bookCopyListTwo);

        borrowingOne.setBookCopyList(bookCopyListOne);
        borrowingTwo.setBookCopyList(bookCopyListTwo);

        //Then
        assertEquals(2, bookCopyOne.getReaderList().size());
        assertEquals(2, bookCopyThree.getBorrowingList().size());
        assertEquals(2, readerOne.getBookCopyList().size());
        assertEquals(4, readerTwo.getBookCopyList().size());
        assertEquals(2, borrowingOne.getBookCopyList().size());
        assertEquals(4, borrowingTwo.getBookCopyList().size());

        //CleanUp
        bookCopyRepository.deleteAll();
    }


}
