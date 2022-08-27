package com.restapilibrary.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BookCopy {

    @Id
    @GeneratedValue
    @Column(name = "BOOK_COPY_ID", unique = true)
    private Long bookCopyId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "BOOK_ID", referencedColumnName = "BOOK_ID")
    private Book book;

    @Column(name = "BOOK_STATUS")
    private BookStatus bookStatus;

    @ManyToMany(mappedBy = "bookCopyList")
    private List<Reader> readerList = new ArrayList<>();

    public BookCopy(Book book, BookStatus bookStatus) {
        this.book = book;
        this.bookStatus = bookStatus;
    }

    public BookCopy(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }
}
