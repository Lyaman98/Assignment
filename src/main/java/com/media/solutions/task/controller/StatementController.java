package com.media.solutions.task.controller;

import com.media.solutions.task.domain.Statement;
import com.media.solutions.task.gpc.readers.StatementReader;
import com.media.solutions.task.gpc.writers.StatementWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StatementController {

    private final Logger log = LoggerFactory.getLogger(StatementController.class);

    private StatementWriter statementWriter;
    private StatementReader statementReader;

    public StatementController(StatementWriter statementWriter, StatementReader statementReader) {
        this.statementWriter = statementWriter;
        this.statementReader = statementReader;
    }

    //api for uploading file from some source and saving it in Mongodb

    @GetMapping("/upload/file")
    public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file) throws IOException {

        List<Statement> statements = statementReader.read(file.getInputStream());
        statementWriter.write(statements);

        return ResponseEntity.ok("Successfully saved");
    }


}
