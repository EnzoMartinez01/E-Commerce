package com.empresa.empresa.Services.Contact;

import com.empresa.empresa.Dto.Contact.ContactDto;
import com.empresa.empresa.Models.Authentication.CustomUserDetails;
import com.empresa.empresa.Models.Authentication.Users;
import com.empresa.empresa.Models.Contact.Contact;
import com.empresa.empresa.Models.Contact.StatusContact;
import com.empresa.empresa.Repositories.Adresses.*;
import com.empresa.empresa.Repositories.Authentication.UsersRepository;
import com.empresa.empresa.Repositories.Contact.ContactRepository;
import com.empresa.empresa.Repositories.Contact.StatusContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContacService {
    private final static Logger logger = LoggerFactory.getLogger(ContacService.class);

    private final UsersRepository usersRepository;
    private final ContactRepository contactRepository;
    private final StatusContactRepository statusContactRepository;

    public ContacService(UsersRepository usersRepository,
                         ContactRepository contactRepository,
                         StatusContactRepository statusContactRepository) {
        this.usersRepository = usersRepository;
        this.contactRepository = contactRepository;
        this.statusContactRepository = statusContactRepository;
    }

    //Get all Contact
    public Page<ContactDto> getAllContact(int page, int size, Integer status){
        StatusContact statusContact = statusContactRepository.findById(status)
                .orElseThrow(() -> new RuntimeException("Status Contact not found"));
        Pageable pageable = PageRequest.of(page, size);
        Page<Contact> contacts = contactRepository.findByStatus(statusContact, pageable);
        return contacts.map(this::mapToDto);
    }

    //Get Contact by User
    public List<ContactDto> getContactByDni(String dni){
        List<Contact> contacts = contactRepository.findByDniRuc(dni);
        return contacts.stream().map(this::mapToDto).toList();
    }

    // Get Contact by ID
    public ContactDto getContactById(Integer id){
        Contact contact = contactRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Contact not found with ID: " + id));
        return mapToDto(contact);
    }

    //Map to Dto
    public ContactDto mapToDto(Contact contact){
        ContactDto dto = new ContactDto();
        dto.setIdContact(contact.getId());
        dto.setNamesContact(contact.getNames());
        dto.setLastnameContact(contact.getLastname());
        dto.setSocialreasonContact(contact.getSocial_reason());
        dto.setEmailContact(contact.getEmail());
        dto.setDnirucContact(contact.getDniRuc());
        dto.setTelephoneContact(contact.getTelephone());
        dto.setMessageContact(contact.getMessage());
        dto.setIdUser(contact.getCreate_by().getId());
        dto.setCreatebyContact(contact.getCreate_by().getFullname());
        dto.setCreatedatContact(contact.getCreate_at());
        dto.setIsagreeContact(contact.getIs_agree());
        dto.setIdStatus(contact.getStatus().getId());
        dto.setStatusContact(contact.getStatus().getName());
        dto.setAnswer(contact.getAnswer());
        return dto;
    }
    
    //Add Contact@Transactional
    public Contact addContact(Contact contacts){
        try {
            StatusContact statusContact = statusContactRepository.findById(1)
                    .orElseThrow(() -> new RuntimeException("Status Contact not found"));

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            Users currentUser = customUserDetails.getUsers();

            Users users = usersRepository.findById(currentUser.getId()).orElseThrow(() -> new RuntimeException("User not found"));

            contacts.setCreate_by(users);
            contacts.setStatus(statusContact);
            contacts.setCreate_at(LocalDateTime.now());

            return contactRepository.save(contacts);
        } catch (Exception e){
            logger.error("Error al añadir contacto", e);
            throw  new RuntimeException("Error al añadir contacto", e);
        }
    }


    // Updated Contact status
    public void updatedContactStatus(Integer idContact, Integer idStatus, String answer){
        StatusContact status = statusContactRepository.findById(idStatus)
                .orElseThrow(() -> new RuntimeException("Status Contact not found"));

        Contact contact = contactRepository.findById(idContact)
                .orElseThrow(() -> new RuntimeException("Contact not found"));

        contact.setStatus(status);
        contact.setAnswer(answer);
        contactRepository.save(contact);
    }

    // Get all Contact Status
    public List<StatusContact> getAllContactStatus(){
        return statusContactRepository.findAll();
    }
}
