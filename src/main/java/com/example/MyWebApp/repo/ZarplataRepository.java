package com.example.MyWebApp.repo;

import com.example.MyWebApp.models.Zarplata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ZarplataRepository extends JpaRepository<Zarplata, Integer> {
}
