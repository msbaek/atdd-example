package com.example.employee

import spock.lang.Specification

/**
 * https://blog.jetbrains.com/idea/2021/01/tutorial-spock-part-1-getting-started/
 */
class EmployeeControllerSpec extends Specification {
    def "should be a simple assertion"() {
        expect:
        1 == 0
    }
}
