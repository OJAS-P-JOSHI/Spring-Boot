package com.example.myproject.controllers;

import com.example.myproject.models.Contact;
import com.example.myproject.repositories.ContactRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    private final ContactRepository contactRepository;

    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping("/messages")
    public List<Contact> getAllMessages() {
        return contactRepository.findAll();
    }

    @PostMapping
    public Contact createMessage(@RequestBody Contact contact) {
        return contactRepository.save(contact);
    }
}
