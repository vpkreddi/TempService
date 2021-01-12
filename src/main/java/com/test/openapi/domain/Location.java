package com.test.openapi.domain;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Node
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Location {
	@Id @GeneratedValue
	private Long id;
	@NotNull(message = "latitude cannot be null")
	private Double latitude;
	@NotNull(message = "latitude cannot be null")
	private Double longitude;
	@NotNull(message = "latitude cannot be null")
	private Double temperature;
	@CreatedDate
	private LocalDateTime createdTime;
	@LastModifiedDate
	private LocalDateTime updatedTime;
	
	public Location(Long id, Double latitude, Double longitude,Double temperature) {
		super();
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.temperature = temperature;
	}
	
	
}
