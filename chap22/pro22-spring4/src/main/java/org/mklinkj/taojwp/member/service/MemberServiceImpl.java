package org.mklinkj.taojwp.member.service;

import java.util.List;
import lombok.Setter;
import org.mklinkj.taojwp.member.dao.MemberDAO;
import org.mklinkj.taojwp.member.domain.MemberVO;

public class MemberServiceImpl implements MemberService {
  @Setter private MemberDAO memberDAO;

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
