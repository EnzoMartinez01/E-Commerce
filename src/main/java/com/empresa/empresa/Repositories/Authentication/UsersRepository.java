package com.empresa.empresa.Repositories.Authentication;

import com.empresa.empresa.Models.Authentication.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByUsername(String username);
    Optional<Users> findByEmail(String email);

    @Query("""
        SELECT u FROM Users u 
        WHERE (:searchTerms IS NULL OR 
               LOWER(u.username) LIKE LOWER(CONCAT('%', :searchTerms, '%')) OR 
               LOWER(u.fullname) LIKE LOWER(CONCAT('%', :searchTerms, '%')) OR 
               LOWER(u.email) LIKE LOWER(CONCAT('%', :searchTerms, '%')) OR 
               LOWER(u.dni) LIKE LOWER(CONCAT('%', :searchTerms, '%')))
        AND (:roleId IS NULL OR u.role.id = :roleId)
        AND (:isActive IS NULL OR u.isActive = :isActive)
    """)
    Page<Users> findByFilters(
            @Param("searchTerms") String searchTerms,
            @Param("roleId") Integer roleId,
            @Param("isActive") Boolean isActive,
            Pageable pageable
    );
}
