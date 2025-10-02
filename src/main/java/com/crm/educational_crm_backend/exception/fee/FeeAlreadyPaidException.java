package com.crm.educational_crm_backend.exception.fee;

import java.time.LocalDate;

public class FeeAlreadyPaidException extends RuntimeException {

    public FeeAlreadyPaidException(LocalDate paidDate) {
        super("Fee is already paid on: " + paidDate);
    }

    public FeeAlreadyPaidException(String message) {
        super(message);
    }
}
