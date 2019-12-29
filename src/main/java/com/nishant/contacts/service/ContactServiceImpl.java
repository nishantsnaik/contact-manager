package com.nishant.contacts.service;

import com.google.common.collect.Lists;
import com.nishant.contacts.dto.Contact;
import com.nishant.contacts.mappers.ContactMapper;
import com.nishant.contacts.repository.ContactRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    private final ContactMapper contactMapper = Mappers.getMapper(ContactMapper.class);

    @Override
    public List<Contact> findAll() {

        Iterable<com.nishant.contacts.entity.Contact> contacts = contactRepository.findAll();

        List<com.nishant.contacts.entity.Contact> actualList = StreamSupport
                .stream(contacts.spliterator(), false)
                .collect(Collectors.toList());

        List<Contact> responseList = new ArrayList<>();
        actualList.forEach(contact -> {
            responseList.add(contactMapper.repoToService(contact));
        });

        return responseList;
    }

    @Override
    public Contact findOne(int contactId) {
        com.nishant.contacts.entity.Contact contact = contactRepository.findByContactId(contactId);
        return contactMapper.repoToService(contact);
    }

    @Override
    public List<Contact> findContactByRequestParams(String lastName, String firstName, String dateOfBirth) throws ParseException {

        if(lastName != null && firstName != null && dateOfBirth != null){
            return findContactByLastNameAndFirstNameAndDateOfBirth(lastName, firstName, dateOfBirth);
        } else if (lastName != null && firstName != null ){
            return findContactByLastNameAndFirstName(lastName, firstName);
        } else if(lastName != null && dateOfBirth != null ){
            return findContactByLastNameAndDateOfBirth(lastName, dateOfBirth);
        } else if (lastName != null){
            return findContactByLastName(lastName);
        }
        return null;
    }

    @Override
    public Contact createContact(Contact contact) throws ParseException{
        if(isContactExists(contact)){
            return contact;
        }

        com.nishant.contacts.entity.Contact savedContact =  contactRepository.save(contactMapper.serviceToRepo(contact));

        return contactMapper.repoToService(savedContact);
    }

    @Override
    public Contact update(Long contactId, Contact contact) {
        return Contact.builder().contactId(95675).firstName("Nishant").lastName("Naik").gender("M").middleName("S")
                .build();
    }

    @Override
    public void delete(Long contactId) {

    }

    private List<Contact> findContactByLastName(String lastName) {

        List<com.nishant.contacts.entity.Contact> contacts = contactRepository.findContactByLastName(lastName);

        return mapRepoContactsToServiceContacts(contacts);

    }

    private List<Contact> findContactByLastNameAndFirstName(String lastName, String firstName) {
        List<com.nishant.contacts.entity.Contact> contacts =  contactRepository.findContactByLastNameAndFirstName(lastName,firstName);

        return mapRepoContactsToServiceContacts(contacts);
    }

    private List<Contact> findContactByLastNameAndDateOfBirth(String lastName, String dateOfBirth) throws ParseException {

        Date dob = null;
        if(dateOfBirth != null){
            dob=new SimpleDateFormat("yyyy-MM-dd").parse(dateOfBirth);
        }
        List<com.nishant.contacts.entity.Contact> contacts = contactRepository.findContactByLastNameAndDateOfBirth(lastName,dob);

        return mapRepoContactsToServiceContacts(contacts);
    }

    private List<Contact> findContactByLastNameAndFirstNameAndDateOfBirth(String lastName, String firstName, String dateOfBirth) throws ParseException {

        Date dob = null;
        if(dateOfBirth != null){
            dob=new SimpleDateFormat("yyyy-MM-dd").parse(dateOfBirth);
        }
        List<com.nishant.contacts.entity.Contact> contacts =  contactRepository.findContactByLastNameAndFirstNameAndDateOfBirth(lastName,firstName,dob);

        return mapRepoContactsToServiceContacts(contacts);
    }

    private List<Contact> mapRepoContactsToServiceContacts(List<com.nishant.contacts.entity.Contact> contacts) {
        List<Contact> responseList = new ArrayList<>();
        contacts.forEach(contact -> {
            responseList.add(contactMapper.repoToService(contact));
        });
        return responseList;
    }

    private boolean isContactExists(Contact contact) throws ParseException {

        List<Contact> exitingContact = findContactByLastNameAndFirstNameAndDateOfBirth(contact.getLastName(), contact.getFirstName(), contact.getDateOfBirth());
        if(exitingContact != null && exitingContact.size() > 0){
            return true;
        }
        return false;

    }


}
