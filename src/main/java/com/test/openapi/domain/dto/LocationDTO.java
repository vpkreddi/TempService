package com.test.openapi.domain.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationDTO {
	private Long id;
	@NotNull(message = "latitude cannot be null")
	@JsonProperty("latitude")
	private Double lat;
	@NotNull(message = "latitude cannot be null")
	@JsonProperty("longitude")
	private Double lon;
	@NotNull(message = "latitude cannot be null")
	@JsonProperty("temperature")
	private Double temperature;
}
