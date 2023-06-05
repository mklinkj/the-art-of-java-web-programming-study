package org.mklinkj.taojwp.security;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.mklinkj.taojwp.member.domain.MemberVO;
import org.mklinkj.taojwp.member.service.MemberService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final MemberService memberService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    MemberVO member = memberService.getMember(username);

    if (member == null) {
      throw new UsernameNotFoundException("해당 ID의 유저가 없습니다. (%s)".formatted(username));
    }

    return new User(member.getId(), member.getPwd(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
  }
}
