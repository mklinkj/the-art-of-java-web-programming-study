package org.mklinkj.taojwp.member;

import java.util.List;
import java.util.Optional;

public interface MemberDAO {
  List<MemberVO> listMembers();

  int addMember(MemberVO memberVO);

  Optional<MemberVO> findMember(String id);

  Optional<MemberVO> findMemberWithPassword(String id, String password);

  int modMember(MemberVO memberVO);

  int delMember(String id);
}
