package com.empresa.empresa.Repositories.Adresses;

import com.empresa.empresa.Models.Addresess.Address;
import com.empresa.empresa.Models.Authentication.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    Page<Address> findByUsers(Pageable pageable, Users users);
}
