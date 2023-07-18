package org.mklinkj.taojwp.file.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.type.Alias;

@Alias("attachFile")
@Getter
@Setter
@Builder
@ToString
public class AttachFile {
  private String uuid;

  private String fileName;

  private FileType fileType;

  private LocalDateTime registerDate;

  private Integer articleNo;

  // 파일이름을 따로 DB로 관리하므로, 파일 시스템에 저장은 uuid 값으로 저장을 하되 확장자는 원본 내용 유지하게 해보자.
  public String getStoredFileName() {
    return uuid + (getExtension().isBlank() ? "" : "." + getExtension());
  }

  public String getExtension() {
    return FilenameUtils.getExtension(fileName);
  }
}
