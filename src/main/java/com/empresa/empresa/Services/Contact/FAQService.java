package com.empresa.empresa.Services.Contact;

import com.empresa.empresa.Dto.Contact.FAQDto;
import com.empresa.empresa.Models.Authentication.CustomUserDetails;
import com.empresa.empresa.Models.Authentication.Users;
import com.empresa.empresa.Models.Contact.FAQ;
import com.empresa.empresa.Repositories.Authentication.UsersRepository;
import com.empresa.empresa.Repositories.Contact.FAQRepository;
import jakarta.transaction.Transactional;
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
public class FAQService {
    private final static Logger logger = LoggerFactory.getLogger(FAQService.class);

    private final UsersRepository usersRepository;
    private final FAQRepository faqRepository;

    public FAQService(UsersRepository usersRepository,
                      FAQRepository faqRepository ){
        this.usersRepository = usersRepository;
        this.faqRepository = faqRepository;
    }

    //Get all FAQ
    public Page<FAQDto> getAllFaq(int page, int size, Boolean isActive) {
        Pageable pageable = PageRequest.of(page, size);
        Page<FAQ> faqs = faqRepository.findByIsActive(isActive, pageable);
        return faqs.map(this::mapToDto);
    }

    //Get FAQ by User
    public List<FAQDto> getFaqbyId(Integer idUsers){
        Users users = usersRepository.findById(idUsers)
                .orElseThrow(() -> new RuntimeException("Usuario no Encontrado"));
        List<FAQ> faqs = faqRepository.findByCreatedBy(users);
        return faqs.stream().map(this::mapToDto).toList();
    }

    //Map to Dto
    public FAQDto mapToDto(FAQ faq){
        FAQDto dto = new FAQDto();
        dto.setIdFaq(faq.getId());
        dto.setQuestionFaq(faq.getQuestion());
        dto.setAnswerFaq(faq.getAnswer());
        dto.setIsActive(faq.getIsActive());
        dto.setIdUser(faq.getId());
        dto.setCreatedbyFaq(faq.getCreatedBy().getFullname());
        dto.setCreatedatFaq(faq.getCreated_at());
        return dto;
    }

    //Add Faq
    @Transactional
    public FAQ addFaq(FAQ faq){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            Users currentUser = customUserDetails.getUsers();

            Users users = usersRepository.findById(currentUser.getId())
                    .orElseThrow(() -> new RuntimeException("Usuario no Encontrado"));
            faq.setCreatedBy(users);
            faq.setCreated_at(LocalDateTime.now());
            faq.setIsActive(true);

            return  faqRepository.save(faq);
        } catch (Exception e){
            logger.error("Error al añadir FAQ", e);
            throw new RuntimeException("Error al añadir FAQ",e);
        }
  }

  //Update Faq
    public FAQ updateFaq(Integer idFaq, FAQ updatedFaq){
        try {
            FAQ existingFaq = faqRepository.findById(idFaq)
                    .orElseThrow(() -> new RuntimeException("FAQ no encontrado"));

            if (updatedFaq.getQuestion() != null && !updatedFaq.getQuestion().isEmpty()) {
                existingFaq.setQuestion(updatedFaq.getQuestion());
            }
            if (updatedFaq.getAnswer() != null && !updatedFaq.getAnswer().isEmpty()) {
                existingFaq.setAnswer(updatedFaq.getAnswer());
            }
            if (updatedFaq.getIsActive() != null) {
                existingFaq.setIsActive(updatedFaq.getIsActive());
            }
            return faqRepository.save(existingFaq);
        } catch (Exception e){
            logger.error("Error al actualizar FAQ",e);
            throw  new RuntimeException("Error al actualizar FAQ", e);
        }
    }

    //Deactivate FAQ
    public void deactivateFaq(Integer idFaq){
        try {
            FAQ faq = faqRepository.findById(idFaq)
                    .orElseThrow(() -> new RuntimeException("FAQ not found with ID: " + idFaq));

            if (!faq.getIsActive()) {
                throw new IllegalStateException("FAQ is already deactivated.");
            }

            faq.setIsActive(false);
            faqRepository.save(faq);
        } catch (Exception e){
            logger.error("Error al desactivar FAQ",e);
            throw  new RuntimeException("Error al desactivar FAQ", e);
        }
    }
}
