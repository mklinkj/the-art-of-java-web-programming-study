# 19장 스프링 의존성 주입과 제어 역전 기능

> * 이미 18장에서 의존성, 제어 역전 관련해서는 미리 해봤는데... 혹시 추가로 해볼 내용이 있다면 진행해보자.
> * 예제 프로젝트: [pro19](pro19)

## 19.1 의존성 주입하기

* ...












---

## 진행

19장은 회원제 게시판을 완성하는 것으로 방향을 잡고 패키지 복잡하게 늘어진것 하나로 합치도록 하자.

#### ✨ TODO 목록

- [x] 프로젝트 패키지 정리, 기능별 컨트롤러 단일화

- [x] 접근 권한 제어 코드 추가

  * 로그인 / 로그아웃 기능 추가

  * 글보기는 누구나 가능, 로그인된 사용자만 글쓰기/수정/삭제 가능, 자신이 쓴 글만 수정/삭제 가능

  - 회원 목록 페이지 접근은 일단 제한 없이 둠 (관리자 권한 사용자를 나눠야 제한의 의미가 있는데, 이부분은 일단 두자.)

- [ ] Oracle 외의 다른 DB 대응

  * MySQL 대응, HSQLDB 대응이 되도 좋다.
  * DAO를 인터페이스로 전환하고 빈 설정만으로  DB를 전환해서 사용가능하게 코드를 변경한다.



>  회원, 게시물 테이블간 부모 - 자식 관계 외래키가 걸리게 되면 어느 특정 회원이 게시물을 작성한 상태라면, 그 게시물들을 완전히 제거하기 전까지는 회원을 삭제할 수 없다.
>
> 보통 삭제를 하더라도 회원의 상태 값만 바꾸고 정보는 잠시 유지했다가 일정 기간 지난 이후에 배치 작업으로 일괄 삭제하는 방식을 써야할 것 같은데...



#### OncePerRequestFilter를 사용해봤음.

```
OncePerRequestFilter는 Spring Framework에서 제공하는 추상 클래스로, 서블릿 필터의 한 종류입니다. 이 클래스의 이름에서 알 수 있듯이, 이 필터는 한 번의 요청에서 한 번만 실행됩니다. 이는 Filter 인터페이스를 직접 구현하는 필터와는 다릅니다. Filter 인터페이스를 구현하는 필터는 요청당 여러 번 실행될 수 있습니다.

OncePerRequestFilter의 장점은 요청당 한 번만 실행된다는 것입니다. 이는 필터가 여러 번 실행되어 성능이 저하되거나 예기치 않은 결과가 발생하는 것을 방지합니다. 또한 OncePerRequestFilter는 Spring Framework와 통합되어 있어서 Spring의 기능을 사용할 수 있습니다. 예를 들어, ApplicationContext에 액세스하거나 의존성 주입을 사용할 수 있습니다.
```



#### HikariConfig 에 클래스패스상의 프로퍼티 파일 경로를 직접 입력해서 인식 시킬 수 있다.

```xml
  <bean id="hikariConfig" class="com.zaxxer.hikari.HikariConfig">
    <constructor-arg value="/db-oracle.properties"/>
    <property name="dataSourceProperties">
      <props>
        <prop key="cachePrepStmts">true</prop>
        <prop key="prepStmtCacheSize">250</prop>
        <prop key="prepStmtCacheSqlLimit">2048</prop>
      </props>
    </property>
  </bean>
```





## 정오표

* ...
  



## 기타

* ...

  