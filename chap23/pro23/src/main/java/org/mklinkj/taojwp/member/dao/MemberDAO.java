package org.mklinkj.taojwp.member.dao;

import java.util.List;
import org.mklinkj.taojwp.member.domain.MemberVO;
import org.mklinkj.taojwp.member.dto.SearchDTO;

public interface MemberDAO {

  List<MemberVO> selectAllMembers(SearchDTO searchDTO);

  int addMember(MemberVO memberVO);

  MemberVO findById(String id);

  int updateMember(MemberVO memberVO);
}
