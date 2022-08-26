package com.restapilibrary.repository;

import com.restapilibrary.domain.Borrowing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowingRepository extends CrudRepository<Borrowing, Long> {

    @Override
    List<Borrowing> findAll();
}
