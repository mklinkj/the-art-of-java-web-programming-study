package org.mklinkj.taojwp.sec01.ex01;

import static org.mklinkj.taojwp.common.CommonUtils.fileNameOnly;
import static org.mklinkj.taojwp.common.Constants.UTF_8_ENCODING;

import java.io.IOException;
import java.util.Collection;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;

/**
 * Servlet 스팩의 파일 업로드 기능을 사용한 서블릿
 *
 * <p>web.xml에 명시하지 않고, 해당 기능을 사용하는 클래스에다가만 사용했기 때문에, 문제는 없을 것 같다.
 */
@Slf4j
// ✨ 아래 내용을 동적으로 하고 싶었는데, 어노테이션 안의 설정 값은 오직 상수만 넣을 수 있어서 프로퍼티 값 처리가 불가능하다.
// @WebServlet("/upload.do")
// @MultipartConfig(
//    fileSizeThreshold = MEGA_BYTE,
//    maxFileSize = 10 * MEGA_BYTE,
//    maxRequestSize = 15 * MEGA_BYTE,
//    location = "C:\\upload\\art_of_java_web")
public class FileUpload extends AbstractHttpServlet {
  @Override
  protected void doHandle(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    request.setCharacterEncoding(UTF_8_ENCODING);

    Collection<Part> parts = request.getParts();
    for (Part p : parts) {
      if (isFormField(p)) {
        LOGGER.info("{}={}", p.getName(), request.getParameter(p.getName()));
      } else {
        LOGGER.info("매개변수 이름: {}", p.getName());
        LOGGER.info("파일 이름: {}", p.getSubmittedFileName());
        LOGGER.info("파일 크기: {}", p.getSize());
        if (p.getSize() > 0) {
          p.write(UUID.randomUUID() + "__" + fileNameOnly(p.getSubmittedFileName()));
        }
      }
    }
    response.sendRedirect("test01/uploadForm.jsp");
  }

  private boolean isFormField(Part part) {
    return part.getSubmittedFileName() == null;
  }
}
