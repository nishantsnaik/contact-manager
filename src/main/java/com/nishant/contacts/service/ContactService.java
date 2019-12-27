package com.nishant.contacts.service;

import com.nishant.contacts.dto.Contact;

import java.util.List;

public interface ContactService {

    public List<Contact> findAll();
    public Contact findOne(Long contactId);
    public List<Contact> findContactByLastNameAndFirstNameAndDateOfBirth(String lastName, String firstName, String dateOfBirth);
    public Contact createContact(Contact contact);
    public Contact update(Long contactId,  Contact contact);
    public void delete(Long contactId);


}
