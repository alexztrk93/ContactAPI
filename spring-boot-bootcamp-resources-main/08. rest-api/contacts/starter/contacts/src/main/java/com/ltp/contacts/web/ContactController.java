package com.ltp.contacts.web;

import com.ltp.contacts.pojo.Contact;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.ltp.contacts.service.ContactService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Tag(name = "Contact Controller", description = "Create and retrieve contacts")
public class ContactController {
    @Autowired

    private ContactService contactService;

    @Operation(summary = "Get all contacts", description = "retrieves all contacts")
    @GetMapping("/contact/all")
    public ResponseEntity<List<Contact>> getContacts() {
        List<Contact> contacts = contactService.getContacts();

        return new ResponseEntity<>(contacts, HttpStatus.OK);

    }

    @Operation(summary = "GET single contact", description = "id must be provided")
    @GetMapping("/contact/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable String id) {
        Contact contact = contactService.getContactById(id);
        return new ResponseEntity<>(contact, HttpStatus.OK);

    }

    @Operation(summary = "POST single contact first time", description = "Name and phoneNumber fields must be provided")
    @PostMapping("/contact")
    public ResponseEntity<HttpStatus> createContact(@Valid @RequestBody Contact contact) {
        contactService.saveContact(contact);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "PUT updates single contact", description = "All fields must be provided, id as path parameter")
    @PutMapping("/contact/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable String id, @Valid @RequestBody Contact contact) {

        contactService.updateContact(id, contact);

        return new ResponseEntity<>(contactService.getContactById(id), HttpStatus.OK);

    }

    @Operation(summary = "DELETE single contact", description = "id must be provided as path parameter")
    @DeleteMapping("/contact/{id}")
    public ResponseEntity<HttpStatus> deleteContact(@PathVariable String id) {
        contactService.deleteContact(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
