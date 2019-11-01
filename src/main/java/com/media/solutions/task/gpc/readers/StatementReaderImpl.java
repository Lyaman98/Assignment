package com.media.solutions.task.gpc.readers;

import com.media.solutions.task.domain.Statement;
import com.media.solutions.task.gpc.readers.parsers.StatementParser;
import com.media.solutions.task.gpc.readers.parsers.impl.StatementParserImpl;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Component
public class StatementReaderImpl implements StatementReader {

    private StatementParser statementParser = new StatementParserImpl();

    @Override
    public List<Statement> read(InputStream inputStream) {

        return statementParser.parse(new InputStreamReader(inputStream));
    }
}
