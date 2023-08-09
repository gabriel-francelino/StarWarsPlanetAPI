package com.example.starwarsplanetapi.domain;

import org.assertj.core.api.Assertions;
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
import static com.example.starwarsplanetapi.common.PlanetConstants.INVALID_PLANET;

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

    @Test
    public void createPlanet_WithInvalidData_ThrowsException(){
        Mockito.when(planetRepository.save(INVALID_PLANET)).thenThrow(RuntimeException.class);
        Assertions.assertThatThrownBy(() -> planetService.create(INVALID_PLANET)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void getPlanet_ByExistingId_ReturnsPlanet(){
        //TODO
    }

    @Test
    public void getPlanet_ByUnexistingId_ReturnsEmpty(){
        //TODO
    }
}



