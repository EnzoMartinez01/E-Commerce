package com.empresa.empresa.Repositories.Adresses;

import java.util.List;
import java.util.Optional;

import com.empresa.empresa.Models.Addresess.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empresa.empresa.Models.Addresess.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {
    Optional<State> findByName(String name);
    List<State> findByCountry(Country country);
}