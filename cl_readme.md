


# 47. Assertions

* https://junit.org/junit5/docs/5.0.1/api/org/junit/jupiter/api/Assertions.html


```shell
assertEquals(2, 2)
assertNotEquals(2, 2)
assertEquals(2, 2, "Values do not match")
```

* JUnit 5 supports lambdas
* Grouped Assertions
* Dependent Assertions
* Expected exceptions
* Timeouts

Frameworks to use
* AssertJ
* Hamcrest
* Truth

# 49 Grouped Assertions

```groovy
    @Test
    void groupedAssertions2() {
        // given
        Person person = new Person(11L, "Joe", "Buck");
        // then
        assertAll("Test Props Set",
                () -> assertEquals(person.getFirstName(), "Joe 2"),
                () -> assertEquals(person.getLastName(), "Buck 2")
        );
    }
```

# 50. Dependent Assertions

```groovy
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
    }
```

# 54. Timeouts

* assertTimeout
* assertTimeoutPreemptive - eigener Thread der originalen Thread nach timeout abschießt


# 55. Assumption

* unterschiedliche Tests in Environments
  * assumeTrue -> bei Fehlschlag wird Test als deaktiviert angezeigt

# 56. Conditional Test Execution

* https://junit.org/junit5/docs/current/user-guide/#writing-tests-conditional-execution

* Bedingt auf
  * OS - @EnabledOnOs(OS.WINDOWS)
  * Java Runtime Environment - @EnabledOnJre(JRE.JAVA_17)
  * System Property
  * Environment Variable - @EnabledIfEnvironmentVariable(named="USER", matches="lange")
  * Custom Conditions


# 57. Using AssertJ with JUnit

* https://github.com/assertj/assertj-core
* https://assertj.github.io/doc/

```xml
        <dependency>
            <groupId>org.assertj</groupId>
            <artifactId>assertj-core</artifactId>
            <version>3.22.0</version>
            <scope>test</scope>
        </dependency>
```