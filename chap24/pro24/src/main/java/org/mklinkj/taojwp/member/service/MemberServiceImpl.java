package org.mklinkj.taojwp.member.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.mklinkj.taojwp.member.dao.MemberDAO;
import org.mklinkj.taojwp.member.domain.MemberVO;
import org.mklinkj.taojwp.member.dto.SearchDTO;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
  private final MemberDAO memberDAO;

  @Override
  public List<MemberVO> listMembers(SearchDTO searchDTO) {
    return memberDAO.selectAllMembers(searchDTO);
  }

  @Override
  public void addMember(MemberVO member) {
    memberDAO.addMember(member);
  }

  @Override
  public MemberVO getMember(String id) {
    return memberDAO.findById(id);
  }

  @Override
  public void updateMember(MemberVO member) {
    memberDAO.updateMember(member);
  }

  @Override
  public void delMember(String id) {
    memberDAO.deleteMember(id);
  }
}
