package com.empresa.empresa.Repositories.Adresses;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empresa.empresa.Models.Addresess.Districts;

@Repository
public interface DistrictsRepository extends JpaRepository<Districts, Integer> {
    Optional<Districts> findByName(String name);
}
