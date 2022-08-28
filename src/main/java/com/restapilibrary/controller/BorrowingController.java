package com.restapilibrary.controller;

import com.restapilibrary.domain.BookCopy;
import com.restapilibrary.domain.Borrowing;
import com.restapilibrary.domain.Reader;
import com.restapilibrary.dto.BorrowingDto;
import com.restapilibrary.exceptions.BorrowingNotFoundException;
import com.restapilibrary.mapper.BorrowingMapper;
import com.restapilibrary.repository.BookCopyRepository;
import com.restapilibrary.repository.BorrowingRepository;
import com.restapilibrary.repository.ReaderRepository;
import com.restapilibrary.service.BorrowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/borrowings")
public class BorrowingController {

    private final BorrowingService borrowingService;
    private final BorrowingMapper borrowingMapper;
    private  final BorrowingRepository borrowingRepository;
    private final ReaderRepository readerRepository;
    private final BookCopyRepository bookCopyRepository;

    @GetMapping
    public ResponseEntity<List<BorrowingDto>> getAllBorrowings() {
        List<Borrowing> borrowings = borrowingService.findAllBorrowings();
        return ResponseEntity.ok(borrowingMapper.mapToBorrowingDtoList(borrowings));
    }

    @GetMapping("{borrowingId}")
    public ResponseEntity<BorrowingDto> getBorrowing(@PathVariable Long borrowingId) throws BorrowingNotFoundException {
        return ResponseEntity.ok(borrowingMapper.mapToBorrowingDto(borrowingService.findBorrowingById(borrowingId)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Borrowing> addBorrowing(@RequestBody BorrowingDto borrowingDto) {
        Borrowing borrowing = borrowingMapper.mapToBorrowing(borrowingDto);
        borrowingService.saveBorrowing(borrowing);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(borrowing);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BorrowingDto> updateBorrowing(@RequestBody BorrowingDto borrowingDto) throws BorrowingNotFoundException {
        Borrowing borrowing = borrowingMapper.mapToBorrowing(borrowingDto);
        Borrowing updatedBorrowing = borrowingService.updateBorrowing(borrowing);
        borrowingMapper.mapToBorrowingDto(updatedBorrowing);
        return ResponseEntity.ok(borrowingDto);
    }

    @PutMapping("/{borrowingId}/readers/{readerId}")
    public Borrowing assignReaderToBorrowing(@PathVariable Long borrowingId, @PathVariable Long readerId) {
        Borrowing borrowing = borrowingRepository.findById(borrowingId).get();
        Reader reader = readerRepository.findById(readerId).get();
        borrowing.setReader(reader);
        return borrowingRepository.save(borrowing);
    }

    @PutMapping("/{borrowingId}/bookCopies/{bookCopyId}")
    public Borrowing assignBorrowingToBookCopy(@PathVariable Long borrowingId, @PathVariable Long bookCopyId) {
        Borrowing borrowing = borrowingRepository.findById(borrowingId).get();
        BookCopy bookCopy = bookCopyRepository.findById(bookCopyId).get();
        borrowing.addBookCopy(bookCopy);
        return borrowingRepository.save(borrowing);
    }

    @DeleteMapping(value = "{borrowingId}")
    public ResponseEntity<Void> deleteBorrowing(@PathVariable Long borrowingId) throws BorrowingNotFoundException {
        borrowingService.deleteBorrowing(borrowingId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
