package com.empresa.empresa.Repositories.Adresses;

import java.util.List;
import java.util.Optional;

import com.empresa.empresa.Models.Addresess.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empresa.empresa.Models.Addresess.Province;

@Repository
public interface ProvincesRepository extends JpaRepository<Province, Integer> {
    Optional<Province> findByName(String name);
    List<Province> findByState(State state);
}
