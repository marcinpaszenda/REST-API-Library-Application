package com.restapilibrary.service;

import com.restapilibrary.domain.Reader;
import com.restapilibrary.exceptions.ReaderNotFoundException;
import com.restapilibrary.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderService {

    private final ReaderRepository readerRepository;

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    public Reader findReaderById(final Long readerId) throws ReaderNotFoundException {
        return readerRepository.findById(readerId).orElseThrow(ReaderNotFoundException::new);
    }

    public Reader saveReader(Reader reader) {
        return readerRepository.save(reader);
    }

    public Reader updateReader(final Reader reader) throws ReaderNotFoundException {
        if (readerRepository.existsById(reader.getReaderId())) {
            readerRepository.save(reader);
            return reader;
        } else {
            throw new ReaderNotFoundException();
        }
    }

    public void deleteReader(Long readerId) throws ReaderNotFoundException {
        if (readerRepository.existsById(readerId)) {
            readerRepository.deleteById(readerId);
        } else {
            throw new ReaderNotFoundException();
        }

    }


}
