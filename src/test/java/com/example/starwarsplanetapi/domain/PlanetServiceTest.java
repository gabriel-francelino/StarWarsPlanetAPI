package com.example.starwarsplanetapi.domain;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.example.starwarsplanetapi.common.PlanetConstants.PLANET;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(classes = PlanetService.class)
public class PlanetServiceTest {
    @Autowired
    private PlanetService planetService;

    @MockBean
    private PlanetRepository planetRepository;
    @Test
    public void createPlanet_WithValidData_ReturnsPlanet(){
        // princípios AAA(Arrange, Act, Assert)
        // Arrange
        Mockito.when(planetRepository.save((PLANET))).thenReturn(PLANET);

        // Act
        // sut - system under test
        Planet sut = planetService.create(PLANET);

        // Assert
        assertThat(sut, equalTo(PLANET));
    }
}
