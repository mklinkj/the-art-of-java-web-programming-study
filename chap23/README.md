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
