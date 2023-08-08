package com.example.starwarsplanetapi.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static com.example.starwarsplanetapi.common.PlanetConstants.PLANET;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(classes = PlanetService.class)
public class PlanetServiceTest {
    @Autowired
    private PlanetService planetService;
    @Test
    public void createPlanet_WithValidData_ReturnsPlanet(){
        // sut - system under test
        Planet sut = planetService.create(PLANET);
        assertThat(sut, equalTo(PLANET));
    }
}
