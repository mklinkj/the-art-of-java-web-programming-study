package org.mklinkj.taojwp.file.domain;

import org.apache.ibatis.type.Alias;

/**
 * 첨부파일 타입
 *
 * <p>간편하게 MyBatis가 기본으로 핸들링 해주는대로 사용하기 위해 <br>
 * 일단은 DB에 저장할 값을 그대로 열거형의 이름으로 사용하자
 */
@Alias("fileType")
public enum FileType {
  I, // 이미지 파일 타입
  F // 일반 파일 타입
}
