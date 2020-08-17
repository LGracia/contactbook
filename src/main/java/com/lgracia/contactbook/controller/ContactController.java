package com.lgracia.contactbook.controller;

import com.lgracia.contactbook.entity.Contact;
import com.lgracia.contactbook.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping
    public ResponseEntity<?> showAll() {
        return new ResponseEntity<>(contactRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return new ResponseEntity<>(contactRepository.findById(id), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createContact(@Valid @RequestBody Contact contact) {
        contactRepository.save(contact);

        return new ResponseEntity<>(contact, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Contact> updateContact(@PathVariable Long id, @RequestBody Contact contact) {
        return contactRepository.findById(id)
                .map(oldContact -> {
                    oldContact.setAddress(contact.getAddress());
                    oldContact.setBirthday(contact.getBirthday());
                    oldContact.setEmail(contact.getEmail());
                    oldContact.setFirstName(contact.getFirstName());
                    oldContact.setLastName(contact.getLastName());
                    oldContact.setPhoneNumber(contact.getPhoneNumber());
                    return contactRepository.save(oldContact);
                });
    }

    @ResponseStatus
    @DeleteMapping("/{id}")
    public HttpStatus deleteContact(@PathVariable Long id) {
        contactRepository.deleteById(id);
        return HttpStatus.NO_CONTENT;
    }
}
