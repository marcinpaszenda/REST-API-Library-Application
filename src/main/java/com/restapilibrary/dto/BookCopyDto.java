package com.restapilibrary.dto;

import com.restapilibrary.domain.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookCopyDto {

    private Long bookCopyId;
//    private Long bookId;
    private BookStatus bookStatus;
}
