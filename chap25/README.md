# 25장 스프링 트랜잭션 기능 사용하기

> * 트랜잭션은 언제나 중요하다..👍
> * 예제 프로젝트: 
>   * [pro25](pro25)



## 25.1 트랜젝션 기능

* ...

* 트랜젝션은 커넥션 단위이기 때문에 DataSource로 트랜젝션 메니저를 생성해주면 됨.

  * https://mybatis.org/spring/ko/transactions.html

    

## 25.2 은행 계좌 이채를 통한 트랜잭션 기능

* ...

  

## 25.3 스프링의 트랜잭션 속성 알아보기

* ...



## 25.4 스프링 트랜잭션 기능 적용해 계좌 이체 실습하기

* ...

* HSQLDB로 먼저 해보고... 동작의 차이가 있어 확인이 필요하다면 Oracle 18c에 적용해보자.

  * 진행을 다해보니. 단순 롤백 테스트여서...  Oracle 까지 확인할 필요는 없을 것 같다.

    

* 트랜젝션 설정은 서비스 컨텍스트 설정에 넣었음.

  ```xml
   <tx:annotation-driven/>
  
    <bean id="transactionManager"
      class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
      <constructor-arg name="dataSource" ref="dataSource"/>
    </bean>
  ```

  * 트랜젝션 메니저 이름을 transactionManager로 사용하면 ` <tx:annotation-driven/>`에서 이름을 생략할 수 있음.



---

## 진행

* ...
  



## 의견

* 진짜 기본적인 트랜젝션 동작만 확인했다. 😅
* account 패키지 이하는 그냥 너무 단순해서 테스트 코드는 굳이 추가하진 말자..😅😅😅
* 그런데 지금 작성한 예제에서 `Controller <-> Service <-> Mapper` 형식으로 쓰고 있는데... Repository를 생략한 것을 좋지 않은 패턴으로 보는 것 같기도한데...
  * 이런 상태에서는 굳이   `Controller <-> Service <-> Repository <-> Mapper` 로 쓸필요 없을 것 같은데.. 어차피 `Repository <-> Mapper`  사이는 추가 로직없이  단순이 바이패스만 하게될 것 같아서... 진짜 의미가 없어보이긴하는데... MyBatis를 걷어낼때를 고려하는건가?
  * 그런데 Spring @Repository 어노테이션이 예외가 나면 뭔가 더 해주는게 있다고 들은게 있긴한데.. 생각이 안난다. 😓




## 정오표

* ...



## 기타

### `@BeforeTransaction`, `@AfterTransaction`은 언제 사용할까?

**`@BeforeTransaction`**

> Spring의 `@Transactional` 어노테이션을 통해 트랜잭션 내에서 실행되도록 구성된 테스트 메서드에 대해 트랜잭션이 시작되기 전에 어노테이션이 달린 void 메서드가 실행되어야 함을 나타내는 테스트 어노테이션입니다.
> 수퍼클래스 또는 인터페이스 기본 메서드로 선언된 `@BeforeTransaction` 메서드는 현재 테스트 클래스의 메서드보다 먼저 실행됩니다.
> 이 어노테이션은 메타 어노테이션으로 사용되어 사용자 정의 구성된 어노테이션을 생성할 수 있습니다.
> Spring 프레임워크 4.3부터 @BeforeTransaction은 Java 8 기반 인터페이스 기본 메서드에서도 선언할 수 있습니다.

**`@AfterTransaction`**

> Spring의 `@Transactional` 어노테이션을 통해 트랜잭션 내에서 실행되도록 구성된 테스트 메서드에 대해 트랜잭션이 종료된 후 어노테이션된 void 메서드가 실행되어야 함을 나타내는 테스트 어노테이션입니다.
> 수퍼클래스 또는 인터페이스 기본 메서드로 선언된 `@AfterTransaction` 메서드는 현재 테스트 클래스의 메서드 다음에 실행됩니다.
> 이 어노테이션은 메타 어노테이션으로 사용되어 사용자 정의 구성된 어노테이션을 생성할 수 있습니다.
> Spring 프레임워크 4.3부터 @AfterTransaction은 Java 8 기반 인터페이스 기본 메서드에서도 선언할 수 있습니다.

전에 AutoIncrement 값 초기화할려고 쓰려다 말았는데... 아직 꼭 필요한 부분이 있을지는 잘 모르겠음.
