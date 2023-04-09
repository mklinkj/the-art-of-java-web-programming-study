package org.mklinkj.taojwp.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.mklinkj.taojwp.member.MemberVO;

@Mapper
public interface MemberMapper {
  int addMember(MemberVO memberVO);

  List<MemberVO> listMembers();

  MemberVO findMember(String id);

  int modMember(MemberVO memberVO);

  int delMember(String id);
}
