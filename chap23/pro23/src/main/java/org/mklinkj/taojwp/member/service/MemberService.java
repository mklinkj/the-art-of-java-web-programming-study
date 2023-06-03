package org.mklinkj.taojwp.member.service;

import java.util.List;
import org.mklinkj.taojwp.member.domain.MemberVO;

public interface MemberService {
  List<MemberVO> listMembers();

  void addMember(MemberVO member);

  MemberVO getMember(String id);
}
