package com.nishant.contacts.repository;

import com.nishant.contacts.entity.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer> {

    public List<com.nishant.contacts.entity.Contact> findContactByLastName(String lastName);

    public List<com.nishant.contacts.entity.Contact> findContactByLastNameAndFirstName(String lastName, String firstName);

    public List<com.nishant.contacts.entity.Contact> findContactByLastNameAndDateOfBirth(String lastName, Date dateOfBirth);

    public List<com.nishant.contacts.entity.Contact> findContactByLastNameAndFirstNameAndDateOfBirth(String lastName, String firstName, Date dateOfBirth);

    public Contact findByContactId(int contactId);
}
