package com.nishant.contacts.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Contact {

    /*
    todo profile number should not come as input in POST
     */

    @ApiModelProperty(notes="Customer number should be numnric")
    @Digits(integer =5, fraction =0, message = "must have 5 digits")
    private Integer contactId;

    @ApiModelProperty(notes="First Name should be 3 to 15 chars")
    @Size(min = 3, max = 10, message = "must have between 3 and 10 characters")
    private String firstName;

    @ApiModelProperty(notes="First Name should be 3 to 10 chars")
    @Size(min = 3, max = 10, message = "must have between 3 and 15 characters")
    private String lastName;

    @ApiModelProperty(notes="First Name should be 1 chars")
    @Size(min = 1, max = 1, message = "must have between 1 characters")
    private String middleName;

    @ApiModelProperty(notes="First Name should be 1 chars")
    @Size(min = 1, max = 1, message = "must have between 1 characters")
    private String gender;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @ApiModelProperty(notes="Date format should be in: yyyy-MM-dd")
    @Past
    private ZonedDateTime dateOfBirth;
}