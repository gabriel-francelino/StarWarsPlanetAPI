package com.example.starwarsplanetapi.web;

import com.example.starwarsplanetapi.domain.Planet;
import com.example.starwarsplanetapi.domain.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlanetController {
    @Autowired
    private PlanetService planetService;

    @PostMapping("/planets")
    public ResponseEntity<Planet> create(@RequestBody Planet planet){
        Planet planetCreated = planetService.create(planet);
        return ResponseEntity.status(HttpStatus.CREATED).body(planetCreated);

    }


}
