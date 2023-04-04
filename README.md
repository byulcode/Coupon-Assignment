# Coupon-Assignment
사이드프로젝트 스터디 4주차 과제 - JPA와 스프링부트로 coupon crud 구현하기

### Tech Stack
- Spring Boot 2.7.10
- Java 11
- Spring Data JPA

<details>
<summary>새로 알게된 부분</summary>
<div markdown="1">

#### 1. JPA Auditing
Spring Data JPA에서 시간에 대해서 자동으로 값을 넣어 주는 기능. aduit을 자동으로 생성 시간, 업데이트 시간을 매핑하여 데이터베이스 테이블에 넣을 수 있다.</br>
사용 방법 : </br>
1. Main application에 `@EnableJpaAuditing` 추가
2. 생성일자와 수정일자를 자동생성할 BaseTimeEntity 생성 </br>
   https://github.com/byulcode/Coupon-Assignment/blob/71b6611ebea6626b263211355efde63d0e004a1c/src/main/java/study/coupon/domain/BaseTimeEntity.java#L12-L22
+ @MappedSuperclass : 객체의 입장에서 공통 매핑 정보가 필요할 때 사용
+ @EntityListeners : 엔티티를 db에 적용하기 이전, 이후에 어떤 이떠한 작업을 하기 위한 이벤트 처리 어노테이션
  </br></br>

#### 2. setter 사용을 지양해야 하는 이유는?
+ 공개 set 메서드는 도메인 로직을 도메인 객체가 아닌 응용 영역(service)이나 표현 영역(controller)로 분산시킨다. -> 코드의 응집도가 낮아진다
+ 실행하면 문제가 되는 메서드들의 실행 권한을 주게 되는 경우가 발생할 수 있다.
+ 외부에서 필드값을 변경하기 위해 접근할 때 공개 set 메서드 사용시 목적을 제대로 파악하기 힘들다.  setter 사용 대신 그 목적을 잘 표현하는 메서드를 제공하라.
  </br></br>
#### 3. DTO vs Entity
- DTO : 계층간 데이터를 전달하기 위한 객체. 클라이언트에서 사용하는 것이므로 노출되어도 상관없다.
- Entity : 데이터베이스의 테이블과 스키마를 표현하는 역할. DB컬럼과 연결되기때문에 필드명이 노출되서는 안된다.

#### 4. Lombok을 사용하는 이유?
Lombok이란 어노테이션 기반으로 코드를 자동완성 해주는 라이브러리다. 롬복을 사용하면 코드 자동 생성을 통한 생산성 향상, 중복되는 코드 제거 등의 장점이 있다.</br>
하지만 많은 기능을 제공하는 만큼 주의해서 사용해야 한다.
</br></br>
#### 5. @AllArgsConstructor 사용을 지양하는 이유는?
인스턴스 멤버의 순서가 생성자의 파라미터 순서와 동일하다. 다음과 같은 클래스가 있다고 하자.

```java
@AllArgsConstructor
public class Person {
   private String lastName;
   private String firstName;
}
   Person person = new Person("김", "별이");
```
만약 누군가 `firstName`이 성이라고 착각하고 인스턴스 멤버의 순서를 바꾼다면, 성과 이름의 순서가 바뀌어 저장되게 된다.
</br></br>

#### 6. 정적 팩토리 메서드(Static Factory Method)란?
정적 팩토리 메서드는 객체의 생성을 담당하는 클래스 메서드다. 객체 생성을 생성자(new)가 아닌 정적(static) 메서드로 하는 것을 말한다. 정적 팩토리 메서드 사용시 다음과 같은 이점이 있다.
+ 이름을 가질 수 있다(가독성을 높일 수 있다).
+ 사용하는 구현체를 숨길 수 있다.
+ 호출될 때 마다 인스턴스를 새로 생성하지 않아도 된다.</br>
  </br>
  내가 사용한 부분 :
  https://github.com/byulcode/Coupon-Assignment/blob/71b6611ebea6626b263211355efde63d0e004a1c/src/main/java/study/coupon/domain/dto/ResponseDto.java#L35-L48
>💡정적 팩토리 메서드 생성시 네이밍 컨벤션을 따르도록 하자!

</br></br>
#### Reference
+ https://kwonnam.pe.kr/wiki/java/lombok/pitfall
+ https://velog.io/@backfox/setter-%EC%93%B0%EC%A7%80-%EB%A7%90%EB%9D%BC%EA%B3%A0%EB%A7%8C-%ED%95%98%EA%B3%A0-%EA%B0%80%EB%B2%84%EB%A6%AC%EB%A9%B4-%EC%96%B4%EB%96%A1%ED%95%B4%EC%9A%94
+ https://velog.io/@cjh8746/%EC%A0%95%EC%A0%81-%ED%8C%A9%ED%86%A0%EB%A6%AC-%EB%A9%94%EC%84%9C%EB%93%9CStatic-Factory-Method

</div>
</details>

<details>
<summary>리팩토링</summary>
<div markdown="1">

### domain

- `@DynamicUpdate` : 실제 값이 변경된 컬럼으로만 update 쿼리를 만드는 어노테이션
- datetime vs timestamp
  - datetime : YYYY-MM-DD hh:mm:ss 형식. timezone 적용 안됨.
  - timestamp : YYYY-MM-DD hh:mm:ss 형식. timzone 적용됨. 기본적으로 not null
- *`NoArgsConstructor(AccessLevel.PROTECTED)` : 무분별한 객체 생성을 체크할 수 있는 수단. 기본 생성자의 생성을 방지하고 지정한 생성자를 사용하도록 강제하여 완전한 상태의 객체를 생성하도록 도움을 줌.*
- [Builder Pattern을 사용해야 하는 이유](https://mangkyu.tistory.com/163)
  - 필요한 데이터만 설정할 수 있음
  - 인자의 순서와 상관 없이 객체를 생성할 수 있어 유연성을 확보할 수 있음
  - 적절한 책임을 이름에 부여하여 가독성을 높일 수 있음
  - 변경 가능성을 최소화할 수 있음

  ⇒ 객체를 생성할 때 builder 패턴을 사용하자


### Controller

- `@RequiredArgsConstructor` : final 혹은 @NutNull이 붙은 필드의 생성자를 자동으로 만들어 준다. 하지만 자동적으로 생성자가 만들어지기 때문에 사용에 유의!
- `ResponseEntity` : HttpEntity 클래스를 상속받으며 HTTP 상태 코드를 직접 제어할 수 있고, 사용자의 HttpRequest에 대한 응답 데이터를 포함하는 클래스다. HTTP 아키텍처 형태에 맞는 Response를 보내 준다. [사용법](https://www.baeldung.com/spring-response-entity)
  - 결과값, 상태코드, 헤더값을 프론트에 넘겨줄 수 있고, 에러코드를 섬세하게 설정해서 보내줄 수 있다.
  - Raw 타입으론 사용하지 말것!  [참고](https://100100e.tistory.com/481)
    - Raw 타입 : 제네릭 타입에서 타입 매개변수를 전혀 사용하지 않을 때
    - Unbounded wildcard 타입 : 제네릭을 사용하고 싶지만 실제 타입 매개변수가 무엇인지 신경쓰고 싶지 않을 때 `<?>`을 사용하면 된다.

</div>
</details>