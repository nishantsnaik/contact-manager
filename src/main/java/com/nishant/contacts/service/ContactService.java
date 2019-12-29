package com.nishant.contacts.service;

import com.nishant.contacts.dto.Contact;

import java.text.ParseException;
import java.util.List;

public interface ContactService {

    public List<Contact> findAll();
    public Contact findOne(int contactId);
    public List<Contact> findContactByRequestParams(String lastName, String firstName, String dateOfBirth)
            throws ParseException;
    public Contact createContact(Contact contact) throws ParseException;
    public Contact update(Long contactId,  Contact contact);
    public void delete(Long contactId);


}
