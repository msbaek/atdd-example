package com.example.employee

import spock.lang.Specification

/**
 * https://blog.jetbrains.com/idea/2021/01/tutorial-spock-part-1-getting-started/
 */
class SpockFeaturesSpec extends Specification {
    def "should be a simple assertion"() {
        expect:
        1 == 1
    }
    def "should demonstrate given-when-then"() {
        given:
        int i = 1
        int j = 2
        int sum = 0

        when:
        sum = i + j

        then:
        sum == i + j
    }

    def "should expect exceptions"() {
        when:
        throw new IllegalAccessException();

        then:
        thrown(IllegalAccessException)
    }
}
