package com.restapilibrary.dto;

import com.restapilibrary.domain.Borrowing;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ReaderDto {

    private Long readerId;
    private String name;
    private String surname;
    private LocalDate accountCreationDate;
    private List<Long> borrowingList;
}
