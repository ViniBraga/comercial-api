package com.algaworks.comercial.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.algaworks.comercial.model.Opportunity;
import com.algaworks.comercial.repository.OpportunityRepository;

@CrossOrigin
@RestController
@RequestMapping("/opportunities")
public class OportunidadeController {

	@Autowired
	private OpportunityRepository opportunityRepository;
	
	@GetMapping
	public List<Opportunity> list() {
		return opportunityRepository.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Opportunity> fetch(@PathVariable Long id) {
		Optional<Opportunity> opportunity = opportunityRepository.findById(id);
		if(opportunity.isPresent()) {
			return ResponseEntity.ok(opportunity.get());			
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Opportunity add(@Valid @RequestBody Opportunity opportunity) {
		Optional<Opportunity> existingOpportunity = opportunityRepository
				.findByDescriptionAndProspectusName(opportunity.getDescription(), opportunity.getProspectusName());
		if(existingOpportunity.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
					"There is already an opportunity for this prospectus with the same description");
		}
		return opportunityRepository.save(opportunity);
	}
	
	//TODO Exclude
	
	//TODO Update
	
}
