package com.restapilibrary.repository;

import com.restapilibrary.domain.BookCopy;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookCopyRepository extends CrudRepository<BookCopy, Long> {

    @Override
    List<BookCopy> findAll();
}
