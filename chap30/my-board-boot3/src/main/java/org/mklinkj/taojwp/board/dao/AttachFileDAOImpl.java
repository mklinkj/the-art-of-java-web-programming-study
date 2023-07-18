package org.mklinkj.taojwp.board.dao;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.mklinkj.taojwp.board.mapper.OracleAttachFileMapper;
import org.mklinkj.taojwp.file.domain.AttachFile;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class AttachFileDAOImpl implements AttachFileDAO {

  private final OracleAttachFileMapper attachFileMapper;

  @Override
  public int insertAttachFile(List<AttachFile> attachFileList) {
    return attachFileMapper.insertAttachFile(attachFileList);
  }

  @Override
  public AttachFile findById(String uuid) {
    return attachFileMapper.findById(uuid);
  }

  @Override
  public List<AttachFile> findByArticleNo(Integer articleNo) {
    return attachFileMapper.findByArticleNo(articleNo);
  }

  @Override
  public int deleteByArticleNo(Integer articleNo) {
    return attachFileMapper.deleteByArticleNo(articleNo);
  }

  @Override
  public void deleteByArticleNoList(List<Integer> articleNoList) {
    attachFileMapper.deleteByArticleNoList(articleNoList);
  }

  @Override
  public void deleteByUuidList(List<String> uuidList) {
    attachFileMapper.deleteByUuidList(uuidList);
  }
}
