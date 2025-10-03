package com.crm.educational_crm_backend.dto.fee;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeeResponse {

    private UUID id;
    private UUID studentId;
    private UUID courseId;
    private Double amount;
    private LocalDate dueDate;
    private LocalDate paidDate;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
