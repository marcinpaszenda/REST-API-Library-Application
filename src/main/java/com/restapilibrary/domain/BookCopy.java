package com.restapilibrary.domain;

import lombok.*;

import javax.persistence.*;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "BOOK_ID", referencedColumnName = "BOOK_ID")
    private Book book;

    @Column(name = "BOOK_STATUS")
    private BookStatus bookStatus;

    public BookCopy(Book book, BookStatus bookStatus) {
        this.book = book;
        this.bookStatus = bookStatus;
    }

    public BookCopy(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }
}
