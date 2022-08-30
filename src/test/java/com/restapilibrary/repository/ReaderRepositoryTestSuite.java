package com.restapilibrary.repository;

import com.restapilibrary.domain.Borrowing;
import com.restapilibrary.domain.Reader;
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
public class ReaderRepositoryTestSuite {

    @Autowired
    private ReaderRepository readerRepository;

    @Test
    public void testFindAllReaders() {
        //Given
        Reader reader1 = new Reader();
        Reader reader2 = new Reader();
        Reader reader3 = new Reader();

        //When
        readerRepository.save(reader1);
        readerRepository.save(reader2);
        readerRepository.save(reader3);

        //Then
        assertEquals(3, readerRepository.findAll().size());

        //CleanUp
        readerRepository.deleteById(reader1.getReaderId());
        readerRepository.deleteById(reader2.getReaderId());
        readerRepository.deleteById(reader3.getReaderId());
    }

    @Test
    public void testFindReaderById() {
        Reader reader1 = new Reader();
        Reader reader2 = new Reader();
        Reader reader3 = new Reader();

        //When
        readerRepository.save(reader1);
        readerRepository.save(reader2);
        readerRepository.save(reader3);

        Long readerOneId = reader1.getReaderId();
        Long readerTwoId = reader2.getReaderId();
        Long readerThreeId = reader3.getReaderId();

        Optional<Reader> foundReader1 = readerRepository.findById(readerOneId);
        Optional<Reader> foundReader2 = readerRepository.findById(readerTwoId);
        Optional<Reader> foundReader3 = readerRepository.findById(readerThreeId);

        //Then
        assertNotNull(foundReader1);
        assertNotNull(foundReader2);
        assertNotNull(foundReader3);

        assertEquals(readerOneId, foundReader1.get().getReaderId());
        assertEquals(readerTwoId, foundReader2.get().getReaderId());
        assertEquals(readerThreeId, foundReader3.get().getReaderId());

        //CleanUp
        readerRepository.deleteById((reader1.getReaderId()));
        readerRepository.deleteById((reader2.getReaderId()));
        readerRepository.deleteById((reader3.getReaderId()));
    }

    @Test
    public void testRelationsWithReader() {
        //Given
        Reader readerOne = new Reader("Tomasz", "Nowak", LocalDate.of(2021, 11, 11));
        Reader readerTwo = new Reader("Marcin", "Kowalski", LocalDate.of(2022, 02, 24));

        Borrowing borrowingOne = new Borrowing(LocalDate.of(2022, 02, 03), LocalDate.of(2022, 02, 23));
        Borrowing borrowingTwo = new Borrowing(LocalDate.of(2022, 05, 11), LocalDate.of(2022, 07, 01));
        Borrowing borrowingThree = new Borrowing(LocalDate.of(2022, 06, 30), LocalDate.of(2022, 07, 03));
        Borrowing borrowingFour = new Borrowing(LocalDate.of(2021, 12, 22), LocalDate.of(2022, 02, 01));


        List<Borrowing> borrowingListOne = new ArrayList<>();
        borrowingListOne.add(borrowingOne);
        borrowingListOne.add(borrowingTwo);
        List<Borrowing> borrowingListTwo = new ArrayList<>();
        borrowingListTwo.add(borrowingThree);
        borrowingListTwo.add(borrowingFour);

        //When
        readerOne.setBorrowingList(borrowingListOne);
        readerTwo.setBorrowingList(borrowingListTwo);

        //Then
        assertEquals(2, readerOne.getBorrowingList().size());
        assertEquals(2, readerTwo.getBorrowingList().size());

        //CleanUp
        readerRepository.deleteAll();
    }

}
