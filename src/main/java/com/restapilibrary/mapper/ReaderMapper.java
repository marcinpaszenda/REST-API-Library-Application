package com.restapilibrary.mapper;

import com.restapilibrary.domain.Reader;
import com.restapilibrary.dto.ReaderDto;
import com.restapilibrary.repository.BorrowingRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReaderMapper {

    BorrowingRepository borrowingRepository;

    public Reader mapToReader(final ReaderDto readerDto) {
        return new Reader(
                readerDto.getReaderId(),
                readerDto.getName(),
                readerDto.getSurname(),
                readerDto.getAccountCreationDate()
        );
    }

    public ReaderDto mapToReaderDto(final Reader reader) {
        return new ReaderDto(
                reader.getReaderId(),
                reader.getName(),
                reader.getSurname(),
                reader.getAccountCreationDate(),
                reader.getBorrowingList().isEmpty()? Collections.emptyList() : reader.getBorrowingList().stream()
                        .map(borrowing -> borrowing.getBorrowingId())
                        .collect(Collectors.toList())
        );
    }

    public List<ReaderDto> mapToReaderDtoList(final List<Reader> readerList) {
        return readerList.stream()
                .map(this::mapToReaderDto)
                .collect(Collectors.toList());
    }
}
