package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.entity.Contact;
import com.alkemy.ong.repository.ContactRepository;
import com.alkemy.ong.service.ContactService;
import com.alkemy.ong.service.EmailService;
import com.alkemy.ong.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private EmailService emailService;

    @Override
    @Transactional
    public Contact create(Contact contact) throws Exception {
        if(contact.getName().isEmpty() || contact.getName() == null){
            throw new Exception("Name is empty");
        }
        if(contact.getEmail().isEmpty() || contact.getEmail() == null){
            throw new Exception("Email is empty");
        }
        contactRepository.save(contact);
        emailService.sendContactMail(contact.getEmail(), contact.getName());
        return contact;
    }

    @Override
    public List<ContactDto> getContactList() throws Exception {
        List<Contact> contacts = contactRepository.findAll();
        if(contacts.isEmpty()){
            throw new Exception("List Contact is empty");
        }
        List<ContactDto> dtos = new ArrayList<>();
        for(Contact entity : contacts){
            dtos.add(Mapper.mapToDto(entity,new ContactDto()));
        }
        return dtos;
    }

}
