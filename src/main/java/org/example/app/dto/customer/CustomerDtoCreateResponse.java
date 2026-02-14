package org.example.app.dto.customer;

import org.example.app.entity.customer.Customer;
import org.example.app.entity.customer.Customer;
import org.springframework.http.HttpStatus;

public record CustomerDtoCreateResponse(
        int statusCode,
        String reasonPhrase,
        boolean success,
        String message,
        Customer Customer) {

    public static final String SUCCESS_MESSAGE = "CustomerCustomer has been created successfully.";
    public static final String FAILURE_MESSAGE = "CustomerCustomer has not been created!";

    public static CustomerDtoCreateResponse of(boolean isCustomerCustomerCreated, Customer Customer) {
        return (isCustomerCustomerCreated)
                ? new CustomerDtoCreateResponse(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                true, SUCCESS_MESSAGE, Customer)
                : new CustomerDtoCreateResponse(
                HttpStatus.NO_CONTENT.value(),
                HttpStatus.NO_CONTENT.getReasonPhrase(),
                false, FAILURE_MESSAGE, null);
    }
}
