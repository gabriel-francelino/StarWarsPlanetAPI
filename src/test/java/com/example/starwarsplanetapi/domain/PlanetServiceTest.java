package com.example.starwarsplanetapi.domain;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.example.starwarsplanetapi.common.PlanetConstants.PLANET;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

// @SpringBootTest(classes = PlanetService.class) - Não é muito eficiente quando se coloca vários testes
@ExtendWith(MockitoExtension.class) // Mais eficiente
public class PlanetServiceTest {
    //@Autowired - com spring boot
    @InjectMocks
    private PlanetService planetService;

    //@MockBean - com spring boot
    @Mock
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
        MatcherAssert.assertThat(sut, Matchers.equalTo(PLANET));
    }
}
