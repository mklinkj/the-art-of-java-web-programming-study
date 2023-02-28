# 8장 서블릿 확장 API 사용하기

> * Struts 비슷한 환경에서 포워드, 리다이렉트 등등 해깔렸는데.. 이번에 잘 읽어보자.
> * 예제 프로젝트: [pro08](pro08)



## 목차

### 8.1 서블릿의 포워드 기능 사용하기

* ...

#### 8.1.1 포워드 기능

* ...

* 포워드 (forward)
  * 하나의 서블릿에서 다른 서블릿이나 JSP와 연동하는 방법



### 8.2 서블릿의 여러 가지 포워드 방법

* **redirect 방법**

  * HttpServletResponse의 redirect() 메서드 사용

* **Refresh 방법**

  * HttpServletResponse의 addHeader() 메서드 사용

* **location 방법**

  * 자바스크립트의 location.href

* **dispatch 방법**

  * RequestDispatcher 클래스의 forward() 메서드를 이용.

    ```java
    RequestDispatcher dis = request.getRequestDispatcher("포워드할 서블릿 또는 JSP");
    dis.forward(request, response);
    ```



#### 8.2.1 redirect를 이용한 포워딩

* ...

#### 8.2.2  redirect를 이용한 포워딩 실습

* ...

* Spring Web의 HttpStatus 클래스의 응답 코드 정의한 것을 보면...

  ```java
  	/**
  	 * {@code 302 Found}.
  	 * @see <a href="https://tools.ietf.org/html/rfc7231#section-6.4.3">HTTP/1.1: Semantics and Content, section 6.4.3</a>
  	 */
  	FOUND(302, Series.REDIRECTION, "Found"),
  	/**
  	 * {@code 302 Moved Temporarily}.
  	 * @see <a href="https://tools.ietf.org/html/rfc1945#section-9.3">HTTP/1.0, section 9.3</a>
  	 * @deprecated in favor of {@link #FOUND} which will be returned from {@code HttpStatus.valueOf(302)}
  	 */
  	@Deprecated
  	MOVED_TEMPORARILY(302, Series.REDIRECTION, "Moved Temporarily"),
  ```

  * 302 코드에 대해서 ... `MOVED_TEMPORARILY`이 Deprecated 되고 FOUND로 쓰라는 것 같다.

    ```
    6.4.3 . 302 Found
    
    302(Found) 상태 코드는 대상 리소스가 일시적으로 다른 URI 아래에 있음을 나타냅니다. 경우에 따라 리디렉션이 변경될 수 있으므로 클라이언트는 향후 요청에 대해 유효한 요청 URI를 계속 사용해야 합니다.
    ```

    

#### 8.2.3 refresh를 이용한 포워딩

* ...
* 최초 요청 서블릿은 여러개로 쓰더라도 화면 표시 서블릿은 하나로 써야겠다. 그러기 위해서 파라미터를 전달하는 식으로 바꿨다.

#### 8.2.4 refresh를 이용한 포워딩 실습

* ...

#### 8.2.5 location을 이용한 포워딩 실습

* ...

#### 8.2.6 redirect 방식으로 다른 서블릿에 데이터 전달하기

* ...
* 파라미터로 데이터 전달하는 걸 이미 해보긴 해서.. 그래도 name파라미터를 추가했다.





### 8.3 dispatch를 이용한 포워드 방법

#### 8.3.1 dispatch를 이용한 포워딩 과정

* ...
* 클라이언트의 웹브라우저를 거치지 않고 서버에서 포워딩 진행
  * 주소창의 URL이 변경되지 않음

* 그냥 클래스 이름을 `DispatchForwardFirstServlet`으로 했다.
  * 디스패치 코드는 어디선가 본것 같다.. 그때는 스프링 포워드 하는방식 밖에 몰라서 왜이렇게 복잡하지? 란 생각을 했는데... 😓



#### 8.3.2 서블릿을 이용한 dispatch 포워딩 실습

* ...

* 서버측 포워딩을 할 때, 다른 서블릿으로 전달할 파라미터는 URL 쓰듯이 쓰면 됨.

  ```java
  RequestDispatcher dispatch = request.getRequestDispatcher("dispatchForwardSecond?name=lee");
  ```

* POST 디스패치는 테스트 하지 않았는데.. 메서드만 다르게 똑같이 되는 것 같다.

  * https://stackoverflow.com/questions/1940306/does-a-requestdispatcher-forward-maintain-the-original-requests-http-method



### 8.4 바인딩

* 바인딩(Binding) : 서블릿 또는 JSP로 대량의 데이터를 공유하거나 전달하고 싶을 때 사용
  * request에 paramter하고 attribute 가 좀 해깔렸었다.. 이제 확실헤진 것 같다. 👍
* ...
  

#### 8.4.1 HttpServletRequest를 이용한 redirect 포워딩 시 바인딩

* ...
* 주소를 설정한 request는 이미 종료됬음..
  * second 서블릿의 request는 브라우저가 새로 요청한 request임
  * 그러므로 주소가 null로 나오게 됨.
  * 저자님과의 패키지가 다르더라도 꼭 똑같이 가진 않아도 되겠다. 반드시 패키지 경로를 똑같이 지키려다.. 오히려 더 복잡해질 수 있음.

#### 8.4.2 HttpServletRequest를 이용한 dispatch 포워딩 시 바인딩

* ...
* 첫번째 서블릿에서 두번째 서블릿으로 전달되는 request가 브라우저를 거치지않고 바로 전달되어 주소가 정상 노출된다.



#### 8.4.3 두 서블릿 간 회원 정보 조회 바인딩 실습

* ... 
* 추가, 삭제 기능도 연동되게 수정하였다.

  * 테스트 코드가 점점 복잡해지고 있다. 🤪




### 8.5 ServletContext와 ServletConfig 사용법

* ...
* https://youtu.be/OiQ4qmW2oOQ
* ServletContext는 컨택스트당 생성
* ServletConfig는 각 서블릿마다 생성



#### 8.5.2 ServletContext 바인딩 기능

* ...

* ServletContext 관련 메서드를 사용하는 서블릿을 테스트 할 때.. 오류가 남.

  * 관련 부분은 처음에 아래 글 보고 Mockito로 처리 했었는데...

    * https://stackoverflow.com/a/22611270

  * Spring Test에서 이미 MockServletContext 란 것을 제공해줌

    * request와 테스트할 서블릿의 ServletContext 동기화만 해주면 Mockito 없이도 테스트를 잘 할 수 있음.
      * MockHttpServletTestSupport와 SetServletContextTest, GetServletContextTest 를 참고 할 것. 😄

    

    







## 의견

* 

  

## 정오표

* 



## 기타

* 
