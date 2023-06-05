package org.mklinkj.taojwp.member.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.mklinkj.taojwp.member.domain.MemberVO;
import org.mklinkj.taojwp.member.dto.SearchDTO;
import org.mklinkj.taojwp.member.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
  private final MemberRepository memberRepository;

  private final PasswordEncoder passwordEncoder;

  @Override
  public List<MemberVO> listMembers(SearchDTO searchDTO) {
    return memberRepository.selectAllMembers(searchDTO);
  }

  @Override
  public void addMember(MemberVO member) {
    member.setPwd(passwordEncoder.encode(member.getPwd()));
    memberRepository.addMember(member);
  }

  @Override
  public MemberVO getMember(String id) {
    return memberRepository.findById(id);
  }

  @Override
  public void updateMember(MemberVO member) {
    memberRepository.updateMember(member);
  }

  @Override
  public void delMember(String id) {
    memberRepository.deleteMember(id);
  }
}
