package org.mklinkj.taojwp.member.repository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.mklinkj.taojwp.member.domain.MemberVO;
import org.mklinkj.taojwp.member.dto.SearchDTO;
import org.mklinkj.taojwp.member.mapper.MemberMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepository implements MemberMapper {
  private final MemberMapper memberMapper;

  @Override
  public List<MemberVO> selectAllMembers(SearchDTO searchDTO) {
    return memberMapper.selectAllMembers(searchDTO);
  }

  @Override
  public int addMember(MemberVO memberVO) {
    return memberMapper.addMember(memberVO);
  }

  @Override
  public MemberVO findById(String id) {
    return memberMapper.findById(id);
  }

  @Override
  public int updateMember(MemberVO memberVO) {
    return memberMapper.updateMember(memberVO);
  }

  @Override
  public int deleteMember(String id) {
    return memberMapper.deleteMember(id);
  }

  @Override
  public List<MemberVO> searchMember(MemberVO memberVO) {
    return memberMapper.searchMember(memberVO);
  }

  @Override
  public List<MemberVO> foreachSelect(List<String> nameList) {
    return memberMapper.foreachSelect(nameList);
  }

  @Override
  public int foreachInsert(List<MemberVO> memberList) {
    return memberMapper.foreachInsert(memberList);
  }
}
