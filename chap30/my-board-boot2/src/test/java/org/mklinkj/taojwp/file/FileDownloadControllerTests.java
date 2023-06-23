package org.mklinkj.taojwp.file;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.File;
import java.nio.file.Files;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@Slf4j
class FileDownloadControllerTests {

  private final String IMAGE_FILE_NAME = "image_file_01.png";

  private final String UPLOAD_PATH_FORMAT = "/upload_test_file/%s";

  @TempDir private File tempDir;

  @Autowired private FileDownloadController fileDownloadController;

  @Autowired private WebApplicationContext context;

  private MockMvc mockMvc;

  @BeforeEach
  void beforeEach() {
    mockMvc = webAppContextSetup(context).apply(springSecurity()).build();
    LOGGER.info("### tempDir: {}", tempDir);
    ReflectionTestUtils.setField(
        fileDownloadController, "imageRepoPath", tempDir.getAbsolutePath());
  }

  @Test
  void testDownload() throws Exception {
    initUploadFile();

    mockMvc
        .perform(
            get("/file/download") //
                .param("imageFileName", IMAGE_FILE_NAME)) //
        .andDo(print())
        .andExpect(
            result -> {
              assertThat(result.getResponse().getStatus()) //
                  .isEqualTo(HttpStatus.OK.value());
              assertThat(result.getResponse().getContentType()) //
                  .isNotEqualTo(MediaType.IMAGE_PNG_VALUE)
                  .describedAs("컨트롤러 메서드에서 명시적으로 설정을 하지 않아서 null");
              assertThat(result.getResponse().getHeader(HttpHeaders.CONTENT_DISPOSITION)) //
                  .isEqualTo("attachment; fileName=%s".formatted(IMAGE_FILE_NAME));
            });
  }

  @Test
  void testDownloadThumbnailFile() throws Exception {
    initUploadFile();

    mockMvc
        .perform(get("/file/downloadThumbnail/%s".formatted(IMAGE_FILE_NAME)))
        .andDo(print())
        .andExpect(
            result -> {
              assertThat(result.getResponse().getStatus()) //
                  .isEqualTo(HttpStatus.OK.value());
              assertThat(result.getResponse().getContentType()) //
                  .isNotEqualTo(MediaType.IMAGE_PNG_VALUE)
                  .describedAs("컨트롤러 메서드에서 명시적으로 설정을 하지 않아서 null");
            });
  }

  @Test
  void testDownloadThumbnailOutStream() throws Exception {
    initUploadFile();

    mockMvc
        .perform(get("/file/downloadThumbnailOut/%s".formatted(IMAGE_FILE_NAME)))
        .andDo(print())
        .andExpect(
            result -> {
              assertThat(result.getResponse().getStatus()) //
                  .isEqualTo(HttpStatus.OK.value());
              assertThat(result.getResponse().getContentType()) //
                  .isNotEqualTo(MediaType.IMAGE_PNG_VALUE)
                  .describedAs("컨트롤러 메서드에서 명시적으로 설정을 하지 않아서 null");
            });
  }

  /** 임시 디렉토리에 업로드 파일 초기화 */
  private void initUploadFile() throws Exception {
    ClassPathResource imageResource =
        new ClassPathResource(UPLOAD_PATH_FORMAT.formatted(IMAGE_FILE_NAME));

    File imageFile = new File(tempDir, IMAGE_FILE_NAME);

    Files.copy(imageResource.getFile().toPath(), imageFile.toPath());
  }
}
