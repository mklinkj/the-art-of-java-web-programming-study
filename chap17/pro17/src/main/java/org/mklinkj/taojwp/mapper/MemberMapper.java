package org.mklinkj.taojwp.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mklinkj.taojwp.sec01.ex01.MemberVO;

@Mapper
public interface MemberMapper {
  int addMember(MemberVO memberVO);

  List<MemberVO> listMembers();
}
