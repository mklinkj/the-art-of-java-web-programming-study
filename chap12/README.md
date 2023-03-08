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

* 









---

## 의견

* ...

  

## 정오표

* ...



## 기타

* IntellJ에서 기본으로 Google Java Format 활성화, Properties 파일 인코딩 UTF-8 바꾸기 설정하려면 프로젝트젝트 목록 나오는 초기 메뉴 설정에서 설정해주면 전역 설정 먹는 것 같다. 
  * 그동안 매일 프로젝트 새로 열때마다 설정했었는데...😅
