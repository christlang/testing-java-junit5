package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.Model;
import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.VetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class VetControllerTest {

    VetController controller;
    private VetServiceMock vetServiceMock;
    private ModelMock modelMock;

    static class VetServiceMock implements VetService {

        Set<Vet> store = new HashSet<>();

        @Override
        public Set<Vet> findAll() {
            return store;
        }

        @Override
        public Vet findById(Long aLong) {
            return null;
        }

        @Override
        public Vet save(Vet object) {
            store.add(object);

            return object;
        }

        @Override
        public void delete(Vet object) {

        }

        @Override
        public void deleteById(Long aLong) {

        }
    }

    static class ModelMock implements Model {

        Map<String, Object> store = new HashMap<>();
        Object attribute;


        @Override
        public void addAttribute(String key, Object o) {
            store.put(key, o);
        }

        @Override
        public void addAttribute(Object o) {
            attribute = o;
        }
    }

    @BeforeEach
    void setUp() {
        modelMock = new ModelMock();
        vetServiceMock = new VetServiceMock();
        controller = new VetController(vetServiceMock);
    }

    @Test
    @DisplayName("right template is returned")
    void listVetsTemplate() {
        String result = controller.listVets(modelMock);

        assertEquals("vets/index", result);
    }

    @Test
    @DisplayName("Vets can be returned, empty List")
    void listVetsEmpty() {
        controller.listVets(modelMock);

        assertTrue( modelMock.store.containsKey("vets"), "no attribute with name vets was added");
        Set<Vet> vets = new HashSet<>();
        assertEquals(vets, modelMock.store.get("vets"));
    }

    @Test
    @DisplayName("Vets can be returned, one element")
    void listVetsOneElement() {
        vetServiceMock.save(new Vet(1L, "first name", "last name", new HashSet<>()));
        controller.listVets(modelMock);

        Set<Vet> vets = new HashSet<>();
        vets.add(new Vet(1L, "first name", "last name", new HashSet<>()));
        assertEquals(vets, modelMock.store.get("vets"));
    }
}