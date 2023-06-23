package org.mklinkj.taojwp.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mklinkj.taojwp.member.domain.MemberVO;
import org.mklinkj.taojwp.member.service.MemberService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final MemberService memberService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    LOGGER.info("### 로그인 유저 {} ###", username);
    MemberVO member = memberService.getMember(username);

    if (member == null) {
      throw new UsernameNotFoundException(String.format("해당 ID의 유저가 없습니다. (%s)", username));
    }

    return User.builder().username(member.getId()).password(member.getPwd()).roles("USER").build();
  }
}
