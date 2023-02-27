package com.ltp.contacts.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ltp.contacts.pojo.Contact;

@Repository
public class ContactRepository {

    private List<Contact> contacts =new ArrayList<>();
//    private List<Contact> contacts = Arrays.asList(
//
//            new Contact("123","Fabian Barthez","123 456 7892"),
//            new Contact("321","Van Der Sar","298 765 4321"),
//            new Contact("654","Fernando Muslera","325 451 5478")
//            );

    public List<Contact> getContacts() {
        return contacts;
    }

    public Contact getContact(int index) {
        return contacts.get(index);
    }

    public void saveContact(Contact contact) {
        contacts.add(contact);
    }

    public void updateContact(int index, Contact contact) {
        contacts.set(index, contact);
    }

    public void deleteContact(int index) {
        contacts.remove(index);
    }

}
