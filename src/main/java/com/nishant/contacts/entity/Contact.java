package com.nishant.contacts.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
@Entity
public class Contact {

    /*public Contact(){
        this.id = UUID.randomUUID().toString();
    }*/

    /*@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private String id;*/

    @Id
    @SequenceGenerator(name = "contactIdSequenceGen", sequenceName = "contactIdSequence", initialValue = 100001, allocationSize = 1)
    @GeneratedValue(generator = "contactIdSequenceGen")
    @Column(length = 6, updatable = false, nullable = false)
    private Integer contactId;

    @Column(length = 20)
    private String firstName;

    @Column(length = 20)
    private String lastName;

    @Column(length = 1)
    private String middleName;

    @Column(length = 1)
    private String gender;

    private Date dateOfBirth;


}

