package com.crm.educational_crm_backend.dto.fee;

import java.time.LocalDate;
import java.util.UUID;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeeRequest {

    @NotNull(message = "Student ID is required")
    private UUID studentId;

    @NotNull(message = "Course ID is required")
    private UUID courseId;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than zero")
    private Double amount;

    @NotNull(message = "Due date is required")
    private LocalDate dueDate;

}
