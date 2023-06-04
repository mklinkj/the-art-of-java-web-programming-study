package org.mklinkj.taojwp.member.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.common.util.DBDataInitializer;
import org.mklinkj.taojwp.member.domain.MemberVO;
import org.mklinkj.taojwp.member.dto.SearchDTO;
import org.mklinkj.taojwp.member.dto.SearchType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(locations = "classpath:config/action-dataSource.xml")
class MemberDAOImplTests {

  @Autowired private MemberDAO memberDAO;

  @Autowired private DBDataInitializer dbDataInitializer;

  @AfterEach
  void afterEach() {
    dbDataInitializer.resetDB();
  }

  @Test
  void testSelectAllMembers() {
    SearchDTO searchDTO =
        SearchDTO //
            .builder()
            .keyword("정션링크")
            .type(SearchType.NAME)
            .build();

    List<MemberVO> list = memberDAO.selectAllMembers(searchDTO);
    assertThat(list).isNotEmpty();

    assertThat(list.get(0)) //
        .hasFieldOrPropertyWithValue("name", "정션링크")
        .hasFieldOrPropertyWithValue("id", "mklinkj");
  }

  @Test
  void testAddMembers() {
    MemberVO member = new MemberVO();
    member.setId("jdbc");
    member.setName("제이");
    member.setPwd("1234");
    member.setEmail("jdbc@jdbc.org");

    int result = memberDAO.addMember(member);

    assertThat(result).isEqualTo(1);
  }

  @Test
  void testFindById() {
    MemberVO member = memberDAO.findById("mklinkj");
    assertThat(member) //
        .isNotNull()
        .hasFieldOrPropertyWithValue("id", "mklinkj");
  }

  @Test
  void testUpdateMember() {
    MemberVO member = new MemberVO();
    member.setId("mklinkj");
    member.setName("정션링크2");
    member.setPwd("4321");
    member.setEmail("mklinkj2@github.com");

    int result = memberDAO.updateMember(member);

    assertThat(result).isEqualTo(1);
  }

  @Test
  void testDeleteMember() {
    String id = "mklinkj";
    int result = memberDAO.deleteMember(id);
    assertThat(result).isEqualTo(1);
  }

  @Test
  void testSearchMember() {

    MemberVO member = new MemberVO();
    member.setName("정션링크");
    member.setEmail("mklinkj@github.com");

    List<MemberVO> result = memberDAO.searchMember(member);

    assertThat(result).isNotEmpty();

    assertThat(result.get(0)) //
        .hasFieldOrPropertyWithValue("name", "정션링크")
        .hasFieldOrPropertyWithValue("email", "mklinkj@github.com");
  }

  @Test
  void testForeachSelect() {

    List<String> nameList = List.of("정션링크", "최치원", "이순신");

    List<MemberVO> result = memberDAO.foreachSelect(nameList);

    assertThat(result).isNotEmpty().hasSize(3);
  }
}
