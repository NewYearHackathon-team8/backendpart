package com.project.newyearthon.repository;

import com.project.newyearthon.domain.role.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Integer> {
}
