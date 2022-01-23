package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.ModelTests;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonTest implements ModelTests {

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

    @BeforeEach
    void setUp(TestInfo ti, TestReporter tr) {
        System.out.println(ti);
        System.out.println(tr);
    }

    @DisplayName("try it")
    @RepeatedTest(3)
    void repeatedTest(RepetitionInfo ri) {
        System.out.println("try: " + ri.getCurrentRepetition());

    }

}