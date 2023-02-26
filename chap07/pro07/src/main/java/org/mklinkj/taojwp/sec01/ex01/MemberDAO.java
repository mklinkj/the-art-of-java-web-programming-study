package org.mklinkj.taojwp.sec01.ex01;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberDAO {
  private Statement stmt;
  private Connection con;

  private DataSource dataFactory;

  public MemberDAO() {
    try {
      InitialContext ctx = new InitialContext();
      Context envContext = (Context) ctx.lookup("java:/comp/env");
      dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
      LOGGER.info("데이타 소스 획득 완료: {}", dataFactory.getClass().getCanonicalName());
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

  public List<MemberVO> listMembers() {
    List<MemberVO> list = new ArrayList<>();
    try {
      con = dataFactory.getConnection();
      stmt = con.createStatement();

      String query = "SELECT * FROM t_member";
      LOGGER.info(query);
      ResultSet rs = stmt.executeQuery(query);

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
      stmt.close();
      con.close();

    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
    }
    return list;
  }
}
