package com.media.solutions.task.gpc.readers;

import com.media.solutions.task.domain.Statement;

import java.io.InputStream;
import java.util.List;

public interface StatementReader {

    List<Statement> read(InputStream inputStream);

}
