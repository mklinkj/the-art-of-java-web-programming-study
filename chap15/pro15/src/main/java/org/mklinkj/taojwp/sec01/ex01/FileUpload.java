package org.mklinkj.taojwp.sec01.ex01;

import static org.mklinkj.taojwp.common.CommonUtils.fileNameOnly;
import static org.mklinkj.taojwp.common.Constants.MEGA_BYTE;
import static org.mklinkj.taojwp.common.Constants.SERVER_ENCODING;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;

/**
 * Servlet 스팩의 파일 업로드 기능을 사용한 서블릿
 *
 * <p>web.xml에 명시하지 않고, 해당 기능을 사용하는 클래스에다가만 사용했기 때문에, 문제는 없을 것 같다.
 */
@Slf4j
@WebServlet("/upload.do")
@MultipartConfig(
    fileSizeThreshold = MEGA_BYTE,
    maxFileSize = 10 * MEGA_BYTE,
    maxRequestSize = 15 * MEGA_BYTE
    // location은 임시로 저장하는 경로인데, 명시하지 않으면...
    // 시스템 임시 폴더에 저장될 것 같다. (정확한 임시파일 저장 경로를 "java.io.tmpdir" 일것 같았는데... 모르겠음...)
    // 이 값을 비우고 Part의 InputStream을 얻어서 다른 곳으로 복사하는 방식이 나을 것 같긴하다.
    // 스프링이 이런식인 것 같음...
    /* , location = "C:\\upload\\art_of_java_web" */ )
public class FileUpload extends AbstractHttpServlet {
  @Override
  protected void doHandle(HttpServletRequest request, HttpServletResponse response)
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
        LOGGER.info("java.io.temp 경로: {}", System.getProperty("java.io.tmpdir"));

        // p.write(UUID.randomUUID() + "__" + fileNameOnly(p.getSubmittedFileName()));
        // 아래처럼 하면 업로드 경로를 프로퍼티로 관리하기가 편해진다..
        Files.copy(
            p.getInputStream(),
            Path.of(
                "C:\\upload\\art_of_java_web",
                UUID.randomUUID() + "__" + fileNameOnly(p.getSubmittedFileName())));
      }
    }
    response.sendRedirect("test01/uploadForm.jsp");
  }

  private boolean isFormField(Part part) {
    return part.getSubmittedFileName() == null;
  }
}
