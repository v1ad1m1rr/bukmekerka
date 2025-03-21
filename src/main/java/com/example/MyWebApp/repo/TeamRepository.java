package com.example.MyWebApp.repo;

import com.example.MyWebApp.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Integer> {
}
