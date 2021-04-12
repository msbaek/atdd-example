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

    class InvalidNumberException extends RuntimeException {
        final int number

        InvalidNumberException(int number) {
            this.number = number
        }
    }

    def "should expect an Exception to be thrown for even number"() {
        when:
        println "number in when ${number}\n"
        if(number % 2 == 0) {
            print "InvalidNumberException thrown for number = ${number}\n"
            throw new InvalidNumberException(number)
        }

        then:
        if(number % 2 == 1)
            true
        def exception = thrown(InvalidNumberException)
        exception.number == number
        print "exception occurred for number ${number}\n"

        where:
        number << [0, 1, 2, 3, 4]
    }

    def "should use data tables for calculating max"() {
        expect:
        Math.max(a, b) == max

        where:
        a | b || max
        1 | 3 || 3
        7 | 4 || 7
        0 | 0 || 0
    }
}