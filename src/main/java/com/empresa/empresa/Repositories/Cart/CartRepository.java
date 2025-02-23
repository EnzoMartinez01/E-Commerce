package com.empresa.empresa.Repositories.Cart;

import com.empresa.empresa.Models.Authentication.Users;
import com.empresa.empresa.Models.Cart.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Optional<Cart> findByUsers(Users users);

    Page<Cart> findByUsers(Pageable pageable, Users users);
}
