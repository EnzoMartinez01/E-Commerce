package com.empresa.empresa.Controllers.Contact;

import com.empresa.empresa.Dto.Contact.FAQDto;
import com.empresa.empresa.Models.Contact.FAQ;
import com.empresa.empresa.Services.Contact.FAQService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/faq")
public class FAQController {
    private final FAQService faqService;

    public  FAQController(FAQService faqService){
        this.faqService = faqService;
    }

    //Get all Faq
    @GetMapping("/getAllFaq")
    public Page<FAQDto> getAllFaqs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10")int size){
        return  faqService.getAllFaq(page, size);
    }

    //Get Faq by User
    @GetMapping("/getAllFaqs/{idUser}")
    public List<FAQDto> getById(@PathVariable Integer idUser){
        return faqService.getFaqbyId(idUser);
    }

    //Add Faq
    @PostMapping("/addFaq")
    public ResponseEntity<Map<String, String>> addFaq(@RequestBody FAQ faqs){
        try{
            FAQ savedFaq = faqService.addFaq(faqs);
            Map<String,String> response = new HashMap<>();
            response.put("message", "FAQ Creado Satisfactoriamente");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar FAQ", e);
        }
    }

    //Update Faq
    @PutMapping("/updateFaq/{idFaq}")
    public ResponseEntity<Map<String,String>> updateFaq(
            @PathVariable Integer idFaq,
            @RequestBody FAQ updateFaq){
        try{
            faqService.updateFaq(idFaq, updateFaq);
            Map<String,String> response = new HashMap<>();
            response.put("message", "FAQ se actualizo Correctamente");
            return  ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception e){
            throw new RuntimeException("Error al actualizar FAQ", e);
        }
    }

    //Deactivate FAQ
    @PatchMapping("/deactivateFaq/{idFaq}")
    public ResponseEntity<Map<String, String>> deactivateFaq(@PathVariable Integer idFaq){
        try{
            faqService.deactivateFaq(idFaq);
            Map<String, String> response = new HashMap<>();
            response.put("message", "FAQ desactivado Correctamente");
            return  ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception e){
            throw new RuntimeException("Error al desactivar FAQ", e);
        }
    }
}
