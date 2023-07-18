package org.mklinkj.taojwp.board.dao;

import java.util.List;
import org.mklinkj.taojwp.file.domain.AttachFile;

public interface AttachFileDAO {

  /**
   * 첨부 파일 정보 저장
   *
   * @param attachFileList 첨부파일 정보
   * @return 등록된 수
   */
  int insertAttachFile(List<AttachFile> attachFileList);

  /**
   * UUID로 첨부파일 정보 한개 조회
   *
   * @param uuid UUID
   * @return 첨부파일 정보
   */
  AttachFile findById(String uuid);

  /**
   * 게시글 번호로 해당 게시글에 연관된 첨부파일 정보들을 조회
   *
   * @param articleNo 게시글 번호
   * @return 첨부파일 정보 목록
   */
  List<AttachFile> findByArticleNo(Integer articleNo);

  /**
   * 게시글 번호로 해당 게시글에 연관된 첨부파일 정보들을 삭제
   *
   * @param articleNo 게시글 번호
   * @return 삭제된 첨부파일 정보 수
   */
  int deleteByArticleNo(Integer articleNo);

  /**
   * 게시글 번호들로 해당 게시글 들에 연관된 첨부파일 정보들을 삭제
   *
   * @param articleNoList 게시글 번호 목록
   */
  void deleteByArticleNoList(List<Integer> articleNoList);

  /**
   * UUID 목록으로 첨부파일 정보 삭제
   * @param uuidList UUID 목록
   */
  void deleteByUuidList(List<String> uuidList);
}
