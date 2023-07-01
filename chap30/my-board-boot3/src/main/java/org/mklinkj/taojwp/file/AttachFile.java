package org.mklinkj.taojwp.file;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class AttachFile {
  private String originalFileName;

  private String uuid;

  public String getTempFileName() {
    return uuid + "_" + originalFileName;
  }
}
