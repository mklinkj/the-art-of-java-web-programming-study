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






## 의견

* 

  

## 정오표

* 



## 기타

* 
  
