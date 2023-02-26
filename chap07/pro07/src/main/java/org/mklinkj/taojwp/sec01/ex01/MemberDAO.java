package org.mklinkj.taojwp.sec01.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberDAO {
  private Statement stmt;
  private Connection con;

  public List<MemberVO> listMembers() {
    List<MemberVO> list = new ArrayList<>();
    try {
      connDB();
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

  private void connDB() {
    try {
      Class.forName("oracle.jdbc.OracleDriver");
      LOGGER.info("Oracle 드라이버 로딩 성공");
      con =
          DriverManager.getConnection(
              "jdbc:oracle:thin:@localvmdb.oracle_xe_18c:1521:XE", "scott", "tiger");
      stmt = con.createStatement();
      LOGGER.info("Statement 생성 성공");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
