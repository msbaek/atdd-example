package com.example.employee

import groovyx.net.http.RESTClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

/**
 * https://blog.j-labs.pl/2019/05/Test-your-REST-API-with-Spock-Introduction-to-Spock-Framework
 */

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
class EmployeeControllerSpockHttpBuilderIntegrationTest extends Specification {
    @Autowired
    private EmployeeRepository repository;

    @Shared
    def client = new RESTClient("http://localhost:8080")

    def setup() {
        repository.deleteAll()
        repository.save(new Employee("Baek", "Myeongseok"))
    }

    @Unroll
    def "should return default message when last name not found"() {
        when: 'greet with non existing name'
        String nonExistingLastName = "nonExistingLastName";
        String expectedMessage = "Who is this " + nonExistingLastName + " you're talking about?";
        def response = client.get(path: '/api/hello/', query: [lastName : nonExistingLastName])

        then: 'server returns 200 OK and default message'
        assert response.status == 200: 'response should be 200'
        assert response.data.message.equals(expectedMessage)
        println("\n${response.data}\n")

        where:
        lastNameVal             | messageVal
        'nonExistingLastName'   | 'Who is this nonExistingLastName you\'re talking about?'
        'Baek'                  | 'Hello Myeongseok Baek!'
    }
}