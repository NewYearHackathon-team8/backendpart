package com.project.newyearthon.repository;

import com.project.newyearthon.domain.Matching;
import com.project.newyearthon.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatchingRepository extends JpaRepository<Matching, Long> {
}



