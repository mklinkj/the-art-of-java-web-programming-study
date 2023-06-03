package org.mklinkj.taojwp.member.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.mklinkj.taojwp.member.dao.MemberDAO;
import org.mklinkj.taojwp.member.domain.MemberVO;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
  private final MemberDAO memberDAO;

  @Override
  public List<MemberVO> listMembers() {
    return memberDAO.selectAllMembers();
  }

  @Override
  public void addMember(MemberVO member) {
    memberDAO.addMember(member);
  }

  @Override
  public MemberVO getMember(String id) {
    return memberDAO.findById(id);
  }
}
