package org.mklinkj.taojwp.member;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.mklinkj.taojwp.mapper.MemberMapper;
import org.springframework.stereotype.Repository;

/** MyBatis 추가해보자! */
@Repository
@RequiredArgsConstructor
public class MemberDAO {

  private final MemberMapper memberMapper;

  public List<MemberVO> listMembers() {
    return memberMapper.listMembers();
  }

  public int addMember(MemberVO memberVO) {
    return memberMapper.addMember(memberVO);
  }

  public Optional<MemberVO> findMember(String id) {
    return Optional.ofNullable(memberMapper.findMember(id));
  }

  public int modMember(MemberVO memberVO) {

    return memberMapper.modMember(memberVO);
  }

  public int delMember(String id) {

    return memberMapper.delMember(id);
  }
}
