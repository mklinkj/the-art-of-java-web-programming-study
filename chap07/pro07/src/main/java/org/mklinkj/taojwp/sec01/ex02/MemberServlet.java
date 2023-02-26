package org.mklinkj.taojwp.sec01.ex02;

import static org.mklinkj.taojwp.common.Constants.SERVER_ENCODING;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringSubstitutor;
import org.mklinkj.taojwp.sec01.ex01.MemberDAO;
import org.mklinkj.taojwp.sec01.ex01.MemberVO;

@Slf4j
@WebServlet("/member3")
public class MemberServlet extends HttpServlet {
  @Override
  public void init() {
    LOGGER.info("init 메서드 호출");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    doHandle(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    doHandle(request, response);
  }

  protected void doHandle(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    request.setCharacterEncoding(SERVER_ENCODING);
    response.setContentType(String.format("text/html;charset=%s", SERVER_ENCODING));

    MemberDAO dao = new MemberDAO();

    String command = request.getParameter("command");

    if ("addMember".equals(command)) {
      String _id = request.getParameter("id");
      String _pwd = request.getParameter("pwd");
      String _name = request.getParameter("name");
      String _email = request.getParameter("email");

      MemberVO vo = new MemberVO();
      vo.setId(_id);
      vo.setPwd(_pwd);
      vo.setName(_name);
      vo.setEmail(_email);

      dao.addMember(vo);

    } else if ("delMember".equals(command)) {
      String id = request.getParameter("id");
      dao.delMember(id);
    }

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
                        <th>삭제</th>
                      </tr>
                    </thead>
                    <tbody>
                      ${tableContent}
                    </tbody>
                  </table>
                  <a href="${contextPath}/memberForm.html">새 회원 등록하기</a>
                  <form name="delProcForm" action="${contextPath}${servletPath}" method="post">
                    <input type="hidden" name="id">
                    <input type="hidden" name="command" value="delMember">
                  </form>
                  <script>
                    function delProcess(id) {
                      const delProcForm =  document.delProcForm;
                      delProcForm.id.value = id;
                      delProcForm.submit();
                    }
                  </script>
                </body>
                </html>
                """,
                Map.of(
                    "tableContent",
                    tableContent(list),
                    "contextPath",
                    request.getContextPath(),
                    "servletPath",
                    request.getServletPath())));
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
                            <td><button type="button" onclick="delProcess('${id}')">삭제</button></td>
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
