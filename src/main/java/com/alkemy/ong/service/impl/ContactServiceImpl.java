package com.alkemy.ong.service.impl;

import com.alkemy.ong.entity.Contact;
import com.alkemy.ong.repository.ContactRepository;
import com.alkemy.ong.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Contact create(Contact contact) throws Exception {
        if(contact.getName().isEmpty() || contact.getName() == null){
            throw new Exception("Name is empty");
        }
        if(contact.getEmail().isEmpty() || contact.getEmail() == null){
            throw new Exception("Email is empty");
        }
        contactRepository.save(contact);
        return contact;
    }
}
