# 14장 표현 언어와 JSTL



> * ...
> * 예제 프로젝트: [pro14](pro14)



### 14.1 표현 언아란?

* ...

* 기존 표현식 보다 편리하게 값을 출력

  * 기존 표현식 `<%=id %>` ==> `${id}`

  * page 디렉티브 태그 속성인 `isELIgnored`를 `false`로 설정해야함. (`false`가 기본값)

    * IntelliJ의 JSP Directives 2_1.xsd에서는 isELIgnored의 기본 값이 true로 되어있다.

    * JSP 2.1에서는 아마도 이 설정이 false였나봄.. 그런데 지금 사용환경이 JSP 3.1이다.

      ```
      \${100}: ${100 + 10} 
      위의 수식이 isELIgnored가 true이면 
      \${100}: ${100 + 10} 와 같이 그대로 출력
      
      false이면
      ${100}: 110 로 출력된다.
      ```

    * 현재 환경에서는 딱히 명시하지 않아도 되니 해당 속성설정을 빼두자.

      

      




#### 14.1.1 표현 언어에서 사용되는 자료형과 연산자

* ...

  

#### 14.1.2 JSP에서 표현 언어 사용 실습

* ...



## 14.2 표현 언어 내장 객체(내장 변수)

* ...

### 14.2.1 표현 언어에서 제공하는 내장 객체의 종류와 기능

* ...

### 14.2.2 param 내장 객체 사용 실습

* ...

### 14.2.3 requestScope 사용 실습

* ...

### 14.2.4 pageContext 객체 사용 실습

* ...

### 14.2.5 빈 사용 실습

* ...

### 14.2.6 Collection 객체 사용 실습

* ...

### 14.2.7 HashMap 사용 실습

* ...

### 14.2.8 has-a 관계 빈 사용 실습

* ...



## 14.3 표현 언어로 바인딩 속성 출력하기

* ...

### 14.3.1 내장 객체 속성 값 출력 실습

* ...

### 14.3.2 스코프 우선순위

* ...

* #### page > request > session > application



## 14.4 커스텀 태그

* JSTL (JSP Standard Tag Library) 와 개발자가 만든 커스텀 태그



## 14.5 JSP 표준 태그 라이브러리 (JSTL)

* ...

* JSTL 1.2 구현을 받을 수 있긴한데... 

  * https://tomcat.apache.org/download-taglibs.cgi

* 최신 Jakarta Standard Tag Library 3.0 이 구현된 라이브러리를 포함시켜놨다.

  * https://jakarta.ee/specifications/tags/

  ```groovy
  implementation "jakarta.servlet.jsp.jstl:jakarta.servlet.jsp.jstl-api:${jakartaServletJspJstlApiVersion}"
  implementation "org.glassfish.web:jakarta.servlet.jsp.jstl:${jakartaServletJspJstlVersion}"
  ```

  * 최신 구현이라도 1.2 구현도 그대로 사용할 수 있었다. (1.2버전 tld파일도 포함되어있음.)
  * Impl(`glassfish 구현체`)과 Spec(`api`) 은 대응되는 것을 넣은 것 같고, EL과 Compat는 지금 시점에 따로 안넣어줘도 되는 것 같다.



## 14.6 Core 태그 라이브러리 사용하기

* ...

### 14.6.1 `<c:set>` 태그를 이용한 실습

* ...

### 14.6.2 `<c:remove>` 태그를 이용한 실습

* ...

### 14.6.3 `<c:if>` 태그를 이용한 실습

* ...

### 14.6.4 `<c:choose>` 태그를 이용한 실습

* ...

### 14.6.5 `<c:forEach>` 태그를 이용한 실습

* ...

### 14.6.6 `<c:url>` 태그를 이용한 실습

* ...

### 14.6.7 `<c:redirect>` 태그를 이용한 실습

* ...

### 14.6.8 `<c:out>` 태그를 이용한 실습

* ...
* 그냥 EL로 출력하면 이스케이프가 안됨 `<c:cout>` 으로 출력하면서  `escapeXml="true"` 이면 `<`, `>` 등이 이스케이프됨. 그런데 true가 기본값이다.



## 14.7 Core 태그 라이브러리 실습 예제

* ...

### 14.7.1 로그인 예제

* ...

### 14.7.2 학점 변환기 예제

* ...

### 14.7.3 구구단 출력 예제

* ...

### 14.7.4 이미지 출력 예제

* ...



## 14.8 다국어 태그 라이브러리 사용하기

* ...



## 14.9 한글을 아스키 코드로 변환하기

* 한글을 유니코드의 코드값으로 저장하는 것 같은데...

* Eclipse가 아니니 Properties Editor는 따로 설치할 수 없고, IntelliJ에서 확인해보자!!

* IntelliJ에서는 이미 해당 기능이 포함되어있다.

  * Java 9 부터.. UTF-8로 먼저 읽고 실패시 ISO-8859-1로 읽음 (Java 8 환경이라면 ISO-8859-1로 인코딩하고 한글 같은 것은 유니코드로 이스케이프해야함.)
  * 결국 아래 설정은 다시 UTF-8로 바꾸고 `명확한 Native에서 ASCII로 변환` 체크도 해제함..😅
  
  ![image-20230323012643802](doc-resources/image-20230323012643802.png)
  
* 내 환경의 경우는 그냥 `src/main/resouces`에 `messages` 폴더 하나 만들고 거기에 메시지 프로퍼티 파일을 모아둠.

  ![image-20230323112703822](doc-resources/image-20230323112703822.png)

* JSP에서는 아래와 같이 사용함.

  ```jsp
  <fmt:setLocale value="en_US" />
  <%--<fmt:setLocale value="ko_KR" />--%>
  <h1>
    회원정보<br><br>
    <fmt:bundle basename="messages.member" prefix="mem." >
      이름:<fmt:message key="name" /><br>
      주소:<fmt:message key="address" /><br>
      직업:<fmt:message key="job" />
    </fmt:bundle>
  </h1>
  ```

> ✨ 현재 Java 17 환경을 사용하므로 한글에대한 유니코드 이스케이프 필요없이 프로퍼티 파일에 한글도 그대로 입력하고  UTF-8 인코딩으로 저장하면 된다.
>
> * 관련 이슈 정리: https://github.com/mklinkj/the-art-of-java-web-programming-study/issues/6



## 14.10 포매팅 태그 라이브러리 사용하기

* ...

* 그런데 지금 JSTL 3.0에서는 LocalDate, LocalDateTime을 처리할 수 있는지? 알아봐야겠다..

  * 이전 것들이 안되어서, `https://github.com/sargue/java-time-jsptags` 이 라이브러리를 써오긴 했음.

  * IntelliJ에서 에디터 상에 오류가 표시되도 3.0 으로 tld 선언을 한뒤에 확인해보면 되겠다.

    ```jsp
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
    ```

    역시 확인해보니.. foramatDate는 Date만 가능하다.. 내부에서는 

    `org.apache.taglibs.standard.tag.rt.fmt.FormatDateTag`를 사용하는데 Date 입력만 받음. 😅

    formatDate에 대한 스팩이 바뀐것은 없나보다..

* ✨ `java-time-jsptags` 예제를 추가해봄 잘 동작한다.

  ```jsp
    <h2>java-time-jsptags 모듈 사용 예제</h2>
    <c:set var="localDateTime" value="<%=LocalDateTime.now() %>" />
    <javatime:format value="${localDateTime}" style="F-" /><br>
    <javatime:format value="${localDateTime}" style="S-" /><br>
    <javatime:format value="${localDateTime}" style="-M" /><br>
    <javatime:format value="${localDateTime}" style="FF" /><br>
    <javatime:format value="${localDateTime}" pattern="yyyy-MM-dd hh:mm:ss" /><br><br>
  
    <c:set var="zonedDateTime" value="<%=ZonedDateTime.now() %>" />
    한국 현재 시간:
    <javatime:format value="${zonedDateTime}" style="FF" /><br>
    뉴욕 현재 시간:
    <javatime:format value="${zonedDateTime}" style="FF" zoneId="America/New_York" /><br>
    <br><br>
  ```

  

## 14.11 문자열 처리 함수 사용하기

* ...



## 14.12 표현 언어와 JSTL을 이용한 회원 관리 실습

* ...

* 아래처럼 써보고 싶었는데... 힘들다..

  ```jsp
  <jsp:useBean  id="m" class="org.mklinkj.taojwp.sec02.ex01.MemberBean" />
  <jsp:setProperty name="m" property="*" />
  
  <%
    final MemberDAO memberDAO = new MemberDAO();
    Optional.ofNullable(m.getId()) //
        .filter(id -> !id.isBlank()) //
        .ifPresent(c ->
            memberDAO.addMember(m)
        );
    List<MemberBean> memberList = memberDAO.listMembers();
    request.setAttribute("memberList", memberList);
  %>
  ```

  람다식 내부에서 사용하는 외부의 m변수가 final이 아니여서 오류남..

  ```jsp
  <jsp:useBean  id="m" class="org.mklinkj.taojwp.sec02.ex01.MemberBean" />
  <jsp:setProperty name="m" property="*" />
  
  <%
    final MemberBean member = m;  // final 참조로 옮겨담아주면 되긴함. 🎃
    final MemberDAO memberDAO = new MemberDAO();
    Optional.ofNullable(m.getId()) //
        .filter(id -> !id.isBlank()) // id를 강제로 null로 고정했을 때, 여기서 NPE발생할지 궁금했는데.. 안남.
        .ifPresent(c ->
            memberDAO.addMember(member)
        );
    List<MemberBean> memberList = memberDAO.listMembers();
    request.setAttribute("memberList", memberList);
  %>
  ```

  위와 같이 하면 되긴된다... 😅




---

## 의견

* 14장 거의 100 페이지가 넘는... 대분량이였는데.. 잘 마쳤다.. 👍

  

## 정오표

* 575쪽

  * 안쪽 `<c:choose>`의 A학점 조건에서 100점도 포함되도록 `=`를 추가해야함

* 596쪽

  * 뉴욕에 대한 ZoneId가 잘못됨
    * `"America/New York"`> `"America/New_York"`

* 599쪽

  ```jsp
  fn:substring(title1, 3, 6) = ${fn:substring(title1, 3, 6)}<br> <!-- "lo " -->
  fn:substring('01234567890', 3, 6) = ${fn:substring('01234567890', 3, 6)}<br> <!-- "345" -->
  ```

  입력이 둘다 인덱스인데...

  ```
  beginIndex – the beginning index, inclusive.
  endIndex – the ending index, exclusive.
  ```

  3번인덱스 부터 6번 인덱스 전까지 잘라서 보여주는 것이니..

  문자열에서 4 ~ 6 번째 문자열을 반환한다고 설명이 붙는게 맞는 것 같다.



## 기타

* 현시점에는 `isELIgnored="false"`가 기본값이기 때문에 따로 명시하지 않아도 되긴하지만.. 이번 장에서만 그냥 써주자..😅

