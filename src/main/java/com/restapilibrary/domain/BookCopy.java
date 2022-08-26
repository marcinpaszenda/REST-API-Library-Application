package com.restapilibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "BOOK_ID", referencedColumnName = "BOOK_ID")
//    private Book book;

    @Column(name = "BOOK_STATUS")
    private BookStatus bookStatus;

}
