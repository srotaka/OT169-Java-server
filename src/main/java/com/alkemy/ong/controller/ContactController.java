package com.alkemy.ong.controller;

import com.alkemy.ong.entity.Contact;
import com.alkemy.ong.service.impl.ContactServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/contacts")
public class ContactController {

    @Autowired
    private ContactServiceImpl contactService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Contact contact) throws Exception {
        try{
            Contact contact1 = contactService.create(contact);
            return ResponseEntity.status(CREATED).body(contact1);
        }catch (Exception e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }

    }

    @GetMapping
    public ResponseEntity<?> getContactList(){
        try{
            return ResponseEntity.status(OK).body(contactService.getContactList());
        }catch (Exception e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }
}
