package org.mklinkj.taojwp.member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.common.util.DBDataInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(locations = "classpath:application-context.xml")
class MemberDaoTest {
  @Autowired private MemberDAO dao;

  @Autowired private DBDataInitializer dbDataInitializer;

  @BeforeEach
  void beforeEach() {
    dbDataInitializer.resetDB();
  }

  @Test
  void listMembers() {
    List<MemberVO> list = dao.listMembers();
    assertThat(list).isNotEmpty();
  }

  @Test
  void addMember() {
    MemberVO member =
        MemberVO.builder() //
            .id("user00")
            .name("사용자00")
            .pwd("1234")
            .email("user00@test.com")
            .build();

    assertThat(dao.addMember(member)).isEqualTo(1);
  }

  @Test
  void findMember() {
    Optional<MemberVO> result = dao.findMember("mklinkj");
    MemberVO member = result.orElseThrow();

    assertThat(member).hasFieldOrPropertyWithValue("id", "mklinkj");
  }

  @Test
  void findMember_Not_exist() {
    Optional<MemberVO> result = dao.findMember("mklinkj999");

    assertThatThrownBy(result::orElseThrow) //
        .isInstanceOf(NoSuchElementException.class);
  }

  @Test
  void modMember() {
    MemberVO memberVO =
        MemberVO.builder() //
            .id("mklinkj")
            .name("접합링크")
            .pwd("4321")
            .email("mklinkj@test.com")
            .build();
    int updatedCount = dao.modMember(memberVO);
    assertThat(updatedCount).isEqualTo(1);
  }

  @Test
  void delMember() {
    int deletedCount = dao.delMember("choi");
    assertThat(deletedCount).isEqualTo(1);
  }
}
