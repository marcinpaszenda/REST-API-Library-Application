package com.restapilibrary.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Reader {

    @Id
    @GeneratedValue
    private Long readerId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "ACCOUNT_DATE")
    private LocalDate accountCreationDate;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "reader_bookCopy",
            joinColumns = @JoinColumn(name = "reader_id"),
            inverseJoinColumns = @JoinColumn(name = "bookCopy_id")
    )
    private List<BookCopy> bookCopyList = new ArrayList<>();

    @OneToMany(
            targetEntity = Borrowing.class,
            mappedBy = "reader",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Borrowing> borrowingList = new ArrayList<>();

    public Reader(Long readerId, String name, String surname, LocalDate accountCreationDate) {
        this.readerId = readerId;
        this.name = name;
        this.surname = surname;
        this.accountCreationDate = accountCreationDate;
    }

    public Reader(String name, String surname, LocalDate accountCreationDate) {
        this.name = name;
        this.surname = surname;
        this.accountCreationDate = accountCreationDate;
    }

    public void addBookCopy(BookCopy bookCopy) {
        bookCopyList.add(bookCopy);
    }
}


