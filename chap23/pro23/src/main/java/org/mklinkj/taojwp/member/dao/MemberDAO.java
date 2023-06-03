package org.mklinkj.taojwp.member.dao;

import java.util.List;
import org.mklinkj.taojwp.member.domain.MemberVO;

public interface MemberDAO {

  List<MemberVO> selectAllMembers();

  int addMember(MemberVO memberVO);

  MemberVO findById(String id);
}
