package com.test.openapi.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.openapi.domain.dto.WeatherResponseDTO;

@FeignClient(name = "weather-service" , url = "https://api.openweathermap.org/data/2.5/weather")
public interface WeatherService {


	@GetMapping
	WeatherResponseDTO getTemperatureByCoords(@RequestParam("lat")Double lat,
			@RequestParam("lon")Double lon,
			@RequestParam("appid")String appKey) ;
}
