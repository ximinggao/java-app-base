package com.cgp.example.demo.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for Agent entities.
 */
@Repository
public interface AgentRepository extends JpaRepository<AgentJpaEntity, Long> {

	boolean existsByName(String name);
}