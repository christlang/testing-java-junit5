


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

```shell
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