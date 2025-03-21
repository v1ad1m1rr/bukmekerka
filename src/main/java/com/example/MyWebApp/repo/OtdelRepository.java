package com.example.MyWebApp.repo;

import com.example.MyWebApp.models.Otdel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface OtdelRepository extends JpaRepository<Otdel, Integer> {
}
