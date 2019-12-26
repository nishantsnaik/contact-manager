package com.nishant.contacts.service;

import com.nishant.contacts.dto.Contact;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {
    @Override
    public List<Contact> findAll() {
        return null;
    }

    @Override
    public Contact findOne(Long contactId) {
        return Contact.builder().contactId(12345).firstName("Nishant").lastName("Naik").gender("M").middleName("S")
                .dateOfBirth(LocalDate.MIN).build();
    }

    @Override
    public Contact findOne(String lastName, String firstName, String dateOfBirth) {
        return Contact.builder().contactId(67890).firstName("Nishant").lastName("Naik").gender("M").middleName("S")
                .dateOfBirth(LocalDate.MIN).build();
    }

    @Override
    public Contact createContact(Contact contact) {
        return Contact.builder().contactId(21354).firstName("Nishant").lastName("Naik").gender("M").middleName("S")
                .dateOfBirth(LocalDate.MIN).build();
    }

    @Override
    public Contact update(Long contactId, Contact contact) {
        return Contact.builder().contactId(95675).firstName("Nishant").lastName("Naik").gender("M").middleName("S")
                .dateOfBirth(LocalDate.MIN).build();
    }

    @Override
    public void delete(Long contactId) {

    }
}
