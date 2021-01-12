package com.test.openapi.repo;

import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.test.openapi.domain.Location;

public interface LocationRepo extends Neo4jRepository<Location, Long> {

	Optional<Location> findByLatitudeAndLongitude(Double lat, Double lon);
}
