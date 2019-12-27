package com.nishant.contacts.controller;

import com.nishant.contacts.dto.Contact;
import com.nishant.contacts.exception.InvalidQueryException;
import com.nishant.contacts.exception.ResourceNotFoundException;
import com.nishant.contacts.service.ContactService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("contacts")
public class Controller {

    private ContactService service;

    public Controller(ContactService service) {
        this.service = service;
    }
    /*
    TODO Add exception handling
    TODO Add Mappers
    TODO Add log4j
    TODO remove hard coding in service layer
    TODO Do not expose contactID in post, return it back in response
    TODO dateofbirth correct conversion as applicable
    TODO dateofbirth input to check for correct format
     */

    @GetMapping("contactId/{contactId}")
    @ApiOperation(value="Find contact by contact ID", notes = "Find contact by contact ID")
    @ApiResponses(value = {@ApiResponse(code =200, message = "Success"),
            @ApiResponse(code =404, message = "Not Found"),
            @ApiResponse(code =500, message = "Internal Server Error")})
    public Contact findContactByContactId(@PathVariable(name = "contactId") Long contactId) {

        Contact contact = service.findOne(Long.valueOf(contactId));

        if (contact != null) {
            return contact;
        } else {
            throw new ResourceNotFoundException("contactId:" + contactId);
        }
    }

    @GetMapping
    @ApiOperation(value="Find contacts by lastname, firstname and /or dateofbirth", notes = "Find contacts by lastname, firstname and /or dateofbirth\"")
    @ApiResponses(value = {@ApiResponse(code =200, message = "Success"),
            @ApiResponse(code =404, message = "Not Found"),
            @ApiResponse(code =500, message = "Internal Server Error")})
    public List<Contact> findContacts(@RequestParam(required = false) String lastName, @RequestParam(required = false) String firstName, @RequestParam(required = false) String dateOfBirth) {

        //TODO add validation if lastname id available but no first name or dateofbirth

        if (lastName == null) {
            return service.findAll();
        }

        List<Contact> contacts = service.findContactByLastNameAndFirstNameAndDateOfBirth(lastName, firstName, dateOfBirth);
        if (contacts != null && contacts.size() > 0) {
            return contacts;
        } else {
            throw new ResourceNotFoundException("lastName:" + lastName);
        }

    }


    @PostMapping
    @ApiOperation(value="Add a new contact", notes = "Add a new contact")
    @ApiResponses(value = {@ApiResponse(code =201, message = "Created"),
            @ApiResponse(code =400, message = "Bad Request-Contact Already exists"),
            @ApiResponse(code =500, message = "Internal Server Error")})
    public ResponseEntity<Object> createContact(@Valid @RequestBody Contact contact){

        Contact createdContact =service.createContact(contact);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{contactId}")
                .buildAndExpand(createdContact.getContactId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{contactId)")
    @ApiOperation(value="Update a contact", notes = "Update a contact")
    @ApiResponses(value = {@ApiResponse(code =201, message = "Updated"),
            @ApiResponse(code =400, message = "Bad Request-Contact does not exists"),
            @ApiResponse(code =500, message = "Internal Server Error")})
    public Contact updateContact(@PathVariable Long contactId, @Valid @RequestBody Contact contact){
        Contact updatedContact = service.update(contactId, contact);

        if(updatedContact == null){
            throw new ResourceNotFoundException("id:" + contactId);
        }

        return updatedContact;
    }

    @DeleteMapping("{contactId)")
    @ApiOperation(value="Delete a contact", notes = "Delete a  contact")
    @ApiResponses(value = {@ApiResponse(code =200, message = "Deleted"),
            @ApiResponse(code =400, message = "Bad Request-Contact does not exists"),
            @ApiResponse(code =500, message = "Internal Server Error")})
    public void deleteContact(@PathVariable Long contactId){
        service.delete(contactId);
    }





}
