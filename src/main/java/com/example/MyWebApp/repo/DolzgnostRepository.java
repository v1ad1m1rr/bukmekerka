package com.example.MyWebApp.repo;

import com.example.MyWebApp.models.Dolzgnost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface DolzgnostRepository extends JpaRepository<Dolzgnost, Integer> {
}
