


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

```groovy
// AssertJ
assertThat(controller.index()).isEqualTo("index");
```

# 58. Using Hamcrest with JUnit

* https://search.maven.org/search?q=g:org.hamcrest
* https://github.com/hamcrest/JavaHamcrest

```xml
        <dependency>
            <groupId>org.hamcrest</groupId>
            <artifactId>hamcrest-library</artifactId>
            <version>2.2</version>
            <scope>test</scope>
        </dependency>
```


```groovy
// hamcrest
assertThat(owner.getCity(), is("Key West"));
```

# 59. Assignment - Write JUnit Test for VetController List Vets

* Create new Test for VetController
* Test List Vets Method
* Verify return value for view name
* Use VetMapService - Add two or more vets
* Create Model Implementation
* Verify value List value is added to Model Object (HashMap)


# 63. Tagging and Filtering JUnit Tests

```groovy
@Tag("tagName")
class a {
  
}
```

* Test-Ausführung auf Tags einschränken (intellij)
  * unit-tests
  * integration-tests
  * message-queue-tests
  * ...


# 64. JUnit Nested Tests

```groovy
@DisplayName("prefix wenn auf Klassenebene genutzt")

//...

@Nested
class NestedClass {
  
  // tests
  
  @Nested
  class NestedNexLevel {
    // tests
  }
}


```

# 65. JUnit Test Interfaces

Testklassen können Interfaces implementieren


# 68. JUnit Test Dependency Injection

* TestInfo -> test name, method, class, tags
* RepetitionInfo -> test repetition
* TestReporter -> publish runtime info for test reporting

```groovy
@RepeatedTest(5)
void myRepeatedTestWithDi(TestInfo testInfo, RepitionInfo repititionInfo) {
  System.out.println(testInfo.getDisplayName() + ":" + repititionInfo.getCurrentRepition());
}
```

# 69. Assignment - JUnit Dependency Injection

* Create new Repeated Test PersonTest
* Customize DisplayName of repeated test using palceholders
* Create default BeforeEachMethod to output test information (name, iteration)
  * Use Interface!

# 70. Assignment Review

* RepetitionInfo only available on RepetionTests

```groovy

    @BeforeEach
    default void beforeEachConsoleOutputer(TestInfo ti, RepetitionInfo ri) {
        System.out.println("Running Test - " + ti.getDisplayName() + " - "
        + ri.getCurrentRepetition() + " | " + ri.getTotalRepetitions());
    }

    @RepeatedTest(value = 5, name = "{displayName} | {currentRepetition} - {totalRepetitions}")
    @DisplayName("My Assignment Repeated Test")
    void myAssignmentRepeated() {
      // todo impl
    }
```


# 71. JUnit Parameterized Tests - Value Source

```xml
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-params</artifactId>
            <version>${junit-platform.version}</version>
            <scope>test</scope>
        </dependency>
```

```groovy
    @ParameterizedTest
    @ValueSource(strings = {"Spring", "Framework", "Guru"})
    void testValueSource(String val) {
        System.out.println(val);
    }
```

#72. JUnit Parameterized Test - Display Name

```groovy

    @DisplayName("Value Source Test ")
    @ParameterizedTest(name = "{displayName} [{index}] - {arguments}")
    @ValueSource(strings = {"Spring", "Framework", "Guru"})
    void testValueSource(String val) {
        System.out.println(val);
    }

```





