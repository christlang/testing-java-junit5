package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.ModelTests;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OwnerTest implements ModelTests {

    @Test
    void dependentAssertions() {
        Owner owner = new Owner(1L, "Joe", "Buck");
        owner.setCity("Key West");
        owner.setTelephone("123");

        assertAll("Properties Test",
                () -> assertAll("Person Properties",
                        () -> assertEquals("Joe", owner.getFirstName(), "First Name did not match"),
                        () -> assertEquals("Buck", owner.getLastName(), "Last Name did not match")
                ),
                () -> assertAll("Owner Properties",
                        () -> assertEquals("Key West", owner.getCity(), "City did not match"),
                        () -> assertEquals("123", owner.getTelephone(), "Telefone did not match")
                )
            );

        // hamcrest
        assertThat(owner.getCity(), is("Key West"));
    }

}