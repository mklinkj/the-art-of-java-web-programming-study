# 13장 자바 코드를 없애는 액션 태그



> * 액션 태그에서 인클루드 액션 태그는 업무에서도 썼던 것 같기도 하고 기억이.....😅
> * 예제 프로젝트: [pro13](pro13)

* 디자에너 입장에서 더 쉽고 편리하게 작업할 수 있는 태그 형태로 기능제공



### 13.1 인클루드 액션 태그 사용하기

* ...

* 인클루드 액션 태그와 인클루드 디렉티브 태그의 중요 차이

  * 인클루드 액션 태크는 포함되는 JSP가 각각 자바 파일로 생성

    ```jsp
    <jsp:include page="smile_image.jsp" flush="true">
        <jsp:param name="name" value="스마일"/>
        <jsp:param name="imgName" value="smile.png"/>
      </jsp:include>
    ```

  * 인클루드 디렉티브 태크는 합쳐져서 하나의 자바 파일로 생성

    ```jsp
    <% include file="duke_image.jsp" %>
    ```

  * 정말 하나의 파일로 생성되는지? 별도 클래스로 분리되어있음.

    ![image-20230310162908990](doc-resources/image-20230310162908990.png)

    

  * 부모 페이지에서 자식으로 파라미터 전달하는 부분

    ```java
    org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "smile_image.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("name", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("스마일", request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("imgName", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("smile.png", request.getCharacterEncoding()), out, true);
    ```



## 13.2 포워드 액션 태그 사용하기

* 포워드 액션 태그의 형식

  ```jsp
  <jsp:forward page="포워딩할 JSP 페이지">
    ...
  </jsp:forward>
  ```

* ...





## 13.3 useBean, setProperty, getProperty 액션 태그 사용하기

* ...

### 13.3.1 자바 빈을 이용한 회원 정보 조회 실습

* ...

### 13.3.2 유즈빈 액션 태그를 이용한 회원 정보 조회 실습

* ...

### 13.3.3 setProperty/getProperty 액션 태그를 이용한 회원 정보 조회 실습

* ...

#### 예외

* `/member3.jsp (행: [13], 열: [50]) 속성 값 [request.getParameter("id")]이(가)  ["]을(를) 사용하여 인용부 처리되어 있는데, 이는 값 내에서 사용될 때에는 반드시 escape되어야 하는 것입니다.`

  ```jsp
  <jsp:setProperty name="m" property="id" value="<%=request.getParameter("id")%>" />
  ```

  value 를 `"` 대신에 `'`로 감싸주면 정상 처리된다.

  ```jsp
  <jsp:setProperty name="m" property="id" value='<%=request.getParameter("id")%>' />
  ```

  value를 쌍따옴표로(`"`)감싸면 값 내용 중의 쌍따옴표를 이스케이프 처리하려해서 오류나는 것 같다. Tomcat 서버 설정의 strictQuoteEscaping를 false로 바꿔줘도 된다지만.. 그냥 따옴표(`'`)로 감싸서 해결하는게 낫겠다.

  

  

---

## 의견

* 13장도 무사히 마쳤다..🎉

  

## 정오표

* ...
  


## 기타

* IntelliJ에서 JSP 파일 만들때... 기본 템플릿이 쓸때없이 OS 유저명이나 호스트명 포함된 주석이 포함되고 HTML5 관련 내용도 포함이 안되어서 템플릿 추가했다.

  ![image-20230310155005316](doc-resources/image-20230310155005316.png)

  ```jsp
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <!DOCTYPE html>
  <html lang="ko">
    <head>
      <meta charset="UTF-8">
      <title>#[[$Title$]]#</title>
    </head>
    <body>
    #[[$END$]]#
    </body>
  </html>
  
  ```

  이렇게 설정해두면...  새로만들기 할 때... 나타남..

  ![image-20230310155133019](doc-resources/image-20230310155133019.png)
