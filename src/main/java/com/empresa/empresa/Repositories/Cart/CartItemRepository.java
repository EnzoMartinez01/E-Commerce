package com.empresa.empresa.Repositories.Cart;

import com.empresa.empresa.Models.Authentication.Users;
import com.empresa.empresa.Models.Cart.CartItems;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItems, Integer> {
}
