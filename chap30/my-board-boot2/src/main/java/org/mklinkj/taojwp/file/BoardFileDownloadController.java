package org.mklinkj.taojwp.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.mklinkj.taojwp.common.util.ProjectDataUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardFileDownloadController {

  private final String uploadPath = ProjectDataUtils.getProperty("upload_path");

  @GetMapping("/download.do")
  public void download(String imageFileName, Integer articleNo, HttpServletResponse response)
      throws IOException {

    LOGGER.info("imageFileName: {}", imageFileName);

    try (OutputStream out = response.getOutputStream()) {
      String path = uploadPath + File.separator + articleNo + File.separator + imageFileName;
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
