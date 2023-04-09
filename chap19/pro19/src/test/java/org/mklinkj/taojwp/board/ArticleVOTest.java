package org.mklinkj.taojwp.board;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
class ArticleVOTest {

  @Test
  void setOrGetImageFileName() {
    ArticleVO articleVO = new ArticleVO();
    articleVO.setImageFileName("한글이미지.png");

    LOGGER.info("articleVO: {}", articleVO);
    // 로거 출력에서는 디코딩된 값으로 나오긴 하는데 실제 필드에는 URL 인코딩 된 값으로 들어가 있다.
    // %ED%95%9C%EA%B8%80%EC%9D%B4%EB%AF%B8%EC%A7%80.png

    Assertions.assertThat(articleVO.getImageFileName()) //
        .isEqualTo("한글이미지.png");
  }
}
