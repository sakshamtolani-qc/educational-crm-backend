package com.crm.educational_crm_backend.dto.results;

import lombok.Data;

import java.util.UUID;

@Data
public class ResultRequest {
    private UUID studentId;
    private Double marksObtained;
    private Integer remarks;
}
