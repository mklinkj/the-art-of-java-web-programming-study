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

  









---

## 의견

* ...

  

## 정오표

* ...



## 기타

* IntellJ에서 기본으로 Google Java Format 활성화, Properties 파일 인코딩 UTF-8 바꾸기 설정하려면 프로젝트젝트 목록 나오는 초기 메뉴 설정에서 설정해주면 전역 설정 먹는 것 같다. 
  * 그동안 매일 프로젝트 새로 열때마다 설정했었는데...😅
