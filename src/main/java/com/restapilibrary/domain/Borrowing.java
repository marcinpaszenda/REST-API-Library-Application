package com.restapilibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "BORROWING")
public class Borrowing {

    @Id
    @GeneratedValue
    @Column(name = "BORROWING_ID", unique = true)
    private Long borrowingId;

//    @Column
//    private BookCopy bookCopy;

//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "READER_ID", referencedColumnName = "BORROWING_ID")
//    private Reader reader;

    @Column
    private LocalDate dateOfBorrowing;

    @Column
    private LocalDate dateOfReturn;


}
