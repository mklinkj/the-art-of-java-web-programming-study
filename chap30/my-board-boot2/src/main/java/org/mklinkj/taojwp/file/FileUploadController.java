package org.mklinkj.taojwp.file;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@RequestMapping("/file")
@Slf4j
@Controller
public class FileUploadController {

  private final FileUploadService fileUploadService;

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

    List<String> fileList = fileUploadService.multiImageFileUploadExample(multipartRequest);
    map.put("fileList", fileList);

    redirectAttributes.addFlashAttribute("map", map);

    return "redirect:result";
  }
}
