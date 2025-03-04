package com.empresa.empresa.Repositories.Contact;

import com.empresa.empresa.Models.Authentication.Users;
import com.empresa.empresa.Models.Contact.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empresa.empresa.Models.Contact.FAQ;

import java.util.List;

@Repository
public interface FAQRepository extends JpaRepository<FAQ, Integer> {
    List<FAQ> findByCreatedBy(Users createdBy);
    Page<FAQ> findByIsActive(Boolean isActive, Pageable pageable);
}
