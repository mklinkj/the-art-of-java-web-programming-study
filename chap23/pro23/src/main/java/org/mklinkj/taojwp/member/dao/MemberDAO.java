package org.mklinkj.taojwp.member.dao;

import java.util.List;
import org.mklinkj.taojwp.member.domain.MemberVO;
import org.mklinkj.taojwp.member.dto.SearchDTO;

public interface MemberDAO {

  List<MemberVO> selectAllMembers(SearchDTO searchDTO);

  int addMember(MemberVO memberVO);

  MemberVO findById(String id);

  int updateMember(MemberVO memberVO);

  int deleteMember(String id);

  // 23.5.1 `<if>` 태그로 동적 SQL문 만들기 쿼리 확인만...
  List<MemberVO> searchMember(MemberVO memberVO);

  // 23.5.3 `<foreach>` 태그로 회원 정보 조회하기
  List<MemberVO> foreachSelect(List<String> nameList);
}
