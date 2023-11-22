package org.mklinkj.taojwp.common.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;
import org.webjars.WebJarAssetLocator;

/**
 * 알려진 설정으로는 webjars를 버전없이 사용할 수 없어서 경로를 만들어주는 컨트롤러 작성
 *
 * <p>문제 해결에 가장 도움된 답변 👍
 *
 * <p><a
 * href="https://stackoverflow.com/questions/35536836/remove-webjars-version-from-url">remove-webjars-version-from-url</a>
 */
@Slf4j
@Controller
public class WebJarsController {
  private static final WebJarAssetLocator LOCATOR = new WebJarAssetLocator();

  @ResponseBody
  @GetMapping("/webjars_locator/{webjar}/**")
  public ResponseEntity<Resource> locateWebjarAsset(
      @PathVariable("webjar") String webjar, HttpServletRequest request) {

    try {
      String mvcPrefix = String.format("/webjars_locator/%s/", webjar);
      String mvcPath =
          (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
      String fullPath = LOCATOR.getFullPath(webjar, mvcPath.substring(mvcPrefix.length()));
      ClassPathResource classPathResource = new ClassPathResource(fullPath);

      return ResponseEntity.status(HttpStatus.OK) //
          .header(
              HttpHeaders.CONTENT_TYPE,
              Files.probeContentType(Path.of(classPathResource.getPath())))
          .cacheControl(CacheControl.maxAge(Duration.ofDays(7)))
          .body(classPathResource);

    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }
}
