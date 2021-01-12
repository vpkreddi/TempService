package com.test.openapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.openapi.domain.Location;
import com.test.openapi.domain.dto.LocationDTO;
import com.test.openapi.repo.LocationRepo;
import com.test.openapi.service.LocationService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/temperature")
public class LocationController {
	@Autowired
	LocationRepo repo;
	@Autowired
	LocationService service;

	@ApiOperation(value = "Create Or Update Location Temperature",
		      notes = "Create new Location node  or Update the existing node on graph database")
	@PostMapping
	public ResponseEntity<? super Object> createOrUpdateLocationTemperature(@RequestBody LocationDTO dto) {
		Optional<Object> response = service.createOrUpdateLocationTemperature(dto);
		if(response.isEmpty()){
			return new ResponseEntity<>("unique constraint voilated",HttpStatus.NOT_FOUND);
		} else {
			return ResponseEntity.ok(response.get());
		}
	}
	@ApiOperation(value = "Get Location Temperature",
		      notes = "get location temperature using latitude and longitude")
	@GetMapping
	public ResponseEntity<? super Object> getLocationTemperature(@RequestParam Double lat, @RequestParam Double lon) {
		Optional<Location> dbObj = service.findByLatitudeAndLongitude(lat, lon);
		if(dbObj.isEmpty()) {
			return new ResponseEntity<>("data not found", HttpStatus.NOT_FOUND);
		} else {
			return ResponseEntity.ok(dbObj.get());
		}
	}
	@ApiOperation(value = "Get All Location Temperature Nodes from db",
		      notes = "ll Location Temperature Nodes from db")
	@GetMapping("/all")
	public List<Location> getAllTemperatures() {
		return repo.findAll();
	}
	@ApiOperation(value = "Delete location node from db",
		      notes = "Delete location node from db using id")
	@DeleteMapping
	public Boolean deleteLocationTemperature(@RequestParam Long id) {
		repo.deleteById(id);
		return repo.findById(id).isEmpty();
	}
	@ApiOperation(value = "Delete location node from db",
		      notes = "Delete location node from db using latitude and longitude")
	@DeleteMapping("/coords")
	public Boolean deleteLocationTemperature(@RequestParam Double lat, @RequestParam Double lon) {
		Optional<Location> location = repo.findByLatitudeAndLongitude(lat, lon);
		if (location.isPresent()) {
			repo.deleteById(location.get().getId());
			return true;
		}
		return false;
	}
}
