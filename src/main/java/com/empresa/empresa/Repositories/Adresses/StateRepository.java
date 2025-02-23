package com.empresa.empresa.Repositories.Adresses;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empresa.empresa.Models.Addresess.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {
    Optional<State> findByName(String name);
}