package com.restapilibrary.repository;

import com.restapilibrary.domain.Borrowing;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BorrowingRepositoryTestSuite {

    @Autowired
    BorrowingRepository borrowingRepository;

    @Test
    public void testFindAllBorrowings() {
        //Given
        Borrowing borrowing1 = new Borrowing();
        Borrowing borrowing2 = new Borrowing();
        Borrowing borrowing3 = new Borrowing();
        Borrowing borrowing4 = new Borrowing();

        //When
        borrowingRepository.save(borrowing1);
        borrowingRepository.save(borrowing2);
        borrowingRepository.save(borrowing3);
        borrowingRepository.save(borrowing4);

        //Then
        assertEquals(4, borrowingRepository.findAll().size());

        //CleanUp
        borrowingRepository.deleteById(borrowing1.getBorrowingId());
        borrowingRepository.deleteById(borrowing2.getBorrowingId());
        borrowingRepository.deleteById(borrowing3.getBorrowingId());
        borrowingRepository.deleteById(borrowing4.getBorrowingId());
    }

    @Test
    public void testFindBorrowingById() {
        //Given
        Borrowing borrowing1 = new Borrowing();
        Borrowing borrowing2 = new Borrowing();
        Borrowing borrowing3 = new Borrowing();

        //When
        borrowingRepository.save(borrowing1);
        borrowingRepository.save(borrowing2);
        borrowingRepository.save(borrowing3);

        Long borrowing1Id = borrowing1.getBorrowingId();
        Long borrowing2Id = borrowing1.getBorrowingId();
        Long borrowing3Id = borrowing1.getBorrowingId();

        Optional<Borrowing> foundBorrowing1 = borrowingRepository.findById(borrowing1Id);
        Optional<Borrowing> foundBorrowing2 = borrowingRepository.findById(borrowing2Id);
        Optional<Borrowing> foundBorrowing3 = borrowingRepository.findById(borrowing3Id);

        //Then
        assertNotNull(foundBorrowing1);
        assertNotNull(foundBorrowing2);
        assertNotNull(foundBorrowing3);

        assertEquals(borrowing1Id, foundBorrowing1.get().getBorrowingId());
        assertEquals(borrowing2Id, foundBorrowing2.get().getBorrowingId());
        assertEquals(borrowing3Id, foundBorrowing3.get().getBorrowingId());

        //CleanUp
        borrowingRepository.deleteAll();

    }

}
