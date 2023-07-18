package org.mklinkj.taojwp.file.service;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.mklinkj.taojwp.board.dao.AttachFileDAO;
import org.mklinkj.taojwp.common.util.ProjectDataUtils;
import org.mklinkj.taojwp.file.domain.AttachFile;
import org.mklinkj.taojwp.file.domain.FileType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@Service
public class FileService {
  private final String imageRepoPath = ProjectDataUtils.getProperty("image_repo_path");
  private final String uploadTempPath = ProjectDataUtils.getProperty("upload_temp_path");

  private final String uploadPath = ProjectDataUtils.getProperty("upload_path");

  private final AttachFileDAO attachFileDAO;

  /**
   * 여러 이미지 파일 업로드 예제
   *
   * @param multipartRequest 멀티파트 요청
   * @return 업로된 파일 파일 이름
   * @throws IOException IOException
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
  public void uploadArticleAttachFile(List<MultipartFile> multipartFileList, Integer articleNo)
      throws IOException {

    List<AttachFile> attachFileList = new ArrayList<>(multipartFileList.size());

    for (MultipartFile multipartFile : multipartFileList) {
      if (multipartFile.getOriginalFilename().isBlank() || multipartFile.getSize() == 0) {
        continue;
      }

      AttachFile attachFile =
          AttachFile.builder()
              .uuid(UUID.randomUUID().toString())
              .fileName(multipartFile.getOriginalFilename())
              .fileType(FileType.I) // TODO: 기능을 완료하기 전까진 기본 이미지로 간주.
              .articleNo(articleNo)
              .build();
      attachFileList.add(attachFile);

      File file = new File(uploadTempPath + File.separator + attachFile.getStoredFileName());

      if (!file.exists()) {
        if (file.getParentFile().mkdirs()) {
          file.createNewFile();
        }
      }
      multipartFile.transferTo(file);
    }

    if (attachFileList.isEmpty()) {
      return;
    }

    attachFileDAO.insertAttachFile(attachFileList);

    for (AttachFile attachFile : attachFileList) {
      File srcFile = new File(uploadTempPath + File.separator + attachFile.getStoredFileName());
      File destDir = new File(uploadPath + File.separator + articleNo);
      destDir.mkdirs();
      File destFile =
          new File(
              uploadPath
                  + File.separator
                  + articleNo
                  + File.separator
                  + attachFile.getStoredFileName());
      Files.move(srcFile.toPath(), destFile.toPath(), REPLACE_EXISTING);
    }
  }

  public void removeAttachFile(List<Integer> articleNoList) throws IOException {
    for (int removedArticleNo : articleNoList) {
      File imageDir = new File(uploadPath + File.separator + removedArticleNo);
      FileUtils.deleteDirectory(imageDir);
    }
    attachFileDAO.deleteByArticleNoList(articleNoList);
  }

  public void removeAttachFileByUuid(List<String> uuidList, Integer articleNo) {
    List<AttachFile> fileInfoList = attachFileDAO.findByArticleNo(articleNo);

    Map<String, AttachFile> dbUuidMap =
        fileInfoList.stream().collect(Collectors.toMap(AttachFile::getUuid, Function.identity()));

    List<String> validUuidList =
        uuidList.stream().filter(requestUuid -> dbUuidMap.keySet().contains(requestUuid)).toList();

    for (String uuid : validUuidList) {
      try {
        File imageFile =
            new File(
                uploadPath
                    + File.separator
                    + articleNo
                    + File.separator
                    + uuid
                    + "."
                    + dbUuidMap.get(uuid).getExtension());
        if (imageFile.exists()) {
          FileUtils.delete(imageFile);
        } else {
          LOGGER.warn("파일이 저장소에 없음: 게시물번호: {}, 첨부파일 UUID: {}", articleNo, uuid);
        }

      } catch (IOException ioe) {
        // 개별 파일 삭제할 때... 실패하더라도 진행이 멈추게 하진 말자.
        LOGGER.error("파일 삭제 오류: 게시물번호: {}, 첨부파일 UUID: {}", articleNo, uuid, ioe);
      }
    }

    if (!validUuidList.isEmpty()) {
      attachFileDAO.deleteByUuidList(validUuidList);
    }
  }

  /**
   * UUID 확장자 형태의 파일명으로 부터 원본 이름을 얻는 메서드
   *
   * @param uuidFileName "uuid.확장자" 형태의 파일명
   * @return 원본 이름
   */
  public String getOriginalFileName(String uuidFileName) {
    String uuid = FilenameUtils.getBaseName(uuidFileName);
    AttachFile attachFile = attachFileDAO.findById(uuid);

    if (attachFile == null) {
      return null;
    } else {
      return attachFile.getFileName();
    }
  }

  /**
   * 특정 게시물의 첨부파일 목록 가져오기
   *
   * @param articleNo 게시물 번호
   */
  public List<AttachFile> getAttachFileList(Integer articleNo) {
    return attachFileDAO.findByArticleNo(articleNo);
  }
}
