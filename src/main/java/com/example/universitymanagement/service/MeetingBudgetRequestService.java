package com.example.universitymanagement.service;

import com.example.universitymanagement.entity.MeetingBudgetRequest;
import com.example.universitymanagement.entity.RequestStatus;
import com.example.universitymanagement.repository.MeetingBudgetRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeetingBudgetRequestService {

    private final MeetingBudgetRequestRepository requestRepository;

    public MeetingBudgetRequest createRequest(MeetingBudgetRequest request) {
        request.setStatus(RequestStatus.PENDING);
        return requestRepository.save(request);
    }

    public MeetingBudgetRequest approveRequest(Long requestId) {
        MeetingBudgetRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));
        request.setStatus(RequestStatus.APPROVED);
        return requestRepository.save(request);
    }

    public MeetingBudgetRequest rejectRequest(Long requestId) {
        MeetingBudgetRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));
        request.setStatus(RequestStatus.REJECTED);
        return requestRepository.save(request);
    }

    // Other methods as needed
}
