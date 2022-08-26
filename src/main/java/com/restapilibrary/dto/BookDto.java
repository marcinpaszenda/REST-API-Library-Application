package com.restapilibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class BookDto {
    private Long bookId;
    private String title;
    private String author;
    private LocalDate publicationYear;
}
