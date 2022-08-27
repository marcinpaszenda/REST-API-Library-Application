package com.restapilibrary.mapper;

import com.restapilibrary.domain.BookCopy;
import com.restapilibrary.dto.BookCopyDto;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookCopyMapper {

    public BookCopy mapToBookCopy(final BookCopyDto bookCopyDto) {
        return new BookCopy(
                bookCopyDto.getBookStatus()
        );
    }

    public BookCopyDto mapToBookCopyDto(final BookCopy bookCopy) {
        return new BookCopyDto(
                bookCopy.getBookCopyId(),
                bookCopy.getBook().getBookId(),
                bookCopy.getBookStatus()
        );
    }

    public List<BookCopyDto> mapToBookCopyDtoList(final List<BookCopy> bookCopyList) {
        return bookCopyList.isEmpty()? Collections.emptyList() : bookCopyList.stream()
                .map(this::mapToBookCopyDto)
                .collect(Collectors.toList());
    }
}
