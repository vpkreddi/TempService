package com.test.openapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.test.openapi.domain.Location;
import com.test.openapi.domain.dto.LocationDTO;
import com.test.openapi.domain.dto.WeatherResponseDTO;
import com.test.openapi.feign.clients.WeatherService;
import com.test.openapi.repo.LocationRepo;

@Service
public class LocationService {
	@Autowired
	LocationRepo repo;
	@Autowired
	WeatherService weatherService;
	@Value("${weather.api.key}")
	String appKey;

	public Optional<Object> createOrUpdateLocationTemperature(@RequestBody LocationDTO dto) {
		Optional<Location> dbObj = repo.findByLatitudeAndLongitude(dto.getLat(), dto.getLon());
		if (dto.getId() == null && dbObj.isPresent()) {
			return Optional.empty();
		} else if (dbObj.isPresent()) {
			return Optional.of(repo.save(new Location(dto.getId(), dto.getLat(), dto.getLon(),
					dto.getTemperature(), dbObj.get().getCreatedTime(), null)));
		} else {
			return Optional.of(repo
					.save(new Location(dto.getId(), dto.getLat(), dto.getLon(), dto.getTemperature())));
		}
	}

	public Optional<Location> findByLatitudeAndLongitude(Double lat, Double lon) {
		Optional<Location> location = repo.findByLatitudeAndLongitude(lat, lon);
		if(location.isEmpty()) {
			WeatherResponseDTO dto = weatherService.getTemperatureByCoords(lat, lon, appKey);
			return Optional.of(repo
					.save(new Location(null, lat, lon,dto.getMain().getTemp())));
		}
		else {
			return location;
		}
	}
}