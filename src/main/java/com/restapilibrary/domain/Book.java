package com.restapilibrary.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
//@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "BOOK_ID", unique = true)
    private Long bookId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "PUBLICATION_YEAR")
    private LocalDate publicationYear;

//    @OneToMany(
//            targetEntity = BookCopy.class,
//            mappedBy = "book",
//            cascade = CascadeType.ALL,
//            fetch = FetchType.EAGER
//    )
//    private List<BookCopy> bookCopyList = new ArrayList<>();

    public Book(Long bookId, String title, String author, LocalDate publicationYear) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }
}

