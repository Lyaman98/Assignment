package com.media.solutions.task.service;

import com.media.solutions.task.domain.FirstMessage;
import com.media.solutions.task.repository.FirstMessageRepository;
import org.springframework.stereotype.Service;

@Service
public class FirstMessageService {

    private FirstMessageRepository firstMessageRepository;

    public FirstMessageService(FirstMessageRepository firstMessageRepository) {
        this.firstMessageRepository = firstMessageRepository;
    }

    public FirstMessage save(FirstMessage firstMessage) {
        return firstMessageRepository.save(firstMessage);
    }
}
