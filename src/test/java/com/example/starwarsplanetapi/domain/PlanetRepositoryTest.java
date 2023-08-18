package com.example.starwarsplanetapi.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static com.example.starwarsplanetapi.common.PlanetConstants.PLANET;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

//@SpringBootTest(classes = PlanetRepository.class)
@DataJpaTest // com essa anotação não será necessário o @SpringBootTest
public class PlanetRepositoryTest {
    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @AfterEach
    public void afterEach(){
        PLANET.setId(null); // depois de cada teste o id de PLANET será null
    }

    @Test
    public void createPlanet_WithValidData_ReturnsPlanet(){
        Planet planet = planetRepository.save(PLANET);

        Planet sut = testEntityManager.find(Planet.class, planet.getId());
        System.out.println(planet.toString());

        assertThat(sut).isNotNull();
        assertThat(sut.getName()).isEqualTo(PLANET.getName());
        assertThat(sut.getClimate()).isEqualTo(PLANET.getClimate());
        assertThat(sut.getTerrain()).isEqualTo(PLANET.getTerrain());
    }

    @Test
    public void createPLanet_WithInvalidData_ThrowsException(){
        Planet emptyPlanet = new Planet();
        Planet invalidPlanet = new Planet("","","");

        assertThatThrownBy(() -> planetRepository.save(emptyPlanet)).isInstanceOf(RuntimeException.class);
        assertThatThrownBy(() -> planetRepository.save(invalidPlanet)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void createPLanet_WithExistingName_ThrowsException(){
        Planet planet = testEntityManager.persistFlushFind(PLANET);
        testEntityManager.detach(planet);
        planet.setId(null);

        assertThatThrownBy(() -> planetRepository.save(planet)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void getPlanet_ByExistingId_ReturnsPlanet(){
        Planet planet = testEntityManager.persistFlushFind(PLANET);

        Optional<Planet> sut = planetRepository.findById(planet.getId());

        assertThat(sut).isNotEmpty();
        assertThat(sut.get()).isEqualTo(planet);
    }

    @Test
    public void getPlanet_ByUnexistingId_ReturnsNotFound(){
        Optional<Planet> sut = planetRepository.findById(-9L);

        assertThat(sut).isEmpty();
    }

    @Test
    public void getPlanet_ByExistingName_ReturnsPlanet(){
        Planet planet =  testEntityManager.persistFlushFind(PLANET);

        Optional<Planet> sut = planetRepository.findByName(planet.getName());

        assertThat(sut).isNotEmpty();
        assertThat(sut.get()).isEqualTo(planet);
    }

    @Test
    public void getPlanet_ByUnexistingName_ReturnsNotFound()    {
        Optional<Planet> sut = planetRepository.findByName("Unexisting name");

        assertThat(sut).isEmpty();
    }
}
