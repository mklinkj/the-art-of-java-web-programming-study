package org.mklinkj.taojwp.board.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.common.util.DBDataInitializer;
import org.mklinkj.taojwp.file.domain.AttachFile;
import org.mklinkj.taojwp.file.domain.FileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class AttachFileDAOTests {
  @Autowired private DBDataInitializer dbDataInitializer;

  @Autowired private AttachFileDAO attachFileDAO;

  private static final String TEST_UUID = "bf0890ec-3a79-4f63-a560-63e84ed4dcad";

  @BeforeEach
  void beforeEach() {
    dbDataInitializer.resetDB();
  }

  private List<AttachFile> makeAttachFileList(Integer articleNo, int fileCount) {
    return IntStream.rangeClosed(1, fileCount)
        .mapToObj(
            n ->
                AttachFile.builder() //
                    .uuid(UUID.randomUUID().toString())
                    .fileName("file_name_%d.png".formatted(n))
                    .fileType(FileType.I)
                    .articleNo(articleNo)
                    .build())
        .toList();
  }

  @Test
  void insertArticleFile() {
    int insertRowCount = attachFileDAO.insertAttachFile(makeAttachFileList(10, 3));
    assertThat(insertRowCount).isEqualTo(3);
  }

  @Test
  void findById() {
    AttachFile attachFile = attachFileDAO.findById(TEST_UUID);

    assertThat(attachFile)
        .isNotNull() //
        .hasFieldOrPropertyWithValue("uuid", TEST_UUID);
  }

  @Test
  void findByArticleNo() {
    List<AttachFile> list = attachFileDAO.findByArticleNo(20);
    assertThat(list).isNotEmpty().hasSize(3);
  }

  @Test
  void deleteByArticleNo() {
    int deleteRowCount = attachFileDAO.deleteByArticleNo(30);
    assertThat(deleteRowCount).isEqualTo(3);
  }

  @Test
  void deleteByArticleNoList() {
    attachFileDAO.deleteByArticleNoList(List.of(1, 2, 3));
  }
}
