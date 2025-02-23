package com.empresa.empresa.Repositories.Contact;

import com.empresa.empresa.Models.Contact.StatusContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusContactRepository extends JpaRepository<StatusContact, Integer> {
    Optional<StatusContact> findByName(String name);
}
