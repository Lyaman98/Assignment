package com.media.solutions.task.gpc.writers;

import com.media.solutions.task.domain.Statement;
import com.media.solutions.task.repository.StatementRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StatementWriterImpl implements StatementWriter {

    private StatementRepository statementRepository;

    public StatementWriterImpl(StatementRepository statementRepository) {
        this.statementRepository = statementRepository;
    }

    @Override
    public boolean write(List<Statement> statementList) {
        statementList.forEach(statement -> statementRepository.save(statement));

        return true;
    }
}
