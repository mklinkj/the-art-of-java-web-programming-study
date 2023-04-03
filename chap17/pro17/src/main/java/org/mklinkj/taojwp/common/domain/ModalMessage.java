package org.mklinkj.taojwp.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * 모달 팝업 도메인
 *
 * <p>record를 사용하면 JSP EL로 잃지 못하는 것 같다.
 */
@Getter
@Builder
@ToString
@AllArgsConstructor
public class ModalMessage {
  private final String title;
  private final String content;
}
