package com.example.MyWebApp.repo;

import com.example.MyWebApp.models.Stavka;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface StavkaRepository extends JpaRepository<Stavka, Integer> {
}
