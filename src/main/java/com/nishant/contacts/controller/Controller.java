package com.nishant.contacts.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.nishant.contacts.dto.Contact;
import com.nishant.contacts.exception.InvalidQueryException;
import com.nishant.contacts.exception.ResourceNotFoundException;
import com.nishant.contacts.service.ContactService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("contacts")
public class Controller {

    private ContactService service;

    public Controller(ContactService service) {
        this.service = service;
    }
    /*
    TODO Add Mappers
    TODO Add log4j
    TODO remove hard coding in service layer
    TODO Do not expose contactID in post, return it back in response
    TODO Add hateos
    TODO internationalization
    TODO security
     */

    /********Date of Birth changes
    TODO dateofbirth correct conversion as applicable
    TODO dateofbirth input to check for correct format
    TODO add validation to cheack strng date with specific fomat and in past
    TODO once above step completed, change input string to zoneddatetime and add validations, mappers as applicable
     */

    /**************duplicate post request*****************/
    /*
    TODO Add another field to store nick name to support duplicate
    TODO if the same request comes twice, but needs to be persisted, they have to come with unique nickname
    TODO if the same nickname is sent, send the object with a indicator that says already exists
    TODO nickname should be unique
     */

    @GetMapping("contactId/{contactId}")
    @ApiOperation(value="Find contact by contact ID", notes = "Find contact by contact ID")
    @ApiResponses(value = {@ApiResponse(code =200, message = "Success"),
            @ApiResponse(code =404, message = "Not Found"),
            @ApiResponse(code =500, message = "Internal Server Error")})
    public MappingJacksonValue findContactByContactId(@PathVariable(name = "contactId") Integer contactId) {

        Contact contact = service.findOne(contactId);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("gender","firstName","lastName");
        FilterProvider filters = new SimpleFilterProvider().addFilter("ContactFilter", filter);
        MappingJacksonValue mappings = new MappingJacksonValue(contact);
        mappings.setFilters(filters);

        if (contact != null) {
            //return contact;
            return mappings;
        } else {
            throw new ResourceNotFoundException("contactId:" + contactId);
        }
    }

    @GetMapping
    @ApiOperation(value="Find contacts by lastname, firstname and /or dateofbirth", notes = "Find contacts by lastname, firstname and /or dateofbirth\"")
    @ApiResponses(value = {@ApiResponse(code =200, message = "Success"),
            @ApiResponse(code =404, message = "Not Found"),
            @ApiResponse(code =500, message = "Internal Server Error")})
    public MappingJacksonValue findContacts(@RequestParam(required = false) String lastName, @RequestParam(required = false) String firstName, @RequestParam(required = false) String dateOfBirth) {

        List<Contact> contacts = null;
        if (lastName == null) {
            contacts = service.findAll();
        } else {

            try {
                contacts = service.findContactByRequestParams(lastName, firstName, dateOfBirth);
            } catch (ParseException ex) {
                throw new InvalidQueryException("Date shoule be in the format yyyy-MM-dd");
            }
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("gender", "firstName", "lastName", "contactId");
        FilterProvider filters = new SimpleFilterProvider().addFilter("ContactFilter", filter);
        MappingJacksonValue mappings = new MappingJacksonValue(contacts);
        mappings.setFilters(filters);

        if (lastName != null && (contacts == null || contacts.size() == 0)) {
            throw new ResourceNotFoundException("lastName:" + lastName);
        } else {
            return mappings;
        }
    }


    @PostMapping
    @ApiOperation(value="Add a new contact", notes = "Add a new contact")
    @ApiResponses(value = {@ApiResponse(code =201, message = "Created"),
            @ApiResponse(code =400, message = "Bad Request-Contact Already exists"),
            @ApiResponse(code =500, message = "Internal Server Error")})
    public ResponseEntity<Object> createContact(@Valid @RequestBody Contact contact){

        Contact createdContact = null;
        try {
            createdContact =service.createContact(contact);
        } catch (ParseException ex) {
            throw new InvalidQueryException("Date shoule be in the format yyyy-MM-dd");
        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{contactId}")
                .buildAndExpand(createdContact.getContactId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{contactId)")
    @ApiOperation(value="Update a contact", notes = "Update a contact")
    @ApiResponses(value = {@ApiResponse(code =201, message = "Updated"),
            @ApiResponse(code =400, message = "Bad Request-Contact does not exists"),
            @ApiResponse(code =500, message = "Internal Server Error")})
    public Contact updateContact(@PathVariable Long contactId, @RequestBody Contact contact){
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
