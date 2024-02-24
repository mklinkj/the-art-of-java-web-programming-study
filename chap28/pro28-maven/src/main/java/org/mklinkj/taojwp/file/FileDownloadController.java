package org.mklinkj.taojwp.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import javax.servlet.http.HttpServletResponse;
import net.coobird.thumbnailator.Thumbnails;
import org.mklinkj.taojwp.common.util.ProjectDataUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/file")
@Controller
public class FileDownloadController {
  private final String imageRepoPath = ProjectDataUtils.getProperty("image_repo_path");

  @GetMapping("/download")
  public void download(
      @RequestParam("imageFileName") String imageFileName, HttpServletResponse response)
      throws Exception {

    OutputStream out = response.getOutputStream();
    String downFile = imageRepoPath + File.separator + imageFileName;
    File file = new File(downFile);

    response.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache");
    response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=" + imageFileName);

    responseFile(response, out, file);
  }

  /** 썸네일 파일을 별도 생성하여 다운로드 */
  @GetMapping("/downloadThumbnail/{imageFileName}")
  public void downloadThumbnail(
      @PathVariable("imageFileName") String imageFileName, HttpServletResponse response)
      throws Exception {

    OutputStream out = response.getOutputStream();
    String downFile = imageRepoPath + File.separator + imageFileName;
    File image = new File(downFile);

    int lastIndex = imageFileName.lastIndexOf(".");

    String fileName = imageFileName.substring(0, lastIndex);

    File thumbnail =
        new File(imageRepoPath + File.separator + "thumbnail" + File.separator + fileName + ".png");

    if (image.exists() && Files.probeContentType(image.toPath()).startsWith("image")) {
      thumbnail.getParentFile().mkdirs();
      Thumbnails.of(image).size(50, 50).outputFormat("png").toFile(thumbnail);
    } else {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return;
    }

    responseFile(response, out, thumbnail);
  }

  /** 썸네일 파일을 별도 관리하지 않고, OutStream에 바로 출력 */
  @GetMapping("/downloadThumbnailOut/{imageFileName}")
  public void downloadThumbnailOut(
      @PathVariable("imageFileName") String imageFileName, HttpServletResponse response)
      throws Exception {

    OutputStream out = response.getOutputStream();
    String downFile = imageRepoPath + File.separator + imageFileName;
    File image = new File(downFile);

    if (image.exists() && Files.probeContentType(image.toPath()).startsWith("image")) {
      Thumbnails.of(image).size(50, 50).outputFormat("png").toOutputStream(out);
    } else {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }
  }

  private void responseFile(HttpServletResponse response, OutputStream out, File thumbnail)
      throws IOException {
    try (out;
        FileInputStream fis = new FileInputStream(thumbnail)) {
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
    }
  }
}
