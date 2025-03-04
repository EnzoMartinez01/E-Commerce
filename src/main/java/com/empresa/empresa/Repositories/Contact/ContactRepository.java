package com.empresa.empresa.Repositories.Contact;

import com.empresa.empresa.Models.Contact.Contact;
import com.empresa.empresa.Models.Contact.StatusContact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
    List<Contact> findByDniRuc(String dni_ruc);
    Page<Contact> findByStatus(StatusContact status, Pageable pageable);
}
