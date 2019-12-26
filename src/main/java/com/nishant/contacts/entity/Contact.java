package com.nishant.contacts.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
@Entity
public class Contact {

    public Contact(){
        this.id = UUID.randomUUID().toString();
    }

    @Id
    private String id;

    @Column(length = 6, unique = true)
    private Integer contactId;

    @Column(length = 20)
    private String firstName;

    @Column(length = 20)
    private String lastName;

    @Column(length = 1)
    private String middleName;

    @Column(length = 1)
    private String gender;

    private LocalDate dateOfBirth;


}
