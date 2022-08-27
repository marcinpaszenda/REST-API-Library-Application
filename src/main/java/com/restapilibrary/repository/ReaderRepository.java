package com.restapilibrary.repository;

import com.restapilibrary.domain.Reader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReaderRepository extends CrudRepository<Reader, Long> {

    @Override
    List<Reader> findAll();


}
