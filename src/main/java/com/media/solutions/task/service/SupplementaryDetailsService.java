package com.media.solutions.task.service;

import com.media.solutions.task.domain.SupplementaryDetails;
import com.media.solutions.task.repository.SupplementaryDetailsRepository;
import org.springframework.stereotype.Service;

@Service
public class SupplementaryDetailsService {

    private SupplementaryDetailsRepository supplementaryDetailsRepository;

    public SupplementaryDetailsService(SupplementaryDetailsRepository supplementaryDetailsRepository) {
        this.supplementaryDetailsRepository = supplementaryDetailsRepository;
    }

    public SupplementaryDetails save(SupplementaryDetails supplementaryDetails) {
        return supplementaryDetailsRepository.save(supplementaryDetails);
    }
}
