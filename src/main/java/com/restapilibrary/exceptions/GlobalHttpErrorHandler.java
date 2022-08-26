package com.restapilibrary.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Object> handleBookNotFoundException(BookNotFoundException bookNotFoundException) {
        return new ResponseEntity<>("Book with given ID doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ReaderNotFoundException.class)
    public ResponseEntity<Object> handleReaderNotFoundException(ReaderNotFoundException readerNotFoundException) {
        return new ResponseEntity<>("Reader with given ID doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookCopyNotFoundException.class)
    public ResponseEntity<Object> handleBookCopyNotFoundException(BookCopyNotFoundException bookCopyNotFoundException) {
        return new ResponseEntity<>("BookCopy with given ID doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BorrowingNotFoundException.class)
    public ResponseEntity<Object> handleBorrowingNotFoundException(BorrowingNotFoundException borrowingNotFoundException) {
        return new ResponseEntity<>("Borrowing with given ID doesn't exist", HttpStatus.BAD_REQUEST);
    }
}
