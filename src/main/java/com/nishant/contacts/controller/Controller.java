package com.nishant.contacts.controller;

import com.nishant.contacts.dto.Contact;
import com.nishant.contacts.exception.InvalidQueryException;
import com.nishant.contacts.service.ContactService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

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
     */

    @GetMapping("id/{contactId)")
    @ApiOperation(value="Find contact by contact ID", notes = "Find contact by contact ID")
    @ApiResponses(value = {@ApiResponse(code =200, message = "Success"),
            @ApiResponse(code =404, message = "Not Found"),
            @ApiResponse(code =500, message = "Internal Server Error")})
    public Contact findContactByContactId(@PathVariable Long contactId){

        //TODO add exception handling

        if(contactId != null){

            return service.findOne(Long.valueOf(contactId));
        }/*else if(lastName != null){

            return service.findOne(lastName, firstName, dateOfBirth);
        }else {
            // return invalid query exception
        }*/
        return service.findOne(Long.valueOf(contactId));
    }

    @GetMapping
    @ApiOperation(value="Find contacts by lastname, firstname and /or dateofbirth", notes = "Find contacts by lastname, firstname and /or dateofbirth\"")
    @ApiResponses(value = {@ApiResponse(code =200, message = "Success"),
            @ApiResponse(code =404, message = "Not Found"),
            @ApiResponse(code =500, message = "Internal Server Error")})
    public List<Contact> findContacts(@RequestParam(required = false) String lastName, @RequestParam(required = false) String firstName, @RequestParam(required = false) String dateOfBirth){

        //TODO add exception handling
        //TODO add validation if lastname id available but no first name or dateofbirth

        if(lastName != null){

            return Arrays.asList(service.findOne(lastName, firstName, dateOfBirth));
        } else {
            return service.findAll();
        }
    }


    @PostMapping
    @ApiOperation(value="Add a new contact", notes = "Add a new contact")
    @ApiResponses(value = {@ApiResponse(code =201, message = "Created"),
            @ApiResponse(code =400, message = "Bad Request-Contact Already exists"),
            @ApiResponse(code =500, message = "Internal Server Error")})
    public Contact createContact(@RequestBody Contact contact){
        return service.createContact(contact);
    }

    @PutMapping("{contactId)")
    @ApiOperation(value="Update a contact", notes = "Update a contact")
    @ApiResponses(value = {@ApiResponse(code =201, message = "Updated"),
            @ApiResponse(code =400, message = "Bad Request-Contact does not exists"),
            @ApiResponse(code =500, message = "Internal Server Error")})
    public Contact updateContact(@PathVariable Long contactId, @RequestBody Contact contact){
        return service.update(contactId, contact);
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
