package org.mklinkj.taojwp.file;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.File;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@Slf4j
class FileUploadControllerTests {

  private final String IMAGE_FILE_01_NAME = "image_file_01.png";
  private final String IMAGE_FILE_02_NAME = "image_file_02.png";

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

  /*
   FlashMap:
     Attribute = map
       value = {_csrf=d0b01138-8d61-47ba-92b1-0b6cd0836cdd, fileList=[image_file_01.png, image_file_02.png]}

    플래시 맵 로깅으로도 잘 확인할 수 있다.
    첨부된 파일명 확인까지 테스트 코드로 나타내기는 너무 복잡해질 것 같아서 하지 않았다.
  */
  @Test
  void testUpload() throws Exception {
    EncodedResource imageFile01 =
        new EncodedResource(
            new ClassPathResource(UPLOAD_PATH_FORMAT.formatted(IMAGE_FILE_01_NAME)),
            StandardCharsets.UTF_8);
    MockMultipartFile multipartFile01 =
        new MockMultipartFile(
            "file1", IMAGE_FILE_01_NAME, MediaType.IMAGE_PNG_VALUE, imageFile01.getInputStream());

    ClassPathResource imageFile02 =
        new ClassPathResource(UPLOAD_PATH_FORMAT.formatted(IMAGE_FILE_02_NAME));
    MockMultipartFile multipartFile02 =
        new MockMultipartFile(
            "file2", IMAGE_FILE_02_NAME, MediaType.IMAGE_PNG_VALUE, imageFile02.getInputStream());

    mockMvc
        .perform(
            multipart("/file/upload") //
                .file(multipartFile01)
                .file(multipartFile02)
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .with(csrf()))
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(redirectedUrl("result"))
        .andExpect(flash().attributeExists("map"));
  }
}
