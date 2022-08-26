package com.restapilibrary.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class BorrowingDto {

    private Long borrowingId;
//    private Long bookCopyId;
    private LocalDate dateOfBorrowing;
    private LocalDate dateOfReturn;

}
