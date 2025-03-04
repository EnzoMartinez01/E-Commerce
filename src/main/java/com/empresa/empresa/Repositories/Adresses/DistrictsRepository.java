package com.empresa.empresa.Repositories.Adresses;

import java.util.List;
import java.util.Optional;

import com.empresa.empresa.Models.Addresess.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empresa.empresa.Models.Addresess.Districts;

@Repository
public interface DistrictsRepository extends JpaRepository<Districts, Integer> {
    Optional<Districts> findByName(String name);
    List<Districts> findByProvince(Province province);
}
