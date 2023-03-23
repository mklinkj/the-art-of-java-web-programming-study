package org.mklinkj.taojwp.sec01.ex01;

import static org.mklinkj.taojwp.common.CommonUtils.fileNameOnly;
import static org.mklinkj.taojwp.common.Constants.MEGA_BYTE;
import static org.mklinkj.taojwp.common.Constants.SERVER_ENCODING;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;

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
