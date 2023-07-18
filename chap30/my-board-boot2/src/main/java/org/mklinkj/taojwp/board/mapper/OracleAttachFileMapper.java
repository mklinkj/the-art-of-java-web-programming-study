package org.mklinkj.taojwp.board.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.mklinkj.taojwp.file.domain.AttachFile;

@Mapper
public interface OracleAttachFileMapper {
  int insertAttachFile(List<AttachFile> attachFileList);

  AttachFile findById(String uuid);

  List<AttachFile> findByArticleNo(Integer articleNo);

  int deleteByArticleNo(Integer articleNo);

  void deleteByArticleNoList(List<Integer> articleNoList);

  void deleteByUuidList(List<String> uuidList);
}
