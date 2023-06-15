package org.mklinkj.taojwp.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.mklinkj.taojwp.common.util.ProjectDataUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RequestMapping("/file")
@Controller
public class FileDownloadController {
  private static final String IMAGE_REPO_PATH = ProjectDataUtils.getProperty("image_repo_path");

  @GetMapping("/download")
  public void download(
      @RequestParam("imageFileName") String imageFileName, HttpServletResponse response)
      throws Exception {

    OutputStream out = response.getOutputStream();
    String downFile = IMAGE_REPO_PATH + File.separator + imageFileName;
    File file = new File(downFile);

    response.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache");
    response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=" + imageFileName);

    try (FileInputStream fis = new FileInputStream(file)) {
      byte[] buffer = new byte[1024 * 8];
      while (true) {
        int count = fis.read(buffer);
        if (count == -1) {
          break;
        }
        out.write(buffer, 0, count);
      }
    } catch (FileNotFoundException e) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    } finally {
      out.close();
    }
  }
}
