package com.restapilibrary.service;

import com.restapilibrary.domain.BookCopy;
import com.restapilibrary.exceptions.BookCopyNotFoundException;
import com.restapilibrary.repository.BookCopyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookCopyService {

    private final BookCopyRepository bookCopyRepository;

    public List<BookCopy> findAllBookCopies() {
        return bookCopyRepository.findAll();
    }

    public BookCopy findBookCopyById(final Long bookCopyId) throws BookCopyNotFoundException {
        return bookCopyRepository.findById(bookCopyId).orElseThrow(BookCopyNotFoundException::new);
    }

    public BookCopy saveBookCopy(BookCopy bookCopy) {
        return bookCopyRepository.save(bookCopy);
    }

    public BookCopy updateBookCopy(final BookCopy bookCopy) throws BookCopyNotFoundException {
        if (bookCopyRepository.existsById(bookCopy.getBookCopyId())) {
            bookCopyRepository.save(bookCopy);
            return bookCopy;
        } else {
            throw new BookCopyNotFoundException();
        }
    }

    public void deleteBookCopy(Long bookCopyId) throws BookCopyNotFoundException {
        if (bookCopyRepository.existsById(bookCopyId)) {
            bookCopyRepository.deleteById(bookCopyId);
        } else {
            throw new BookCopyNotFoundException();
        }
    }
}
