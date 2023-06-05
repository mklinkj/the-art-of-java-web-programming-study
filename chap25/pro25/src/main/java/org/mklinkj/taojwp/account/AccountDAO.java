package org.mklinkj.taojwp.account;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountDAO {
  void uploadBalance1();

  void uploadBalance2();

  List<AccountVO> selectAll();

  void resetAllBalance();

}
