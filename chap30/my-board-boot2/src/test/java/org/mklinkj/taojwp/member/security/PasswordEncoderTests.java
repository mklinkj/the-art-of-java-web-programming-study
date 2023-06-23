package org.mklinkj.taojwp.member.security;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
class PasswordEncoderTests {
  private final PasswordEncoder passwordEncoder =
      PasswordEncoderFactories.createDelegatingPasswordEncoder();

  @Test
  void test() {
    String encodedPassword = passwordEncoder.encode("1234");
    // {bcrypt}$2a$10$x.jjGAH0ejiHcWnOtT.fh.7DIST36OLKLHucPkaSrh4/aczSZpAbu
    // 암호화된 값은 계속 랜덤으로 바뀐다. 바뀌더라도 raw 패스워드를 통해 패스워드가 올바름을 확인할 수 있음.
    LOGGER.info(encodedPassword);
    encodedPassword = passwordEncoder.encode("1212");
    // {bcrypt}$2a$10$gjrfhS0Hht9nD6IFaP1.EuZi7wUROvgsw0UxUvQw66uRPk1svxMEm
    LOGGER.info(encodedPassword);
  }
}
