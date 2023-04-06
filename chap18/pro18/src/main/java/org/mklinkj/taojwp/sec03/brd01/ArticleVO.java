package org.mklinkj.taojwp.sec03.brd01;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("article")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleVO {
  private int level;
  private int articleNo;
  private int parentNo;
  private String title;
  private String content;
  private String imageFileName;
  private String id;
  private LocalDateTime writeDate;

  public String getImageFileName() {
    if (imageFileName != null && !imageFileName.isBlank()) {
      return URLDecoder.decode(imageFileName, StandardCharsets.UTF_8);
    }
    return imageFileName;
  }

  public void setImageFileName(String imageFileName) {
    this.imageFileName = URLEncoder.encode(imageFileName, StandardCharsets.UTF_8);
  }
}
