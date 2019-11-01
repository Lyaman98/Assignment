package com.media.solutions.task.gpc.writers;

import com.media.solutions.task.domain.Statement;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StatementWriter {

    boolean write(List<Statement> statementList);
}
