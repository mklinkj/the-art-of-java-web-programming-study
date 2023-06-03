package org.mklinkj.taojwp.member.dao;

import java.sql.ResultSet;
import java.util.List;
import javax.sql.DataSource;
import org.mklinkj.taojwp.member.domain.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO {
  private final JdbcTemplate jdbcTemplate;

  public MemberDAOImpl(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  private RowMapper<MemberVO> getRowMapper() {
    return (ResultSet rs, int rowNum) -> {
      MemberVO memberVO = new MemberVO();
      memberVO.setId(rs.getString("id"));
      memberVO.setPwd(rs.getString("pwd"));
      memberVO.setName(rs.getString("name"));
      memberVO.setEmail(rs.getString("email"));
      memberVO.setJoinDate(rs.getDate("join_date").toLocalDate());
      return memberVO;
    };
  }

  @Override
  public List<MemberVO> selectAllMembers() {
    String query =
        """
        SELECT id
             , pwd
             , name
             , email
             , join_date
          FROM t_member
         ORDER BY join_date DESC
        """;

    return jdbcTemplate.query(query, getRowMapper());
  }

  @Override
  public int addMember(MemberVO memberVO) {
    String id = memberVO.getId();
    String pwd = memberVO.getPwd();
    String name = memberVO.getName();
    String email = memberVO.getEmail();
    String query =
        """
        INSERT INTO t_member (id, pwd, name, email)
        VALUES (?, ?, ?, ?)
        """;
    return jdbcTemplate.update(query, id, pwd, name, email);
  }

  @Override
  public MemberVO findById(String id) {
    String query =
        """
            SELECT id
                 , pwd
                 , name
                 , email
                 , join_date
              FROM t_member
             WHERE id = ?
            """;
    return jdbcTemplate.queryForObject(query, getRowMapper(), id);
  }
}
