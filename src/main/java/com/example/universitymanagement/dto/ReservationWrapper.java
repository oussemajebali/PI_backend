package com.example.universitymanagement.dto;

import lombok.Getter;

@Getter
public class ReservationWrapper {
    // Getters and setters
    private int userId;
    private String startDate;
    private String endDate;
    private int spaceId;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setSpaceId(int spaceId) {
        this.spaceId = spaceId;
    }
}
