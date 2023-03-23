package org.mklinkj.taojwp.sec01.ex02;

import static org.mklinkj.taojwp.common.Constants.HTML_CONTENT_TYPE;
import static org.mklinkj.taojwp.common.Constants.SERVER_ENCODING;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/download.do")
public class FileDownload extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    doHandle(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    doHandle(request, response);
  }

  private void doHandle(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    request.setCharacterEncoding(SERVER_ENCODING);
    response.setContentType(HTML_CONTENT_TYPE);

    String fileRepo = "C:\\upload\\file_repo";

    String fileName = request.getParameter("fileName");
    LOGGER.info("fileName: {}", fileName);

    OutputStream out = response.getOutputStream();
    String downFile = fileRepo + "\\" + fileName;
    File f = new File(downFile);

    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Content-Disposition", "attachment; fileName=" + fileName);

    FileInputStream in = new FileInputStream(f);
    byte[] buffer = new byte[1024 * 8];
    while (true) {
      int count = in.read(buffer);
      if (count == -1) {
        break;
      }
      out.write(buffer, 0, count);
    }
    in.close();
    out.close();
  }
}
