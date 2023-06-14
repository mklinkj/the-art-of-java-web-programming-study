package org.mklinkj.taojwp.member.webjars;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.webjars.WebJarAssetLocator;

@Slf4j
class WebJarsTests {
  @Test
  void test() {
    WebJarAssetLocator LOCATOR = new WebJarAssetLocator();

    Map<String, String> webjars = LOCATOR.getWebJars();
    LOGGER.info(webjars.toString());

    String css = LOCATOR.getFullPath("bootstrap", "css/bootstrap.min.css");
    LOGGER.info(css);
  }
}
