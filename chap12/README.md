# 12장 JSP 스크립트 요소기능



> * JSP는 보통 JSTL 테그 라이브러리로만 주로 써서 스크립트 요소를 쓸일이 별로 없었음.. 쓰더라도 레거시 코드에 남아이있는거 확인하는 정도만 봤던 것 같다.
> * 예제 프로젝트: [pro12](pro12)



## 12.1 JSP 스크립트 요소

* ...

* 요소 3가지

  * 선언문

  * 스크립트릿

  * 표현식

    


## 12.2  선언문 사용하기

* 형식

  ```jsp
  <%! 멤버 변수 or 멤버 메서드 %>
  ```

  

#### 12.2.1 JSP에서 선언문 실습

* ...

* 변환된 코드를 보면 멤버 변수로 변환됨

  ```java
  ...
  public final class hello_jsp extends org.apache.jasper.runtime.HttpJspBase
      implements org.apache.jasper.runtime.JspSourceDependent,
                   org.apache.jasper.runtime.JspSourceImports,
                   org.apache.jasper.runtime.JspSourceDirectives {
  
  
    String name = "듀크";
    public String getName() {
      return name;
    }
  ...
  }
  ```

  

## 12.3 스크립트릿 사용하기

* ...

  ```jsp
  <% 자바 코드 %>
  ```



### 12.3.1 JSP에서 스크립트릿 실습

* 스크립트릿으로 작성한 코드는 _jspService() 메서드 내에 코드가 위치하게 된다.

  ```java
    public void _jspService(final jakarta.servlet.http.HttpServletRequest request, final jakarta.servlet.http.HttpServletResponse response)
        throws java.io.IOException, jakarta.servlet.ServletException {
        ...
        String age = request.getParameter("age"); 
        ...
    }
  ```





## 12.4 표현식 사용하기

* ...

* 표현식: JSP 페이지의 정항 위치에 값을 출력하는 기능

  ```jsp
  <%=값 or 자바 변수 or 자바 식 %>
  ```

  

### 12.4.1 JSP 페이지에서 표현식 실습

* ...

* 표현식 영역은 out.print() 로 출력됨

  ```java
        out.write("  <h1>안녕하세요 ");
        out.print(name );
        out.write("님!!</h1>\r\n");
        out.write("  <h1>나이는 ");
        out.print(age );
        out.write("입니다.!!</h1>\r\n");
        out.write("  <h1>키는 ");
        out.print(180 );
        out.write("입니다.!!</h1>\r\n");
        out.write("  <h1>나이 + 10은 ");
        out.print(Integer.parseInt(age) + 10 );
        out.write("입니다.!!</h1>\r\n");
  ```

  



## 12.5 JSP 주석문 사용하기

* ...
* JSP 페이지에서 사용되는 주석문들..
  * HTML 주석
  * 자바 주석
  * JSP 주석



### 12.5.1 JSP 페이지에서 주석문 사용하기

* ...

* Java 주석과 HTML 주석은 변환된 Java 파일에 기록이됨

  ```java
    /* // 자바 주석문
    String age = request.getParameter("age");
    */
  
        out.write("\r\n");
        out.write("\r\n");
        out.write("<!DOCTYPE html>\r\n");
        out.write("<!-- HTML 주석문 -->\r\n");
        out.write("<html lang=\"ko\">\r\n");
        out.write("<head>\r\n");
        out.write("  <title>주석문 연습</title>\r\n");
        out.write("</head>\r\n");
        out.write("<body>\r\n");
        out.write("  <h1>주석문 예제입니다!!</h1>\r\n");
        out.write("  ");
        out.write(' ');
        out.write("\r\n");
        out.write("</body>\r\n");
        out.write("</html>\r\n");
  ```

  그러나 JSP 주석은 남지 않음.



## 12.6 스크립트 요소에 이용해 실습하기

* ...

### 12.6.1 로그인 예제

* ...



### 12.6.2 학점 변환 예제

* ...



### 12.6.3 구구단 출력 예제

* ...



### 12.6.4 이미지 리스트 출력 예제

* ...
* 스타일이 많네... 😅 CSS 는 아무리봐도 잘 모르겠음...😆



## 12.7 내장 객체 (내장 변수) 기능

* ...
* JSP가 서블릿으로 변환될 때, 컨테이너가 자동으로 생성 시키는 서블릿 멤버 변수.



### 12.7.1 session 내장 객체에 데이터 바인딩 실습

* ...

  

### 12.7.2 application 내장 객체에 데이터 바인딩 실습

* ...



### 12.7.3 request 내장 객체에 데이터 바인딩 실습

* ...
* request1.jsp에서 request2.jsp로 포워딩하기 때문에 주소가 바뀌지 않음.. (서버측에서 페이지 변경)



### 12.7.4 out 내장 객체 이용해 데이터 출력하기

* ...
* 값 출력할 때.. println 메서드를 사용해서 JSP 표현식만 이용해서 출력하는 것 보다 좀 더 간단하게 출력가능



## 12.8 JSP 페이지 예외 처리하기

* ...
* 

> 그런데 환경에 따라 오류 메시지 본문중 JSP 내부의 한글이 깨져보이는 컴퓨터가 있고, 안깨져 보이는 컴퓨터가 있음? 
>
> IntelliJ의 Tomcat 콘솔은 명령프롬프트(CMD)와 다르게 UTF-8 출력을 정상 지원하기 때문에 file.encoding만 제대로 설정해주면 안깨질 것 같긴함.



### 12.8.1 JSP 페에지 예외 처리 과정

* ...
* 예외 JSP 처리 페이지의 isErrorPage 속성 true 설정
* 일반 JSP 페이지에서 errorPage 를 예외 처리 페이지 이름으로 지정



### 12.8.2 JSP 페이지 예외 처리 실습

* 처음에... `<%=exception.printStackTrace() %>` 로 잘못보고 왜 void반환인데 표현식에 넣으셨지? 하고 다음과 같이 쓰긴 했는데... (지면에도 이클립스 콘솔로 예외 메시지를 출력할거라고 잘 써있음 😅)

  ```jsp
  <textarea cols="100" rows="35" readonly><%=
      Arrays.stream(exception.getStackTrace())
          .map(StackTraceElement::toString)
          .collect(Collectors.joining(String.format("%n    ")))
  %></textarea>
  ```

  책을 다시 보니 `<% exception.printStackTrace() %>` 표현식이 아니고 스크립트릿이였음..😅

  그런데 그냥 서버 콘솔의 Std Out에만 쓰는 동작이기 때문에.. 내가 바꿔서 한대로 하는게 낫겠다.



### 12.8.3 JSP 페이지의 오류 페이지 종류

* ...



### 12.8.4 에러 코드에 따른 예외 페이지 지정

* ...

* web.xml

  ```xml
  <error-page>
    <error-code>오류 코드</error-code>
    <location>오류 페이지 위치</location>
  <error-page>
  ```

  

## 12.9 JSP welcome 파일 지정하기

* ...

* 이 부분은 그냥 읽어만보고 넘어가도 되겠다.. 이미 index.html을 welcome 파일로 사용하고 있어서...

* web.xml

  ```xml
    <welcome-file-list>
      <welcome-file>index.html</welcome-file>
      <welcome-file>index.xhtml</welcome-file>
      <welcome-file>index.htm</welcome-file>
      <welcome-file>index.jsp</welcome-file>
    </welcome-file-list>
  ```



## 12.10 스크립트 요소 이용해 회원 정보 조회하기

#### 실수가 있었다. 😅

* `java.sql.SQLException: ORA-01008: 일부 변수가 바인드되지 않았습니다`. - 예외 발생관련....

  MemberDAO를 수정할 때... 

  ````java
        con = dataFactory.getConnection();
        String query = "SELECT id, pwd, name, email, joinDate FROM t_member";
  
        if(_name == null || _name.isBlank()) {
          psmt = con.prepareStatement(query);
        } else {
          query = query.concat(" WHERE name=?");
          psmt = con.prepareStatement(query);
          psmt.setString(1, _name);  // 쿼리 및 파라미터를 준비 해놓고...
        }
        LOGGER.info("query: {}", query);
        ResultSet rs = psmt.executeQuery(query); // 이전 쿼리로 돌려버림
  ````

  PreparedStatement의 setString으로 파라미터를 설정해놓고 다시 ?가 포함된 쿼리로 돌려버림..😅

  ```java
        ResultSet rs = psmt.executeQuery(); // 인자 없이 실행해주자~
  ```

  

  





---

## 의견

* ...

  

## 정오표

* p444 맨위의 코드 대상 링크주소 잘못됨
  * session3.jsp `->`session2.jsp




## 기타

* IntellJ에서 기본으로 Google Java Format 활성화, Properties 파일 인코딩 UTF-8 바꾸기 설정하려면 프로젝트젝트 목록 나오는 초기 메뉴 설정에서 설정해주면 전역 설정 먹는 것 같다. 
  * 그동안 매일 프로젝트 새로 열때마다 설정했었는데...😅
