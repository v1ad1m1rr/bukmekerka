package com.example.MyWebApp.repo;

import com.example.MyWebApp.models.Viplata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ViplataRepository extends JpaRepository<Viplata, Integer> {
}
