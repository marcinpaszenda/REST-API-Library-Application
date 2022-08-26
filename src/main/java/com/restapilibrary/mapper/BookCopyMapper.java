package com.restapilibrary.mapper;

import com.restapilibrary.domain.BookCopy;
import com.restapilibrary.dto.BookCopyDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookCopyMapper {

    public BookCopy mapToBookCopy(final BookCopyDto bookCopyDto) {
        return new BookCopy(
                bookCopyDto.getBookCopyId(),
                bookCopyDto.getBookStatus()
        );
    }

    public BookCopyDto mapToBookCopyDto(final BookCopy bookCopy) {
        return new BookCopyDto(
                bookCopy.getBookCopyId(),
                bookCopy.getBookStatus()
        );
    }

    public List<BookCopyDto> mapToBookCopyDtoList(final List<BookCopy> bookCopyList) {
        return bookCopyList.stream()
                .map(this::mapToBookCopyDto)
                .collect(Collectors.toList());
    }
}
