package com.nishant.contacts.service;

import com.nishant.contacts.dto.Contact;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {
    @Override
    public List<Contact> findAll() {
        return Arrays.asList(Contact.builder().contactId(67890).firstName("Nishant").lastName("Naik").gender("M").middleName("S")
                .dateOfBirth(ZonedDateTime.now()).build());
    }

    @Override
    public Contact findOne(Long contactId) {
        return Contact.builder().contactId(12345).firstName("Nishant").lastName("Naik").gender("M").middleName("S")
                .dateOfBirth(ZonedDateTime.now()).build();
        //return null;
    }

    @Override
    public List<Contact> findContactByLastNameAndFirstNameAndDateOfBirth(String lastName, String firstName, String dateOfBirth) {
         return Arrays.asList(Contact.builder().contactId(67890).firstName("Nishant").lastName("Naik").gender("M").middleName("S")
                .dateOfBirth(ZonedDateTime.now()).build());
    }

    @Override
    public Contact createContact(Contact contact) {
        return Contact.builder().contactId(21354).firstName("Nishant").lastName("Naik").gender("M").middleName("S")
                .dateOfBirth(ZonedDateTime.now()).build();
    }

    @Override
    public Contact update(Long contactId, Contact contact) {
        return Contact.builder().contactId(95675).firstName("Nishant").lastName("Naik").gender("M").middleName("S")
                .build();
    }

    @Override
    public void delete(Long contactId) {

    }
}
