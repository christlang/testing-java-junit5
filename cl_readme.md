


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

# 72. JUnit Parameterized Test - Display Name

```groovy

    @DisplayName("Value Source Test ")
    @ParameterizedTest(name = "{displayName} [{index}] - {arguments}")
    @ValueSource(strings = {"Spring", "Framework", "Guru"})
    void testValueSource(String val) {
        System.out.println(val);
    }

```

# 73. JUnit Parameterized Test - ENUM Source

```groovy

    @DisplayName("Enum Source Test")
    @ParameterizedTest(name = "{displayName} [{index}] - {arguments}")
    @EnumSource(OwnerType.class)
    void enumTest(OwnerType ownerType) {
        System.out.println(ownerType);
    }

```

# 74. JUnit Parameterized Test - CSV Source

```groovy
    @DisplayName("CSV Input Test")
    @ParameterizedTest(name = "{displayName} [{index}] - {arguments}")
    @CsvSource({
            "FL, 1, 1",
            "OH, 2, 2",
            "MI, 1, 1"
    })
    void csvInputTest(String stateName, int val1, int val2) {
        System.out.println(stateName + " = " + val1 + ":" + val2);
    }
```


# 75. JUnit Parameterized Test - CSV File Source


```groovy
    @DisplayName("CSV From File Test")
    @ParameterizedTest(name = "{displayName} [{index}] - {arguments}")
    @CsvFileSource(resources = "/input.csv", numLinesToSkip = 1)
    void csvFromFileTest(String stateName, int val1, int val2) {
        System.out.println(stateName + " = " + val1 + ":" + val2);
    }
```

# 76. JUnit Parameterized Test - Method Provider

```groovy

    @DisplayName("Method Provider Test")
    @ParameterizedTest(name = "{displayName} [{index}] - {arguments}")
    @MethodSource("getArgs")
    void fromMehodTest(String stateName, int val1, int val2) {
        System.out.println(stateName + " = " + val1 + ":" + val2);
    }

    static Stream<Arguments> getArgs() {
        return Stream.of(
                Arguments.of("FL", 5, 1),
                Arguments.of("OH", 2, 8),
                Arguments.of("MI", 3, 5));
    }

```


# 77. JUnit Parameterized Test - Custom Provider


```groovy
public class CustomArgsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of("FL", 7, 1),
                Arguments.of("OH", 11, 8),
                Arguments.of("MI", 4, 5));
    }
}

@DisplayName("Custom Provider Test")
@ParameterizedTest(name = "{displayName} [{index}] - {arguments}")
@ArgumentsSource(CustomArgsProvider.class)
void fromCustomProviderTest(String stateName, int val1, int val2) {
  System.out.println(stateName + " = " + val1 + ":" + val2);
}

```


# 78. Unit Tests vs Integration Tests

* UnitTests -> Test
* IntegrationTests -> IT


# 79. JUnit Extensions


```java
import java.lang.reflect.Method;
import java.util.logging.Logger;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ExtensionContext.Store;

/**
 * https://junit.org/junit5/docs/current/user-guide/#extensions-lifecycle-callbacks-before-after-execution
 */
public class TimingExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private static final Logger logger = Logger.getLogger(TimingExtension.class.getName());

    private static final String START_TIME = "start time";

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        getStore(context).put(START_TIME, System.currentTimeMillis());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        Method testMethod = context.getRequiredTestMethod();
        long startTime = getStore(context).remove(START_TIME, long.class);
        long duration = System.currentTimeMillis() - startTime;

        logger.info(() ->
            String.format("Method [%s] took %s ms.", testMethod.getName(), duration));
    }

    private Store getStore(ExtensionContext context) {
        return context.getStore(Namespace.create(getClass(), context.getRequiredTestMethod()));
    }

}
```

```java
@ExtendWith(TimingExtension.class)
class TimingExtensionTests {

    @Test
    void sleep20ms() throws Exception {
        Thread.sleep(20);
    }

    @Test
    void sleep50ms() throws Exception {
        Thread.sleep(50);
    }

}
```

# 80. Conclusion

* Zusammenfassung