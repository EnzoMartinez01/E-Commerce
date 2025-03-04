package com.empresa.empresa.Controllers.Contact;


import com.empresa.empresa.Dto.Contact.ContactDto;
import com.empresa.empresa.Models.Contact.Contact;
import com.empresa.empresa.Models.Contact.StatusContact;
import com.empresa.empresa.Services.Contact.ContacService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/contacs")
public class ContactController {
    private final static Logger logger = LoggerFactory.getLogger(ContactController.class);
    private final ContacService contacService;

    public ContactController(ContacService contacService) {
        this.contacService = contacService;
    }

    //Get alL Contact
    @GetMapping("/getAllContacts")
    public Page<ContactDto> getallContacts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Integer status)
    {
        return  contacService.getAllContact(page,size, status);
    }

    //Get Contact by User
    @GetMapping("/getByUser/{dni}")
    public List<ContactDto> getByUser(@PathVariable String dni){
        return contacService.getContactByDni(dni);
    }

    //Get Contact by ID
    @GetMapping("/getContactById/{id}")
    public ContactDto getContactById(@PathVariable Integer id){
        return contacService.getContactById(id);
    }

    //Add Contact
    @PostMapping("/addContact")
    public ResponseEntity<Map<String, String>> addContact(@RequestBody Contact contacts) {
        try {
            Contact savedContact = contacService.addContact(contacts);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Contact created successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e){
            logger.error("Error al crear Contacto", e);
            throw new RuntimeException("Error al crear Contacto", e);
        }
    }

    // Updated Contact status
    @PutMapping("/updatedContactStatus/{idContact}/{idStatus}")
    public ResponseEntity<Map<String, String>> updatedContactStatus(@PathVariable Integer idContact,
                                                                     @PathVariable Integer idStatus,
                                                                    @RequestParam(required = false) String answer) {
        try {
            contacService.updatedContactStatus(idContact, idStatus, answer);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Contact status updated successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e){
            logger.error("Error al actualizar estado del Contacto", e);
            throw new RuntimeException("Error al actualizar estado del Contacto", e);
        }
    }

    //Get all Contact Status
    @GetMapping("/getAllContactStatus")
    public List<StatusContact> getAllContactStatus(){
        return contacService.getAllContactStatus();
    }
}
