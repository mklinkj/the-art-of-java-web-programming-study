# 24장 스프링과 마이바티스 연동하기

> * `mybatis-spring`을 사용하면 되는데... Mapper인터페이스를 쓸지? SqlSession을 주입받아 쓸지? 
>   * 그래도 Mapper 인터페이스 사용하는 것이 편하긴함..
> * 예제 프로젝트: 
>   * [pro24](pro24)



## 24.1 스프링-마이바이트 연동 관련 XML 파일 설정하기

* ...

* 디펜던시

  ```groovy
    implementation "org.mybatis:mybatis:${mybatisVersion}"
    implementation "org.mybatis:mybatis-spring:${mybatisSpringVersion}" // 추가
  ```



* 나는 SqlSession을 빈으로 만들지 않고 MapperScan으로 매퍼 생성

  * action-mybatis.xml

    ```xml
    <mybatis:scan base-package="org.mklinkj.taojwp.member.dao"/>
    ```

  * MemberDAO 인터페이스
  
    ```java
    @Mapper
    public interface MemberDAO {
      // ...
    ```
  
    * MemberDAOImpl 클래스는 필요가 없어져 제거함.



## 24.2 마이바티스 관련 XML 파일 설정하기

* ...

* 나는 mybatis-config.xml의 내용이 필요가 없어져서 제거했다.

  * typeAlias는 action-mybatis.xml에 직접 설정함.

    ```xml
      <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <property name="mapperLocations" value="classpath:mappers/*.xml"/>
        <property name="typeAliases">
          <list>
            <value>org.mklinkj.taojwp.member.domain.MemberVO</value>
            <value>org.mklinkj.taojwp.member.dto.SearchDTO</value>
            <value>org.mklinkj.taojwp.member.dto.SearchType</value>
          </list>
        </property>
      </bean>
    ```

    * 별칭에 대한 값은...  각 클래스에 `@Alias`어노테이션으로 설정

      ```java
      @Alias("searchDTO")
      // ...
      public class SearchDTO {
        // ...
      }
      ```

      이렇게 설정해서 typeAlias 설정만 남은 mybatis-config.xml 설정파일이 필요가 없어졌음.

      

      

      

      



---

## 진행

* 이번 장은 별로 할것이 없었음. `mybatis-spring` 을 적용하고나니 더 이상 필요없는 클래스들이나 설정파일을 지울 수 있었음.
  * MemberDAOImpl
    * 설정을 통해 MemberDAO 인터페이스를 메퍼 인터페이스로 직접 사용해서 구현체 클래스는 필요없어짐.

  * mybatis-config.xml
    * DataSource 설정과 typeAlias 설정들이 action-mybatis.xml로 옮겨져서, 설정파일이 따로 필요가 없어졌다.

* 이전 장에 순수 서블릿으로 되어있던 부분 MultiActionController로 변경하셨는데.. 나는 이미 어노테이션 컨트롤러로 쓰고 있어서 컨트롤러 수정할 부분은 없었다.



## 의견

* DAO의 한 메서드 내에서 여러 쿼리들을 조합해야할 경우라면 SqlSession을 주입받아 써야할 경우도 있을 것 같긴한데.. 그러면 그냥 서비스 단에서 여러 매퍼 메서드 호출하는게 나은건 아닌지?
  * 하여튼 필요할 때가 있을 것 같긴한데.. 아직은 잘 모르겠다.. 😅 그래도 확실히 매퍼 인터페이스로 사용하는것이 편함..  👍





## 정오표

* ...




## 기타

* SqlSessionFactoryBean

  * https://mybatis.org/spring/ko/factorybean.html

* SqlSession 사용

  * https://mybatis.org/spring/ko/sqlsession.html

* 매퍼 주입

  * https://mybatis.org/spring/ko/mappers.html

  
