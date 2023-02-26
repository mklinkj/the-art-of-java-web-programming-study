package org.mklinkj.taojwp.sec01.ex01;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringSubstitutor;

@Slf4j
@WebServlet("/member")
public class MemberServlet extends HttpServlet {
  private static final String SERVER_ENCODING = StandardCharsets.UTF_8.name();

  @Override
  public void init() {
    LOGGER.info("init 메서드 호출");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    request.setCharacterEncoding(StandardCharsets.UTF_8.name());
    response.setContentType(String.format("text/html;charset=%s", SERVER_ENCODING));

    MemberDAO dao = new MemberDAO();
    List<MemberVO> list = dao.listMembers();

    response
        .getWriter()
        .print(
            StringSubstitutor.replace(
                """
            <!DOCTYPE html>
            <html lang="ko">
            <head>
              <meta charset="UTF-8">
              <title>회원 목록</title>
              <style>
                table {
                  width: 800px;
                }
                table, th, td {
                  border: 1px solid black;
                  text-align: center;
                }
              </style>
            </head>
            <body>
              <table>
                <thead>
                  <tr style="background-color:lightgreen">
                    <th>아이디</th>
                    <th>비밀번호</th>
                    <th>이름</th>
                    <th>이메일</th>
                    <th>가입일</th>
                  </tr>
                </thead>
                <tbody>
                  ${tableContent}
                </tbody>
              </table>
            </body>
            </html>
            """,
                Map.of("tableContent", tableContent(list))));
  }

  private String tableContent(List<MemberVO> list) {
    StringBuilder sb = new StringBuilder();
    list.forEach(
        member ->
            sb.append(
                StringSubstitutor.replace(
                    """
                          <tr>
                            <td>${id}</td>
                            <td>${pw}</td>
                            <td>${name}</td>
                            <td>${email}</td>
                            <td>${joinDate}</td>
                          </tr>
                    """,
                    Map.of(
                        "id",
                        member.getId(),
                        "pw",
                        member.getPwd(),
                        "name",
                        member.getName(),
                        "email",
                        member.getEmail(),
                        "joinDate",
                        member.getJoinDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))))));
    return sb.toString();
  }

  @Override
  public void destroy() {
    LOGGER.info("destroy 메서드 호출");
  }
}
