package com.example.MyWebApp.repo;

import com.example.MyWebApp.models.Adres_sotr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface Adres_sotrRepository extends JpaRepository<Adres_sotr, Integer> {
}
