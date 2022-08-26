package com.restapilibrary.controller;

import com.restapilibrary.domain.Reader;
import com.restapilibrary.dto.ReaderDto;
import com.restapilibrary.exceptions.ReaderNotFoundException;
import com.restapilibrary.mapper.ReaderMapper;
import com.restapilibrary.service.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/readers")
public class ReaderController {

    private final ReaderService readerService;
    private final ReaderMapper readerMapper;


    @GetMapping
    public ResponseEntity<List<ReaderDto>> getReaders() {
        List<Reader> readers =readerService.getAllReaders();
        return ResponseEntity.ok(readerMapper.mapToReaderDtoList(readers));
    }

    @GetMapping("/{readerId}")
    public ResponseEntity<ReaderDto> getReader(@PathVariable Long readerId) throws ReaderNotFoundException {
        Reader reader = readerService.findReaderById(readerId);
        return ResponseEntity.ok(readerMapper.mapToReaderDto(reader));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reader> createReader(@RequestBody ReaderDto readerDto) {
        Reader reader = readerMapper.mapToReader(readerDto);
            readerService.saveReader(reader);
        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl("http://Localhost:8080/readers/{readerId}")
                .buildAndExpand(reader.getReaderId());
        return ResponseEntity.created(uriComponents.toUri())
                .body(reader);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReaderDto> updateReader(@RequestBody ReaderDto readerDto) throws ReaderNotFoundException {
        Reader reader = readerMapper.mapToReader(readerDto);
        Reader updateReader = readerService.updateReader(reader);
        readerMapper.mapToReaderDto(updateReader);
        return ResponseEntity.ok().body(readerDto);
    }

    @DeleteMapping(value = "{readerId}")
    public ResponseEntity<Void> deleteReader(@PathVariable Long readerId) throws ReaderNotFoundException {
        readerService.deleteReader(readerId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
