package com.media.solutions.task.gpc.writers;

import com.media.solutions.task.domain.Statement;

import java.util.List;

public interface StatementWriter {

    void write(List<Statement> statementList);
}
