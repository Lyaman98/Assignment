package com.media.solutions.task.gpc.readers.parsers;

import com.media.solutions.task.domain.Statement;

import java.io.InputStreamReader;
import java.util.List;

public interface StatementParser {

    List<Statement> parse(InputStreamReader reader);
}
