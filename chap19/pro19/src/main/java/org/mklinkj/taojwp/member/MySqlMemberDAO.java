package org.mklinkj.taojwp.member;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.mklinkj.taojwp.mapper.MySqlMemberMapper;

/** MyBatis 추가해보자! */
@RequiredArgsConstructor
public class MySqlMemberDAO implements MemberDAO {

  private final MySqlMemberMapper memberMapper;

  public List<MemberVO> listMembers() {
    return memberMapper.listMembers();
  }

  public int addMember(MemberVO memberVO) {
    return memberMapper.addMember(memberVO);
  }

  public Optional<MemberVO> findMember(String id) {
    return Optional.ofNullable(memberMapper.findMember(id));
  }

  public Optional<MemberVO> findMemberWithPassword(String id, String password) {
    return Optional.ofNullable(memberMapper.findMemberWithPassword(id, password));
  }

  public int modMember(MemberVO memberVO) {

    return memberMapper.modMember(memberVO);
  }

  public int delMember(String id) {

    return memberMapper.delMember(id);
  }
}
