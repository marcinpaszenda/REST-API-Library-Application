package com.restapilibrary.dto;

import com.restapilibrary.domain.BookCopy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class BookDto {
    private Long bookId;
    private String title;
    private String author;
    private LocalDate publicationYear;
    private List<Long> bookCopiesId;
//    private List<BookCopy> bookCopyList;

}
