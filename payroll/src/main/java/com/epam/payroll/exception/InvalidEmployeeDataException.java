package com.epam.payroll.exception;

public class InvalidEmployeeDataException extends RuntimeException {

    public InvalidEmployeeDataException(String message) {
        super(message);
    }
}