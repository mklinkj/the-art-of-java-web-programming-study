# 23장 마이바티스 프레임워크 사용하기

> * 이미 MyBatis를 미리 써오긴 했는데...
> * 다시 Spring 6 프로젝트로...
>   *  MultiActionController에서, 어노테이션 컨트롤러로 바꾸고나니 훨씬 편해졌다. 보기도 쉽고...
> * 예제 프로젝트: 
>   * [pro23](pro23)



## 23.1 마이바티스란?

* ...

## 23.2 마이바티스 설치하기

* ...

* Gradle에 디펜던시 추가하자..

  ```groovy
  implementation "org.mybatis:mybatis:${mybatisVersion}"
  // ...
  implementation "org.hsqldb:hsqldb:${hsqldbVersion}"
  ```

  * DB는 그냥 HSQLDB 메모리 모드로 사용.



## 23.3 마이바티스 이용해 회원 기능 실습하기

* ...

### 23.3.1 마이바티스 설정 파일 작성

* ...



### 23.3.2 마이바티스를 이용한 회원 정보 조회 실습

* 설정파일의 공식 명칭은: `mybatis-config.xml` 인 것 같다. 
  * https://mybatis.org/mybatis-3/ko/getting-started.html#xml%EC%97%90%EC%84%9C-sqlsessionfactory-%EB%B9%8C%EB%93%9C%ED%95%98%EA%B8%B0
  * `sql-map-config.xml`은 아마도 iBatis때 사용하던 이름인듯.. 지금도 호환이되긴하지만..

* Mapper 인터페이스로 바로 쓰면 편하긴 한데...

  ```java
    public MemberDAOImpl() {
      try {
        EncodedResource resource =
            new EncodedResource(new ClassPathResource("mybatis-config.xml"), StandardCharsets.UTF_8);
        this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource.getInputStream());
  
      } catch (Exception e) {
        throw new IllegalStateException("SqlSessionFactory 생성 실패", e);
      }
    }
  // ...
  @Override
    public List<MemberVO> selectAllMembers() {
      try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
        return sqlSession.getMapper(MemberDAO.class).selectAllMembers();
      }
    }
  ```

  * SqlSessionFactory를 빈설정으로 만들지 않고 있어서.. 설정이 좀 웃기게 되었다. 😅

    * 세션 팩토리를 얻은 다음에 메퍼 연동해서 실행하게 하는 좀 뭔가 이상한 ...😅

    * 책코드를 보니.. 이부분은 다시 서블릿을 쓰지 마시고, 22장에서 스프링 전환 코드 쓰셨으면 나았을 것 같은데...  😅

      



## 23.4 마이바티스 이용해 회원 정보 CRUD 실습하기

* ...

### 23.4.1 회원의 ID와 비밀번호 조회

* ...
* 내가 요즘 selectOne을 안쓰게 된 이유가... 매퍼 인터페이스 메서드에서 반환값이 그냥 단일 값이면 단일 리턴 타입쓰고, 아니면 List 같은 컬렉션을 써서 따로 구분을 할 필요가 없었던 것 같은데... 그러면서 잘 쓰게않게된 것 같다.

### 23.4.2 HashMap을 이용한 모든 회원 정보 조회

* ...
* 왠만하면 도메인 모델에 담아서 반환하는게 베스트 케이스라 결과값을 Map으로 담지않게 된 것 같다.
* 레거시 코드의 Map 반환 코드들을 도메인에 담아 바꾸는 작업도 종종해왔음.



### 23.4.3 조건 값으로 회원 정보 조회

* ...
* 난 별도 페이지 안만들고 회원정보 목록 페이지 위에다 추가해봤다.



### 23.4.4 회원정보추가

* ... 

* 이미 회원 추가 기능은 넣어놨는데... 추가로 MemberVO 도메인에 대한 Validataion만 해보자!

  ```groovy
  implementation "org.hibernate.validator:hibernate-validator:${hibernateValidatorVersion}"
  
  // implementation 'jakarta.validation:jakarta.validation-api:3.0.2'
  // jakarta.validation-api 의 경우는 hibernate-validator가 가져와서 선언 안해줘도 되는 듯..
  ```

* 서블릿 컨텍스트 설정에.. 아래 내용 추가

  ```xml
  <mvc:annotation-driven/>
  ```



### 23.4.5 HashMap을 이용한 회원 정보 추가

* ...
* 이 파트는 딱히 진행하지 않아도 될 것 같다.



### 23.4.6 회원 정보 수정

* ...

### 23.4.7 회원정보 삭제

* ...

* MyBatis 매퍼 XML의 쿼리문을 일부러 CDATA로 감쌀 필요는 없음.

  ```xml
  <![CDATA[
    쿼리문...
  ]]>
  ```

  * 쿼리문 안에 `<` 같은 문자가 포함될 때는 필요가 있지만,  쿼리에 그런게 없다면 굳이 추가할 필요없음.










---

## 진행

* ...



## 의견

* ...




## 정오표

* ...




## 기타

### MyBatis 설정 파일에서  properties 파일을 설정해서 값을 설정할 수도 있다.

```xml
<configuration>
  <properties resource="config/jdbc.properties" />
  ...
  <environments default="development">
    <environment id="development">
      <transactionManager type="JDBC"/>
      <dataSource type="POOLED">
        <property name="driver" value="${jdbc.driverClassName}"/>
        <property name="url" value="${jdbc.url}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
      </dataSource>
    </environment>
  </environments>
  ...
```

* xml 에 직접 고정 값을 적을 필요가 없었음.



### 한번 Submit 이 일어나고, 에러상태로 포워딩 상태에서는 reset버튼이 정상 동작하지 않는 것 처럼 보임?

```html
<form:button type="reset" class="btn btn-secondary">다시입력</form:button>
```

* 이거는 생각을 좀 해봐야겠음.. javascript로 제어를 해야되나?
* 이게 안되는게 아니고 일단 폼전송이 된거는 이전 정상 값이라고 인식해서 reset하면 그 값으로 돌아가기 때문에 그런 것이였음.. 
  * [ ] 이부분은 어떻게 해야할지.. 천천히 생각해보자...



### Hibernate Validator 를 테스트 환경에서 확인하기 위해서는  jakarta.el 이 필요하다.

```groovy
testRuntimeOnly "org.glassfish:jakarta.el:${jakartaElVersion}"
```

운영환경에서는 Tomcat 같은 WAS에 미리 준비되기 때문에 디펜던시를 따로 설정할 필요가 없지만, 테스트 환경은 그렇지 않으므로, 필요함..

* 그런데 tomcat에 대응되는 라이브러리가 정확히 뭔지 모르겠음... 찾으면 그거 넣을려고 했는데..
