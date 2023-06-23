package org.mklinkj.taojwp.member.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.member.domain.MemberVO;
import org.mklinkj.taojwp.member.dto.SearchDTO;
import org.mklinkj.taojwp.member.dto.SearchType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemberMapperImplTests {

  @Autowired private MemberMapper memberMapper;

  @Test
  void testSelectAllMembers() {
    SearchDTO searchDTO =
        SearchDTO //
            .builder()
            .keyword("정션링크")
            .type(SearchType.NAME)
            .build();

    List<MemberVO> list = memberMapper.selectAllMembers(searchDTO);
    assertThat(list).isNotEmpty();

    assertThat(list.get(0)) //
        .hasFieldOrPropertyWithValue("name", "정션링크")
        .hasFieldOrPropertyWithValue("id", "mklinkj");
  }

  @Transactional
  @Rollback
  @Test
  void testAddMembers() {
    MemberVO member = new MemberVO();
    member.setId("jdbc");
    member.setName("제이");
    member.setPwd("1234");
    member.setEmail("jdbc@jdbc.org");

    int result = memberMapper.addMember(member);

    assertThat(result).isEqualTo(1);
  }

  @Test
  void testFindById() {
    MemberVO member = memberMapper.findById("mklinkj");
    assertThat(member) //
        .isNotNull()
        .hasFieldOrPropertyWithValue("id", "mklinkj");
  }

  @Transactional
  @Rollback
  @Test
  void testUpdateMember() {
    MemberVO member = new MemberVO();
    member.setId("mklinkj");
    member.setName("정션링크2");
    member.setPwd("4321");
    member.setEmail("mklinkj2@github.com");

    int result = memberMapper.updateMember(member);

    assertThat(result).isEqualTo(1);
  }

  @Transactional
  @Rollback
  @Test
  void testDeleteMember() {
    String id = "mklinkj";
    int result = memberMapper.deleteMember(id);
    assertThat(result).isEqualTo(1);
  }

  @Test
  void testSearchMember() {

    MemberVO member = new MemberVO();
    member.setName("정션링크");
    member.setEmail("mklinkj@github.com");

    List<MemberVO> result = memberMapper.searchMember(member);

    assertThat(result).isNotEmpty();

    assertThat(result.get(0)) //
        .hasFieldOrPropertyWithValue("name", "정션링크")
        .hasFieldOrPropertyWithValue("email", "mklinkj@github.com");
  }

  @Test
  void testForeachSelect() {
    List<String> nameList = List.of("정션링크", "최치원", "이순신");
    List<MemberVO> result = memberMapper.foreachSelect(nameList);
    assertThat(result).isNotEmpty().hasSize(3);
  }

  @Transactional
  @Rollback
  @Test
  void testForeachInsert() {
    List<MemberVO> memberList =
        List.of(
            MemberVO.builder()
                .id("mklinkj01")
                .pwd("1234")
                .name("정션링크01")
                .email("mklinkj01@github.com")
                .build(),
            MemberVO.builder()
                .id("mklinkj02")
                .pwd("1234")
                .name("정션링크02")
                .email("mklinkj02@github.com")
                .build(),
            MemberVO.builder()
                .id("mklinkj03")
                .pwd("1234")
                .name("정션링크03")
                .email("mklinkj03@github.com")
                .build());

    int updateCount = memberMapper.foreachInsert(memberList);
    assertThat(updateCount).isEqualTo(memberList.size());
  }
}
