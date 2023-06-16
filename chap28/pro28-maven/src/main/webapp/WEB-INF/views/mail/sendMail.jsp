<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<div>
  <h1>메일 전송 하기</h1>

  <form method="post" action="${contextPath}/mail/sendMail">
    <sec:csrfInput/>
    <!-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> -->
    <div class="d-grid gap-2 d-md-block">
      <input type="submit" class="btn btn-outline-primary" value="메일 전송"/>
    </div>
  </form>
  <script>
    if ('${result}') {
      alert('${result}')
    }
  </script>
</div>

