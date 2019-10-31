package com.media.solutions.task.service;

import com.media.solutions.task.domain.SecondMessage;
import com.media.solutions.task.repository.SecondMessageRepository;
import org.springframework.stereotype.Service;

@Service
public class SecondMessageService {

    private SecondMessageRepository secondMessageRepository;

    public SecondMessageService(SecondMessageRepository secondMessageRepository) {
        this.secondMessageRepository = secondMessageRepository;
    }


    public SecondMessage save(SecondMessage secondMessage) {
        return secondMessageRepository.save(secondMessage);
    }
}
