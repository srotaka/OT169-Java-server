package com.alkemy.ong.service;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.entity.Contact;

import java.util.List;

public interface ContactService {
    ContactDto create (ContactDto dto) throws Exception;

    List<ContactDto> getContactList()throws Exception;
}
