package com.controllers;

import com.dtos.MembreDto;
import org.springframework.web.bind.annotation.*;

import com.services.impl.MembreServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/membre")
public class MembreController {
	
	private final MembreServiceImpl membreService;

	public MembreController(MembreServiceImpl membreService) {
		this.membreService = membreService;
	}

	/**
	 * <p>Get all membres in the system</p>
	 * @return List<MembreDto>
	 */
	@GetMapping
	public List<MembreDto> getMembres() {
		return membreService.getAllMembres();
	}

	/**
	 * Method to get the membre based on the ID
	 */
	@GetMapping("/{id}")
	public MembreDto getMembre(@PathVariable Long id){
		return membreService.getMembreById(id);
	}

	/**
	 * Create a new Membre in the system
	 */
	@PostMapping
	public MembreDto saveMembre(final @RequestBody MembreDto membreDto){
		return membreService.saveMembre(membreDto);
	}

	/**
	 * Delete a membre by it's id
	 */
	@DeleteMapping("/{id}")
	public Boolean deleteMembre(@PathVariable Long id){
		return membreService.deleteMembre(id);
	}
	
}
