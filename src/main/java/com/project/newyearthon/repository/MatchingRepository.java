package com.project.newyearthon.repository;

import com.project.newyearthon.domain.Matching;
import com.project.newyearthon.domain.role.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MatchingRepository extends JpaRepository<Matching, Long> {
    @Query("SELECT m FROM Matching m WHERE m.guest = :guest ORDER BY m.id ASC")
    Optional<Matching> findFirstByGuest(@Param("guest") Guest guest);
}



