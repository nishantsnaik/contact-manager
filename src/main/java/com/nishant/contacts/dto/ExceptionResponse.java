package com.nishant.contacts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ExceptionResponse {

    private ZonedDateTime exceptionDateTime;
    private String exceptionMessage;
    private String exceptionDetails;
}
