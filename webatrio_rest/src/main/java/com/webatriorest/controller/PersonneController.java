package com.webatriorest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webatrio.adapter.PersonneServiceAdapter;
import com.webatrio.dto.PersonneDto;


@RestController
@RequestMapping("/personne")
@CrossOrigin
public class PersonneController {
	
	@Autowired
	private PersonneServiceAdapter service;
	
	@PostMapping("/createPersonne")
	public ResponseEntity<?> createTeam(@RequestBody PersonneDto personne) {
		try {
			service.createEquipe(personne);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/loadPersonnes")
	public List<PersonneDto> loadPersonnes(){
			return service.loadPersonnes();
	}

}
