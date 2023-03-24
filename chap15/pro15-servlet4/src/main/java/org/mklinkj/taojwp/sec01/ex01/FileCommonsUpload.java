package org.mklinkj.taojwp.sec01.ex01;

import static org.mklinkj.taojwp.common.CommonUtils.fileNameOnly;
import static org.mklinkj.taojwp.common.Constants.MEGA_BYTE;
import static org.mklinkj.taojwp.common.Constants.UTF_8_ENCODING;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@Slf4j
@WebServlet("/commonsFileUpload.do")
public class FileCommonsUpload extends AbstractHttpServlet {

  private final UploadProps uploadProps;

  public FileCommonsUpload() {
    this(UploadProps.builder().build());
  }

  public FileCommonsUpload(UploadProps uploadProps) {
    this.uploadProps = uploadProps;
  }

  protected void doHandle(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    request.setCharacterEncoding(UTF_8_ENCODING);

    DiskFileItemFactory factory = new DiskFileItemFactory();

    factory.setSizeThreshold(uploadProps.threshold);
    // 설정을 하지 않으면 시스템 OS 임시 폴더경로에 저장을 할 텐데,
    // 최종 업로드 경로를 지정하는 것이 아니고 메모리 처리 한계를 넘늠 파일이 업로드 될 때,
    // 보조 기억 장치(HDD, SSD)에 임시 저장하는 경로 설정이다.
    // threshold를 1MB로 설정했으므로, 1MB 넘는 것은 임시 디렉토리에 저장되면서 처리될 수 있다.
    factory.setRepository(uploadProps.uploadTempDir.toFile());

    ServletFileUpload upload = new ServletFileUpload(factory);

    try {
      List<FileItem> items = upload.parseRequest(request);
      for (FileItem item : items) {
        if (item.isFormField()) {
          LOGGER.info("{}={}", item.getFieldName(), item.getString(UTF_8_ENCODING));
        } else {
          LOGGER.info("매개변수 이름: {}", item.getFieldName());
          LOGGER.info("파일 이름: {}", item.getName());
          LOGGER.info("파일 크기: {}bytes", item.getSize());

          if (item.getSize() > 0) {
            item.write(
                Path.of(
                        uploadProps.uploadDir.toString(),
                        UUID.randomUUID() + "__" + fileNameOnly(item.getName()))
                    .toFile());
          }
        }
      }
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
    }
    response.sendRedirect("test01/uploadForm.jsp");
  }

  @Getter
  @Builder
  @ToString
  public static class UploadProps {
    /** 파일이 디스크에 직접 기록되는 크기 임계값 */
    @Default public int threshold = MEGA_BYTE;

    /** 임시로 저장할 파일 경로 */
    @Default private Path uploadDir = Paths.get("C:\\upload\\art_of_java_web");

    @Default private Path uploadTempDir = Paths.get("C:\\upload\\art_of_java_web\\temp");
  }
}
