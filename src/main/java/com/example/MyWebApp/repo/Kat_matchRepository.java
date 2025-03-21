package com.example.MyWebApp.repo;

import com.example.MyWebApp.models.Kat_match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface Kat_matchRepository extends JpaRepository<Kat_match, Integer> {
}
