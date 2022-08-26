package com.restapilibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReaderDto {

    private Long readerId;
    private String name;
    private String surname;
    private LocalDate accountCreationDate;
}
