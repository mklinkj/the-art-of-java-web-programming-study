package org.mklinkj.taojwp.member.service;

import java.util.List;
import org.mklinkj.taojwp.member.domain.MemberVO;
import org.mklinkj.taojwp.member.dto.SearchDTO;

public interface MemberService {
  List<MemberVO> listMembers(SearchDTO dto);

  void addMember(MemberVO member);

  MemberVO getMember(String id);
}
