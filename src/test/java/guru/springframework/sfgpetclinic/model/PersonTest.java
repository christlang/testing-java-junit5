package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void groupedAssertions() {
        // given
        Person person = new Person(11L, "Joe", "Buck");
        // then
        assertAll("Test Props Set",
                () -> assertEquals("Joe", person.getFirstName()),
                () -> assertEquals("Buck", person.getLastName())
        );
    }

//    @Test
//    void groupedAssertions2() {
//        // given
//        Person person = new Person(11L, "Joe", "Buck");
//        // then
//        assertAll("Test Props Set",
//                () -> assertEquals("Joe 2", person.getFirstName(), "First Name Failed"),
//                () -> assertEquals("Buck 2", person.getLastName(), "Second Name Failed")
//        );
//    }

}