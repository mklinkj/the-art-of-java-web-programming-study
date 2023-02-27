package org.mklinkj.taojwp.sec04.ex03;

import static org.mklinkj.taojwp.common.Constants.HTML_CONTENT_TYPE;
import static org.mklinkj.taojwp.common.Constants.SERVER_ENCODING;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/member")
public class MemberServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    doHandle(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    doHandle(request, response);
  }

  protected void doHandle(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    request.setCharacterEncoding(SERVER_ENCODING);
    response.setContentType(String.format(HTML_CONTENT_TYPE));

    String command = request.getParameter("command");

    MemberDAO dao = new MemberDAO();
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

    List<MemberVO> memberList = dao.listMembers();

    request.setAttribute("memberList", memberList);
    RequestDispatcher dispatch = request.getRequestDispatcher("viewMembers");
    dispatch.forward(request, response);
  }
}
