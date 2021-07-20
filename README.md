# ATDD 기반 Web Application 개발
[TOC]

## ATDD 개요

![](https://api.monosnap.com/rpc/file/download?id=oTQD4t1MIjUpvl64XlH4BwM37FHCFy)

### Levels of Tests
- Acceptance: Does the whole system work?
	- same as: “functional tests,” “customer tests,” “system tests.”, "UI tests"
	- 진척도 측정
- Integration: Does our code work against code we can't change?
	- spring configuration works ?
- Unit: Do our objects do the right thing, are they convenient to work with?  
	- iterative discovery of interfaces, design with working code(refactoring)

### ATDD란 ?
https://gaboesquivel.com/blog/2014/differences-between-tdd-atdd-and-bdd/
- 구현 전에 사용자, 테스터 및 개발자가 인수 조건(Acceptance Criteria)을 정의하는 협업 실천법
- 모든 프로젝트 구성원이 수행해야 할 작업과 요구 사항을 정확히 이해할 수 있도록 도와줌
- 요구 사항이 만족되지 않으면 테스트가 실패하여 빠른 피드백 제공
- 테스트는 비즈니스 도메인 용어로 기술됨.
- 각 기능은 반드시 실질적이고 측정 가능한 비즈니스 가치를 제공해야합니다.
- 인수 테스트(Acceptance Test)에 요구사항을 명시하는데 촛점. 인수 테스트로 개발을 주도(drive)

https://reqtest.com/testing-blog/tdd-and-atdd-an-overview-of-the-two-popular-methods-2/
- 구현 전에 관련된 모든 사람들(테스터, 개발자 및 사용자)이 한 팀으로써 개발 초기 단계에 시스템이 충족해야하는 인수 조건을 정의하도록하는 공동 작업 방식

https://www.agilealliance.org/glossary/atdd/
- ATDD는 해당 기능을 구현하기 전에 서로 다른 시각을 가진 구성원들(고객, 개발, 테스트)이 인수 테스트를 작성하는 작업 방식

### TDD란 ?
- 고품질의 SW를 작성하는 가장 빠른 방법
	- Kent Beck. punch card / print out.
- 작고, 빠른 피드백
- 첫번째 고객. 메뉴얼. 

#### The Three Laws of TDD
1. Write NO production code except to pass a failing test.
2. Write only ENOUGH of a test to demonstrate a failure
3. Write only ENOUGH production code to pass the test

#### TDD 절차

![](https://api.monosnap.com/rpc/file/download?id=FE2EqCQWrrFYfaoWz8cj6CDbZ9MHZE)

refactoring: mandatory not optional
- 시간이 없다 ? 별도의 일정 ?
- 화장실 다녀오면서 손 씻을 시간을 별로도 잡나 ???

### Test First vs After
![](https://api.monosnap.com/rpc/file/download?id=rHs9009YOGood4JvQ0dX1DP6H7YDrb)

- Test After도 나쁘지 않지만 그건 TDD(Design)이 아니라 Coverage가 일부 확보된 것

### The Bigger Picture

[Growing Object-Oriented Software, Guided by Tests](https://www.amazon.com/Growing-Object-Oriented-Software-Guided-Tests/dp/0321503627)

![](https://api.monosnap.com/rpc/file/download?id=ZtdjZoPmCQ0EIkYwVn5wSJnohmE9Mc)

- 특정 클래스에 대한 단위 테스트를 작성함으로써 TDD를 시작하고 싶은 욕구 발생
	- 테스트를 전혀 작성하지 않는 것보다는 좋겠지만 단위 테스트만 있는 프로젝트는 TDD 프로세스의 아주 중요한 혜택을 놓치게 됨
	- 단위 테스트가 잘 작성된 고품질의 코드들이
		- 어디서도 호출되지 않거나 
		- 시스템의 다른 부분과 통합할 수 없거나
		- 재작성해야만 하는
	- 경우가 존재한다(bottom-up의 폐해).
- 어디서 코딩을 시작하고, 언제 코딩을 종료할지를 어떻게 알 수 있을까 ?(top-down)
- 특정 기능(Feature)를 구현할 때 우리가 구현하려는 기능을 보여주는 Acceptance Test를 작성함으로써 시작
	- 이 테스트가 실패하는 동안은 시스템이 이 기능을 아직 구현하지 못했다는 것을 보여준다. 그리고 테스트가 성공하면 우린 완료한 것이다.

### 인수 테스트 vs 단위 테스트

|  **인수테스트** | **단위테스트** |
| :-------  | :------ |
| 인수테스트 작성으로 기능 구현을 시작 | 객체나 소수의 객체 집합을 격리해서 다룸 |
| 시스템이 전체적으로 잘 동작하는지 알려줌 | 클래스 설계를 돕고, 동작한다는 확신을 갖게 하는 점에서 중요 |
| 진척도 측정을 위한 테스트 | 회귀 테스트 |
| 어디서 시작하고 언제 멈출지 | 빠르게 동작하도록 하고 설계(Refactoring) | 


가장 간단한 성공 케이스로 테스트를 시작
- degenerate or failure case로 시작하는 것은 쉽다
- Degenerate cases는 시스템의 가치에 많은 것을 추가하지 않고, 또한 우리의 생각이 유효한지에 대해 충분한 피드백틀 주지 않는다.
- 당신이 읽고 싶은 테스트를 작성하라(Unit vs. Acceptance Test)

## Example

### 0. 요구 사항

사용자(Employee)는 lastName을 인자로 인사말(greeting)을 요구한다. 
시스템은 lastName으로 DB에서 Employee를 찾고 
- 존재하는 경우 "Hello ***firstName*** ***lastName*** !"을
- 존재하지 않는 경우 "Who is this ***lastName*** you're talking about?"을

반환한다.
		
### 1. Create Project

![](https://api.monosnap.com/rpc/file/download?id=fHHuT4HGiCFmMZtM1S9DaTWOz53VXj)

```xml
<dependency>
    <groupId>io.rest-assured</groupId>
    <artifactId>rest-assured</artifactId>
    <version>3.0.7</version>
    <scope>test</scope>
</dependency>
```

추가

### 2. Acceptance Test

#### 2.1 rest-assured를 이용한 Acceptance Test
```java
package com.example.employee;

import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = EmployeeApplication.class
)
@TestPropertySource(
        locations = "classpath:application-integration.properties"
)
public class EmployeeControllerRestAssuredIntegrationTest {
    @Autowired
    private EmployeeRepository repository;

    private RequestSpecification basicRequest;

    @Before
    public void setUp() {
        basicRequest = given()
                .baseUri("http://localhost")
                .port(8080)
        ;

        repository.deleteAll();
        repository.save(new Employee("Baek", "Myeongseok"));
    }

    @Test
    public void shouldReturnDefaultMessageWhenLastNameNotFound() {
        String nonExistingLastName = "nonExistingLastName";
        String expectedMessage = "Who is this " + nonExistingLastName + " you're talking about?";

        given().spec(basicRequest).basePath("/api/hello/" + nonExistingLastName)
                .when().get()
                .then().log().body()
                .statusCode(HttpStatus.OK.value())
                .body(is(expectedMessage));
    }

    @Test
    public void shouldReturnGreetingMessageWhenLastNameFound() {
        String existingLastName = "Baek";
        String expectedMessage = "Hello Myeongseok Baek!";

        given().spec(basicRequest).basePath("/api/hello/" + existingLastName)
                .when().get()
                .then().log().body()
                .statusCode(HttpStatus.OK.value())
                .body(is(expectedMessage));
    }
}
```

#### 2.2 Make it Works

- add src/test/resources/application-integration.properties

```language
spring.datasource.url = jdbc:h2:mem:test
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.H2Dialect
```

- add jpa annotation to Employee, EmployeeRepository
- add EmployeeController

![](https://api.monosnap.com/rpc/file/download?id=KYjR5ccJMafoSlKHVw3agkLiSSJohP)

```java
@RestController
@RequestMapping("/api")
public class EmployeeController {
    @GetMapping("/hello/{lastName}")
    public String greeting(@PathVariable String lastName) {
        return "Who is this " + lastName + " you're talking about?";
    }
}
```

### 3. Implement Service Logic using TDD

controller에서 하드 코딩된 결과를 반환하도록 하여 하나의 테스트는 성공시켰지만 2가지 경우를 성공시키진 못했다. 제대로 하려면 로직이 필요하다.

controller는
- 사용자의 요청 해석(spring-mvc의 경우는 annotation으로 가능)
- controller 수준의 validation
- service로 위임
- 다음 페이지를 결정하고 service에서 반환받은 값을 전달하여 view를 출력

하는 책임을 갖는다([POJOs in Action](https://www.manning.com/books/pojos-in-action)). 

따라서 우리는 service 객체가 필요하다.

![](https://api.monosnap.com/rpc/file/download?id=4RwtT63KcBx0Kn9CJB29ZthmzjGnAV)

```java
@Service
public class GreetingService {
    public String greet(String lastName) {
        return null;
    }
}
```

#### 3.1 Create Unit Test for greet method

로직을 구현할 서비스 객체와 메소드를 발견했으므로 이제부터 TDD로 하나의 메소드를 완성한다.

```java
public class GreetingServiceTest {
    private GreetingService greetService;

    @Before
    public void setUp() throws Exception {
        greetService = new GreetingService();
    }

    @Test
    public void greet_with_nonExisting_last_name_should_return_default_message() {
        String nonExistingLastName = "nonExistingLastName";
        String msg = greetService.greet(nonExistingLastName);
        assertThat(msg, is("Who is this " + nonExistingLastName + " you're talking about?"));
    }
}
```

이게 맞으나 이렇게 하면 작은 단위로 빠르게 확인하며 구현하기 어렵다.
왜냐하면 이 테스트를 성공시키기 위해서 service 메소드를 구현해야 하는데 빠르게 한 줄 단위로 실행시키고 확인해 보면서 구현하기 어렵다.

#### 3.2 Implement method in Test

**테스트에 직접 서비스 메소드를 구현**한다.

![](https://api.monosnap.com/rpc/file/download?id=YyVnkaexWHrDjCqtbXMQncVdYCpN56)

이때 실패하는지도 인자를 바꿔서 확인해서 우리의 테스트가 성공하는 경우와 실패하는 경우 모두를 검증하는지 확인

##### 3.2.1 Prepare Refactoring

- extract fields
- move up given code to setUp
- declare variables(lastName, employee1, msg1)

![](https://api.monosnap.com/rpc/file/download?id=WuBIcbFa7v6ydqIrW2VSdkczGpmGMr)

- extract method to move

![](https://api.monosnap.com/rpc/file/download?id=w1EQ6TJitin7OpHTpmyXshkkqRTMQX)

##### 3.2.2 Move greet method to Service

```java
@Service
public class GreetingService {
    EmployeeRepository repository;

    public String greet(String lastName) {
        Optional<Employee> employee = repository.findByLastName(lastName);
        return employee
                .map(e -> String.format("Hello %s %s!", e.getFirstName(), e.getLastName()))
                .orElse("Who is this " + lastName + " you're talking about?");
    }
}
```

move후에 change signature로 test에 대한 의존성 제거

##### 3.2.3 Clean Test Code

이제 우리의 테스트 코드는 comment out한 초기 의도를 나타낸 테스트 코드와 같아졌다.
comment를 삭제하고 , 테스트 명에 맞게 메소드를 분리한다.

![](https://api.monosnap.com/rpc/file/download?id=pyxD4Zj5aV3f19XlzSvyCneZA69TNB)

### 4. Making Integration test to use Real Objects

controller integration test가 real object로 동작하도록 수정

![](https://api.monosnap.com/rpc/file/download?id=ljNUD2t3s4Btaq4rpSp3XpM5xtx51n)

controller integration test가 제대로된 E2E 테스트(Acceptance)가 되었고, 우리의 작업은 완료되었다.

## Appedix

### TDD가 느린가 ?

바로 테스트 코드에서 한줄 단위로 작성/실행/확인하는데...

### Why rest-assured ?

spring mock mvc도 훌륭. 원격의 CI서버에서 원격의 검증 서버에 대해서 Integration Test를 해야 하는 필요로 rest-assured 선택

### Annotations

**@SpringBootTest**
- 통합 테스트, 전체 Bean 로딩됨

** @WebMvcTest**
- WebApplicationContext이 Bean 들이 로딩됨

** @DataJpaTest	**
- Repository 레스트를 위한 JPA 관련 Bean들이 로딩됨
- persistence layer 테스트를 위한 표준 설정 제공
	- H2 인메모리 DB, Hiberante, Spring Data, Datasource 등을 설정
	- @EntityScan 실행
	- SQL 로깅 설정

**@ExtendWith(SpringExtension.class)**
- spring boot test의 기능과 junit의 연결의 제공
- junit 테스트에서 spring boot test 기능이 필요할 때 이 어노테이션을 사용

**@AutoConfigureMockMvc**
- MockMvc Autowire 제공

**@TestPropertySource**
- 테스트에서 사용할 properties 파일의 위치를 설정
- application.properties에 정의된 설정을 오버라이드

**TestEntityManager**
- TestEntityManager provided by Spring Boot is an alternative to the standard JPA EntityManager that provides methods commonly used when writing tests
```java
@Autowired
private TestEntityManager entityManager;
```
