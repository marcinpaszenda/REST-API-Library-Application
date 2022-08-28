package com.restapilibrary.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @Column
    private LocalDate dateOfBorrowing;

    @Column
    private LocalDate dateOfReturn;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "READER_ID")
    private Reader reader;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "borrowing_bookCopy",
            joinColumns = @JoinColumn(name = "borrowing_id"),
            inverseJoinColumns = @JoinColumn(name = "bookCopy_id")
    )
    private List<BookCopy> bookCopyList = new ArrayList<>();

    public Borrowing(LocalDate dateOfBorrowing, LocalDate dateOfReturn) {
        this.dateOfBorrowing = dateOfBorrowing;
        this.dateOfReturn = dateOfReturn;
    }

    public void addBookCopy(BookCopy bookCopy) {
        bookCopyList.add(bookCopy);
    }
}
