package org.mklinkj.taojwp.board.common;

import static org.mklinkj.taojwp.common.constant.Constants.HTML_CONTENT_TYPE;
import static org.mklinkj.taojwp.common.constant.Constants.UPLOAD_DIR;
import static org.mklinkj.taojwp.common.constant.Constants.UTF_8_ENCODING;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import lombok.extern.slf4j.Slf4j;
import org.mklinkj.taojwp.common.servlet.AbstractHttpServlet;

@Slf4j
@WebServlet("/download.do")
public class FileDownloadController extends AbstractHttpServlet {

  @Override
  protected void doHandle(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    request.setCharacterEncoding(UTF_8_ENCODING);
    response.setContentType(HTML_CONTENT_TYPE);

    String imageFileName = request.getParameter("imageFileName");
    String articleNo = request.getParameter("articleNo");

    LOGGER.info("imageFileName: {}", imageFileName);

    try (OutputStream out = response.getOutputStream()) {
      String path = UPLOAD_DIR + File.separator + articleNo + File.separator + imageFileName;
      File imageFile = new File(path);

      response.setHeader("Cache-Control", "no-cache");
      response.setHeader(
          "Content-disposition", String.format("attachment;filename=%s", imageFileName));

      try (FileInputStream in = new FileInputStream(imageFile)) {
        byte[] buffer = new byte[1024 * 8];

        while (true) {
          int count = in.read(buffer);
          if (count == -1) {
            break;
          }
          out.write(buffer, 0, count);
        }
      }
    }
  }
}
