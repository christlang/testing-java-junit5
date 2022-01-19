package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.Model;
import guru.springframework.sfgpetclinic.fauxspring.ModelMap;
import guru.springframework.sfgpetclinic.fauxspring.ModelMapImpl;
import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.map.SpecialityMapService;
import guru.springframework.sfgpetclinic.services.map.VetMapService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class VetControllerTest {

    VetController controller;
    private SpecialtyService specialtyService;
    private VetMapService vetService;

    @BeforeEach
    void setUp() {
        specialtyService = new SpecialityMapService();
        vetService = new VetMapService(specialtyService);

        controller = new VetController(vetService);
        Vet vet1 = new Vet(1L, "melissa", "hover", null);
        Vet vet2 = new Vet(2L, "charles", "soper", null);

        vetService.save(vet1);
        vetService.save(vet2);
    }

    @Test
    @DisplayName("listVets: right template is returned")
    void listVetsTemplate() {
        ModelMapImpl model = new ModelMapImpl();
        String result = controller.listVets(model);

        assertEquals("vets/index", result);
    }

    @Test
    @DisplayName("listVets: returns list")
    void listVetsEmpty() {
        ModelMapImpl model = new ModelMapImpl();

        controller.listVets(model);

        Set<Vet> result = (Set) model.getMap().get("vets");
        assertEquals(2, result.size());
    }

}