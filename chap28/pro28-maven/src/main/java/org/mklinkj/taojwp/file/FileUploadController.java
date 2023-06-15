package org.mklinkj.taojwp.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.mklinkj.taojwp.common.util.ProjectDataUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/file")
@Slf4j
@Controller
public class FileUploadController {
  private static final String IMAGE_REPO_PATH = ProjectDataUtils.getProperty("image_repo_path");

  @GetMapping("/form")
  public String form() {
    return "file/uploadForm";
  }

  @GetMapping("/result")
  public String result() {
    return "file/result";
  }

  @PostMapping("/upload")
  public String upload(
      MultipartHttpServletRequest multipartRequest, RedirectAttributes redirectAttributes)
      throws IOException {

    Map<String, Object> map = new HashMap<>();
    Enumeration<String> names = multipartRequest.getParameterNames();

    while (names.hasMoreElements()) {
      String name = names.nextElement();
      String value = multipartRequest.getParameter(name);
      map.put(name, value);
    }

    List<String> fileList = fileProcess(multipartRequest);
    map.put("fileList", fileList);

    redirectAttributes.addFlashAttribute("map", map);

    return "redirect:result";
  }

  private List<String> fileProcess(MultipartHttpServletRequest multipartRequest)
      throws IOException {
    List<String> fileList = new ArrayList<>();
    Iterator<String> fileNames = multipartRequest.getFileNames();

    while (fileNames.hasNext()) {
      String fileName = fileNames.next();
      MultipartFile multipartFile = multipartRequest.getFile(fileName);
      String originalFileName = multipartFile.getOriginalFilename();
      fileList.add(originalFileName);

      File file = new File(IMAGE_REPO_PATH + File.separator + fileName);

      if (multipartFile.getSize() != 0) {
        if (!file.exists()) {
          if (file.getParentFile().mkdirs()) {
            file.createNewFile();
          }
        }
        multipartFile.transferTo(new File(IMAGE_REPO_PATH + File.separator + originalFileName));
      }
    }
    return fileList;
  }
}
