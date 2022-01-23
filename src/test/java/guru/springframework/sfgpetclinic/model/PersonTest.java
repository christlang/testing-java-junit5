package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.ModelTests;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

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

    @DisplayName("try it")
    @RepeatedTest(3)
    void repeatedTest(RepetitionInfo ri) {
        System.out.println("try: " + ri.getCurrentRepetition());

    }    @RepeatedTest(value = 5, name = "{displayName} | {currentRepetition} - {totalRepetitions}")
    @DisplayName("My Assignment Repeated Test")
    void myAssignmentRepeated() {
        // todo impl
    }


    @DisplayName("Value Source Test ")
    @ParameterizedTest(name = "{displayName} [{index}] - {arguments}")
    @ValueSource(strings = {"Spring", "Framework", "Guru"})
    void testValueSource(String val) {
        System.out.println(val);
    }

    @DisplayName("Enum Source Test")
    @ParameterizedTest(name = "{displayName} [{index}] - {arguments}")
    @EnumSource(OwnerType.class)
    void enumTest(OwnerType ownerType) {
        System.out.println(ownerType);
    }
}