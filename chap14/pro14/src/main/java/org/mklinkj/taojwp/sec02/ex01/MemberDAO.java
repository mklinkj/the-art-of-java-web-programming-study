package org.mklinkj.taojwp.sec02.ex01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.mklinkj.taojwp.common.DBUtils;

@Slf4j
public class MemberDAO {

  private final DataSource dataFactory;

  public MemberDAO() {
    try {
      dataFactory = DBUtils.getDataSourceFromJNDI();
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

  public List<MemberBean> listMembers() {
    return listMembers(new MemberBean());
  }

  public List<MemberBean> listMembers(MemberBean memberBean) {
    List<MemberBean> list = new ArrayList<>();
    String _name = memberBean.getName();

    String query = "SELECT id, pwd, name, email, joinDate FROM t_member";
    if (_name != null && !_name.isBlank()) {
      query = query + " WHERE name=?";
    }

    query += " ORDER BY joinDate DESC";

    try (Connection connection = dataFactory.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {

      if (_name != null && !_name.isBlank()) {
        preparedStatement.setString(1, _name);
      }

      LOGGER.info("query: {}", query);
      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        String id = rs.getString("id");
        String pwd = rs.getString("pwd");
        String name = rs.getString("name");
        String email = rs.getString("email");
        LocalDateTime joinDate = rs.getTimestamp("joinDate").toLocalDateTime();

        MemberBean vo = new MemberBean();
        vo.setId(id);
        vo.setPwd(pwd);
        vo.setName(name);
        vo.setEmail(email);
        vo.setJoinDate(joinDate);

        list.add(vo);
      }
      rs.close();
      preparedStatement.close();
      connection.close();

    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
    }
    return list;
  }

  public void addMember(MemberBean memberBean) {
    String query =
        """
            INSERT INTO t_member (id, pwd, name, email)
            VALUES (?, ?, ? ,?)
            """;
    try (Connection connection = dataFactory.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      String id = memberBean.getId();
      String pwd = memberBean.getPwd();
      String name = memberBean.getName();
      String email = memberBean.getEmail();
      LOGGER.info(query);
      preparedStatement.setString(1, id);
      preparedStatement.setString(2, pwd);
      preparedStatement.setString(3, name);
      preparedStatement.setString(4, email);

      preparedStatement.executeUpdate();
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
    }
  }

  public int delMember(String id) {
    String query = "DELETE FROM t_member WHERE id = ?";
    LOGGER.info(query);
    try (Connection connection = dataFactory.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, id);

      return preparedStatement.executeUpdate();
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
    }
    return 0;
  }

  public boolean isExisted(MemberBean memberBean) {
    boolean result;
    LOGGER.info("isExisted memberVo{}", memberBean);
    String id = memberBean.getId();
    String pwd = memberBean.getPwd();

    String query =
        """
            SELECT DECODE(COUNT(*), 1, 'true', 'false') AS result
              FROM t_member
             WHERE id = ?
               AND pwd = ?
            """;
    try (Connection connection = dataFactory.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, id);
      preparedStatement.setString(2, pwd);

      ResultSet rs = preparedStatement.executeQuery();
      rs.next(); // 커서를 첫번째 레코드로 위치 시

      result = Boolean.parseBoolean(rs.getString("result"));
      LOGGER.info("result={}", result);

    } catch (SQLException e) {
      throw new IllegalStateException(e);
    }

    return result;
  }
}
