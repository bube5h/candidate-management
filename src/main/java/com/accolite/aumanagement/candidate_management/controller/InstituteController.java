package com.accolite.aumanagement.candidate_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.aumanagement.candidate_management.model.Institute;
import com.accolite.aumanagement.candidate_management.model.Skill;
import com.accolite.aumanagement.candidate_management.repository.InstituteRepository;
import com.accolite.aumanagement.candidate_management.repository.SkillRepository;

@CrossOrigin
@RestController
@RequestMapping("/institutes")
public class InstituteController 
{
	@Autowired
	InstituteRepository instituteRepository;
	
	@GetMapping
	public ResponseEntity<?> getAllInstitutes()
	{
		List<Institute> institutes = null;
		institutes = instituteRepository.getAllInstitutes();
		if(institutes.isEmpty())
		{
			return new ResponseEntity<String>("Empty",HttpStatus.NOT_FOUND); 
		}
		return new ResponseEntity<List<Institute>>(institutes,HttpStatus.OK); 
	}
}
