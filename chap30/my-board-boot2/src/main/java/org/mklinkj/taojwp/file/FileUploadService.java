package org.mklinkj.taojwp.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.mklinkj.taojwp.common.util.ProjectDataUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service
public class FileUploadService {
  private final String imageRepoPath = ProjectDataUtils.getProperty("image_repo_path");
  private final String uploadTempPath = ProjectDataUtils.getProperty("upload_temp_path");

  /**
   * 여러 이미지 파일 업로드 예제
   *
   * @param multipartRequest
   * @return 업로된 파일 파일 이름
   * @throws IOException
   */
  public List<String> multiImageFileUploadExample(MultipartHttpServletRequest multipartRequest)
      throws IOException {
    List<String> fileList = new ArrayList<>();
    // <input type=file>에 설정한 name 값
    Iterator<String> fileNames = multipartRequest.getFileNames();

    while (fileNames.hasNext()) {
      String fileName = fileNames.next();
      MultipartFile multipartFile = multipartRequest.getFile(fileName);
      // <input type=file>에서 실제 업로드한 파일명
      // 일반적으로는 파일 이름만 값이 들어오는데, Opera 의 경우는 경로까지 들어옴 (Paths.getFileName 처리를 했음.)
      // 고유한 파일 명을 생성하고 필요한 경우 이 파일 명을 참조할 수 있도록 어딘가 저장해두라고 함.. 😅
      // 고유한 파일 명 관련해서는 ... 여기선 일단 그냥 쓰자.. 완료를 하고 바꿔보는 것이 좋겠다.
      String originalFileName =
          Paths.get(multipartFile.getOriginalFilename()).getFileName().toString();
      fileList.add(originalFileName);

      File file = new File(imageRepoPath + File.separator + fileName);

      if (multipartFile.getSize() != 0) {
        if (!file.exists()) {
          if (file.getParentFile().mkdirs()) {
            file.createNewFile();
          }
        }
        multipartFile.transferTo(new File(imageRepoPath + File.separator + originalFileName));
      }
    }
    return fileList;
  }

  /*
   * 게시물 첨부 이미지 업로드
   *
   * 20장에서 했던 내용이 첨부파일 테이블을 따로 가지지 않는 구조여서...
   *
   * 1. 임시 경로로 파일 업로드
   * 2. Multipart 에서 얻은 파일명을 그대로 Article 에 포함하여 DB 저장
   * 3. 2.의 저장과정에서 게시물 번호 얻음
   * 4. 임시 경로에 저장한 파일을 "업로드 경로 / 게시물 번호 / 파일 명" 으로 이동 시킴
   *
   * 첨부파일 테이블을 만들어 관리하기 전까진 위처럼 해야한다. 😅
   *
   * 그려면 이 메서드의 역활은 그냥 임시 경로에 업로드 해두는 역활이 된다.
   */
  public List<AttachFile> uploadArticleAttachFile(MultipartHttpServletRequest multipartRequest)
      throws IOException {

    List<AttachFile> fileList = new ArrayList<>();
    Iterator<String> fileNames = multipartRequest.getFileNames();

    while (fileNames.hasNext()) {
      String fileName = fileNames.next();
      MultipartFile multipartFile = multipartRequest.getFile(fileName);
      String originalFileName =
          Paths.get(multipartFile.getOriginalFilename()).getFileName().toString();

      AttachFile attachFile =
          AttachFile.builder()
              .originalFileName(originalFileName)
              .uuid(UUID.randomUUID().toString())
              .build();

      fileList.add(attachFile);

      File file = new File(uploadTempPath + File.separator + attachFile.getTempFileName());

      if (multipartFile.getSize() != 0) {
        if (!file.exists()) {
          if (file.getParentFile().mkdirs()) {
            file.createNewFile();
          }
        }
        multipartFile.transferTo(file);
      }
    }
    return fileList;
  }
}
