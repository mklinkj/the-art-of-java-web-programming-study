package org.mklinkj.taojwp.sec02.ex01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.mklinkj.taojwp.common.DBUtils;

@Slf4j
public class MemberDAO {
  private PreparedStatement psmt;
  private Connection con;

  private final DataSource dataFactory;

  public MemberDAO() {
    try {
      dataFactory = DBUtils.getDataSourceFromJNDI();
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

  public List<MemberVO> listMembers(MemberVO memberVO) {
    List<MemberVO> list = new ArrayList<>();
    String _name = memberVO.getName();
    try {
      con = dataFactory.getConnection();
      String query = "SELECT id, pwd, name, email, joinDate FROM t_member";

      if(_name == null || _name.isBlank()) {
        psmt = con.prepareStatement(query);
      } else {
        query = query.concat(" WHERE name=?");
        psmt = con.prepareStatement(query);
        psmt.setString(1, _name);
      }
      LOGGER.info("query: {}", query);
      ResultSet rs = psmt.executeQuery();

      while (rs.next()) {
        String id = rs.getString("id");
        String pwd = rs.getString("pwd");
        String name = rs.getString("name");
        String email = rs.getString("email");
        LocalDateTime joinDate = rs.getTimestamp("joinDate").toLocalDateTime();

        MemberVO vo = new MemberVO();
        vo.setId(id);
        vo.setPwd(pwd);
        vo.setName(name);
        vo.setEmail(email);
        vo.setJoinDate(joinDate);

        list.add(vo);
      }
      rs.close();
      psmt.close();
      con.close();

    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
    }
    return list;
  }

  public void addMember(MemberVO vo) {
    try {
      con = dataFactory.getConnection();

      String id = vo.getId();
      String pwd = vo.getPwd();
      String name = vo.getName();
      String email = vo.getEmail();

      String query =
          """
          INSERT INTO t_member (id, pwd, name, email)
          VALUES (?, ?, ? ,?)
          """;
      LOGGER.info(query);

      psmt = con.prepareStatement(query);
      psmt.setString(1, id);
      psmt.setString(2, pwd);
      psmt.setString(3, name);
      psmt.setString(4, email);

      psmt.executeUpdate();
      psmt.close();
      con.close();
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
    }
  }

  public int delMember(String id) {
    try {
      con = dataFactory.getConnection();

      String query = "DELETE FROM t_member WHERE id = ?";

      LOGGER.info(query);

      psmt = con.prepareStatement(query);
      psmt.setString(1, id);

      return psmt.executeUpdate();
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
    } finally {
      try {
        psmt.close();
        con.close();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
    return 0;
  }

  public boolean isExisted(MemberVO memberVO) {
    boolean result;
    LOGGER.info("isExisted memberVo{}", memberVO);
    String id = memberVO.getId();
    String pwd = memberVO.getPwd();

    try {
      con = dataFactory.getConnection();

      String query =
          """
          SELECT DECODE(COUNT(*), 1, 'true', 'false') AS result
            FROM t_member
           WHERE id = ?
             AND pwd = ?
          """;
      psmt = con.prepareStatement(query);
      psmt.setString(1, id);
      psmt.setString(2, pwd);

      ResultSet rs = psmt.executeQuery();
      rs.next(); // 커서를 첫번째 레코드로 위치 시

      result = Boolean.parseBoolean(rs.getString("result"));
      LOGGER.info("result={}", result);

    } catch (SQLException e) {
      throw new IllegalStateException(e);
    }

    return result;
  }
}
