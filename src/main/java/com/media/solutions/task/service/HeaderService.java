package com.media.solutions.task.service;

import com.media.solutions.task.domain.Header;
import com.media.solutions.task.repository.HeaderRepository;
import org.springframework.stereotype.Service;

@Service
public class HeaderService{

    private HeaderRepository headerRepository;

    public HeaderService(HeaderRepository headerRepository) {
        this.headerRepository = headerRepository;
    }

    public Header save(Header header){
        return headerRepository.save(header);
    }

}
