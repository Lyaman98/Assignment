package com.media.solutions.task.controller;

import com.media.solutions.task.domain.Statement;
import com.media.solutions.task.service.StatementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.List;

@RestController("/api")
public class StatementController {

    private StatementService statementService;
    private final Logger log = LoggerFactory.getLogger(StatementController.class);

    public StatementController(StatementService statementService) {
        this.statementService = statementService;
    }

    @GetMapping("/file/upload")
    public ResponseEntity<List<Statement>> upload(@RequestParam MultipartFile file) throws FileNotFoundException {

        List<Statement> statementList = statementService.save(file);
        return ResponseEntity.ok().body(statementList);
    }


}
