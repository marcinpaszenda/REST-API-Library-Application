package com.restapilibrary.service;

import com.restapilibrary.domain.Borrowing;
import com.restapilibrary.exceptions.BorrowingNotFoundException;
import com.restapilibrary.repository.BorrowingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowingService {

    private final BorrowingRepository borrowingRepository;

    public List<Borrowing> findAllBorrowings() {
        return borrowingRepository.findAll();
    }

    public Borrowing findBorrowingById(final Long borrowingId) throws BorrowingNotFoundException {
        return borrowingRepository.findById(borrowingId).orElseThrow(BorrowingNotFoundException::new);
    }

    public Borrowing saveBorrowing(Borrowing borrowing) {
        return borrowingRepository.save(borrowing);
    }

    public Borrowing updateBorrowing(final Borrowing borrowing) throws BorrowingNotFoundException {
        if (borrowingRepository.existsById(borrowing.getBorrowingId())) {
            borrowingRepository.save(borrowing);
            return borrowing;
        } else {
            throw new BorrowingNotFoundException();
        }
    }

    public void deleteBorrowing(Long borrowingId) throws BorrowingNotFoundException {
        if (borrowingRepository.existsById(borrowingId)) {
            borrowingRepository.deleteById(borrowingId);
        } else {
            throw new BorrowingNotFoundException();
        }
    }
}
