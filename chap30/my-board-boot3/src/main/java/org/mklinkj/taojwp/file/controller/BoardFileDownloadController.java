package org.mklinkj.taojwp.file.controller;

import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mklinkj.taojwp.common.util.ProjectDataUtils;
import org.mklinkj.taojwp.file.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardFileDownloadController {

  private final FileService fileService;

  private final String uploadPath = ProjectDataUtils.getProperty("upload_path");

  @GetMapping("/download.do")
  public void download(String uuidFileName, Integer articleNo, HttpServletResponse response)
      throws IOException {

    String originalFileName = fileService.getOriginalFileName(uuidFileName);

    LOGGER.info("uuidFileName: {}", uuidFileName);
    LOGGER.info("originalFileName: {}", originalFileName);

    try (OutputStream out = response.getOutputStream()) {
      String path = uploadPath + File.separator + articleNo + File.separator + uuidFileName;
      File imageFile = new File(path);

      response.setHeader("Cache-Control", "no-cache");
      response.setHeader(
          "Content-disposition", String.format("attachment;filename=%s", originalFileName));

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
