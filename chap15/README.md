# 15장 JSP 페이지를 풍부하게 하는 오픈 소스 기능



> * common-fileupload의 사용관련인데... 일단은 Tomcat 10.1.x 을 사용하고 있어서, 서블릿 자체기능으로 진행이 될 것 같다.
>   * Servlet 3.0부터 파일 업로드 기능이 포함되었다고 함.
> * 예제 프로젝트: [pro15](pro15)



## 15.1 JSP에서 파일 업로드

* ...
* common-upload 사용 없이 Servlet 6.0 스팩대로만 진행해보자! 🎉

---

### Servlet 6.0 파일 업로드 스팩

* https://jakarta.ee/specifications/servlet/6.0/jakarta-servlet-spec-6.0.html#file-upload

##### 3.2. 파일 업로드

서블릿 컨테이너를 사용하면 데이터가 multipart/form-data로 전송될 때 파일을 업로드할 수 있습니다.

서블릿 컨테이너는 다음 조건 중 하나라도 충족되면 multipart/form-data 처리를 제공합니다.

요청을 처리하는 서블릿은 섹션 8.1.5, `"@MultipartConfig"`에 정의된 대로 `@MultipartConfig`로 어노테이션 처리됩니다.

배포 설명자(web.xml)에는 요청을 처리하는 서블릿에 대한 `multipart-config` 요소가 포함되어 있습니다.

multipart/form-data 유형 요청의 데이터를 사용 가능하게 만드는 방법은 서블릿 컨테이너가 multipart/form-data 처리를 제공하는지 여부에 따라 다릅니다.

서블릿 컨테이너가 multipart/form-data 처리를 제공하는 경우 HttpServletRequest에서 다음 메서드를 통해 데이터를 사용할 수 있습니다.

```java
public Collection<Part> getParts()

public Part getPart(String name)
```

각 부분은 Part.getInputStream 메서드를 통해 헤더, 이와 관련된 콘텐츠 유형 및 콘텐츠에 대한 액세스를 제공합니다.

Content-Disposition으로 form-data가 있지만 파일 이름이 없는 부분의 경우 부분의 문자열 값은 부분의 이름을 사용하여 HttpServletRequest의 getParameter 및 getParameterValues 메서드를 통해 사용할 수도 있습니다.

서블릿 컨테이너가 multi-part/form-data 처리를 제공하지 않는 경우 데이터는 HttpServletRequest.getInputStream을 통해 사용할 수 있습니다.

---

### 8.1.5. `@MultipartConfig`

* https://jakarta.ee/specifications/servlet/6.0/jakarta-servlet-spec-6.0.html#_MultipartConfig

이 어노테이션은 Servlet에 지정될 때 기대하는 요청이 multipart/form-data 유형임을 나타냅니다. 

해당 서블릿의 HttpServletRequest 객체는 getParts 및 getPart 메서드를 통해 MIME 첨부를 사용 가능하게 하여 다양한 MIME 첨부를 반복해야 합니다. 

jakarta.servlet.annotation.MultipartConfig의 `location` 속성과 `<multipart-config>`의 `<location>` 요소는 절대 경로로 해석되며 기본값은 `jakarta.servlet.context.tempdir` 값입니다. 

상대 경로를 지정하면 `tempdir` 위치를 기준으로 합니다. 

절대 경로 vs 상대 경로에 대한 테스트는 `java.io.File.isAbsolute`를 통해 수행되어야 합니다.



### `@MultipartConfig`의 속성

* **fileSizeThreshold**
  * 업로드 파일을 임시 저장할 때 크기 임계값을 지정합니다. 업로드 파일의 크기가 이 임계값보다 크면 디스크에 저장됩니다. 그렇지 않으면 파일이 메모리에 저장됩니다. 크기(바이트).
* **location**
  * 업로드 파일이 저장되는 디렉토리 지정
* **maxFileSize**
  * 업로드 파일의 최대 크기를 지정합니다. 크기(바이트).
* **maxRequestSize**
  * 요청의 최대 크기를 지정합니다(업로드 파일 및 기타 양식 데이터 모두 포함). 크기(바이트).

---

### commons-fileupload 메서드로 구현 된 내용을  Servlet 스팩 메서드로 수정

```java
@Slf4j
@WebServlet("/upload.do")
@MultipartConfig(
    fileSizeThreshold = 1 * MEGA_BYTE,
    maxFileSize = 10 * MEGA_BYTE,
    maxRequestSize = 15 * MEGA_BYTE,
    location = "C:\\upload\\art_of_java_web")
public class FileUpload extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doHandle(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doHandle(request, response);
  }

  private void doHandle(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    request.setCharacterEncoding(SERVER_ENCODING);

    Collection<Part> parts = request.getParts();
    for (Part p : parts) {
      if (isFormField(p)) {
        LOGGER.info("{}={}", p.getName(), request.getParameter(p.getName()));
      } else {
        LOGGER.info("매개변수 이름: {}", p.getName());
        LOGGER.info("파일 이름: {}", p.getSubmittedFileName());
        LOGGER.info("파일 크기: {}", p.getSize());

        p.write(UUID.randomUUID() + "__" + fileNameOnly(p.getSubmittedFileName()));
      }
    }
    response.sendRedirect("test01/uploadForm.jsp");
  }

  private boolean isFormField(Part part) {
    return part.getSubmittedFileName() == null;
  }
}
```

* isFormField() 메서드가 없음, 
  * 구분할 수 있는 방법은 Part의 getSubmittedFileName() 를 호출해서 null 인 경우 일반 Form 필드인지 확인할 수 있음.
  * 파일 등록 없는 상태에서는 파일 폼 필드 자체가 전달 되지 않음. 그러므로, 이 값이 null 이면서 전달 받은 경우면 일반 폼 필드라 판단할 수 있음.

* Part에 대한 getString() 메서드가 없음.

  * 필드의 이름은 알 수 있으니.. `request.getParameter(p.getName())` 으로 일반 폼 필드 값을 알아낼 수 있음.

* 책에서는 전체 경로가 포함될 경우를 가정해서, `\\`, `/`의 이후 문자열을 얻어 파일명만 얻으려는 코드가 보이는데... getSubmittedFileName()가 경로를 제외한 파일명만 반환하기 때문에, 해당 로직은 불필요한 것으로 판단되어 추가하지 않음.

  * 책의 실행결과를 봐도 경로는 따로 포함되지 않아서 진짜 불필요할 것 같은데...😅

  * https://javadoc.io/doc/commons-fileupload/commons-fileupload/latest/index.html

    ```
    However, some clients, such as the Opera browser, do include path information.
    ```

    그런데... 대부분은 파일명만 포함되지만...  ✨클라이언트 브라우저에 따라 경로까지 포함하는 경우가 있어서 그런 처리가 들어갔구나... 나도 추가해야겠다... 😅😅😅

    ```java
    public static String fileNameOnly(String fileName) {
      return Paths.get(fileName).getFileName().toString();
    }
    ```

    Java 1.7 부터 Paths의 메서드를 쓰면 되겠다. Windows, Linux 경로 알아서 해주는듯..

    

* 파일을 저장할 때.. 항상 다른 이름으로 저장되도록 `UUID.randomUUID() + "__"` 붙임.





## 15.2 JSP에서 파일 다운로드

* ...
* 이부분은 서블릿 스팩이나 기타 라이브러리하고는 크게 관련은 없긴하다. Java의 기능으로 시스템의 파일을 응답으로 내려주는 것이여서... 😅






---

## 의견

* 이번 장은 commons-upload를 쓰지 않고 진행을 해보긴 했는데... 그런대로 잘 되긴 한 것 같다. 😅 무사히 마쳤음.🎉

* 테스트를 어떤식으로 할지는 아직 잘 모르겠는데.. 나중에 천천히 추가해보자..😅

  

## 정오표

* ...
  
  

## 기타

* ...

