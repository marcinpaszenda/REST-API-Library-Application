package com.restapilibrary.mapper;

import com.restapilibrary.domain.Borrowing;
import com.restapilibrary.dto.BorrowingDto;
import com.restapilibrary.repository.BookCopyRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BorrowingMapper {

    BookCopyRepository bookCopyRepository;

    public Borrowing mapToBorrowing(final BorrowingDto borrowingDto) {
        return new Borrowing(
//                borrowingDto.getBorrowingId(),
//                bookCopyRepository.findById(borrowingDto.getBookCopyId())
//                        .orElseThrow(() -> new RuntimeException("BookCopy ID" + borrowingDto.getBookCopyId() +
//                                " doesn't exist")),
                borrowingDto.getDateOfBorrowing(),
                borrowingDto.getDateOfReturn()
        );
    }

    public BorrowingDto mapToBorrowingDto(final Borrowing borrowing) {
        return new BorrowingDto(
                borrowing.getBorrowingId(),
//                borrowing.getBookCopyId(),
                borrowing.getDateOfBorrowing(),
                borrowing.getDateOfReturn()
        );
    }

    public List<BorrowingDto> mapToBorrowingDtoList(final List<Borrowing> borrowingList) {
        return borrowingList.stream()
                .map(this::mapToBorrowingDto)
                .collect(Collectors.toList());
    }
}
