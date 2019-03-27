package com.algaworks.comercial.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.comercial.model.Opportunity;

public interface OpportunityRepository extends JpaRepository<Opportunity, Long>{

	Optional<Opportunity> findByDescriptionAndProspectusName(String description, String ProspectusName);
	
}
