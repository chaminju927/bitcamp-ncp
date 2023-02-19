package bitcamp.myapp.servlet.teacher;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import bitcamp.myapp.dao.TeacherDao;
import bitcamp.myapp.vo.Teacher;
import bitcamp.util.BitcampSqlSessionFactory;
import bitcamp.util.DaoGenerator;

@WebServlet("/teacher/view")
public class TeacherViewServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private TeacherDao TeacherDao;

  public TeacherViewServlet() {
    try {
      InputStream mybatisConfigInputStream = Resources.getResourceAsStream(
          "bitcamp/myapp/config/mybatis-config.xml");
      SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
      BitcampSqlSessionFactory sqlSessionFactory = new BitcampSqlSessionFactory(
          builder.build(mybatisConfigInputStream));
      TeacherDao = new DaoGenerator(sqlSessionFactory).getObject(TeacherDao.class);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    int teacherNo = Integer.parseInt(request.getParameter("no"));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>비트캠프 - NCP 1기</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>강사</h1>");
    out.println("<form action='insert' method='post'>");
    out.println("<table border='1'>");
    out.println("<tr>");
    out.println("  <th>이름</th>");
    out.println("  <td><input type='text' name='title'></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("  <th>이메일</th>");
    out.println("  <td><textarea name='content' rows='10' cols='60'></textarea></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("  <th>전화번호</th>");
    out.println("  <td><textarea name='tel' rows='10' cols='60'></textarea></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("  <th>학위</th>");
    out.println("  <td><select id='tel'></select></td>");
    out.println("  		<option value = '1'>고졸></option>");
    out.println("		<option value = '2'>전문학사</option>");
    out.println("		<option value = '3'>학사</option>");
    out.println("		<option value = '4'>석사</option>");
    out.println("		<option value = '5'>박사</option>");
    out.println("		<option selected>기타</option>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("  <th>학교</th>");
    out.println("  <td><textarea name='school' rows='10' cols='60'></textarea></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("  <th>전공</th>");
    out.println("  <td><textarea name='tel' rows='10' cols='60'></textarea></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("  <th>시강료</th>");
    out.println("  <td><textarea name='tel' rows='10' cols='60'></textarea></td>");
    out.println("</tr>");
    

    Teacher b = this.TeacherDao.findByNo(teacherNo);

    if (b == null) {
      out.println("<p>해당 번호의 게시글 없습니다.</p>");

    } else {

      out.println("<form id='teacher-form' action='update' method='post'>");
      out.println("<table border='1'>");

     
    out.println("<div>");
    out.println("  <button id='btn-list' type='button'>목록</button>");
    out.println("  <button>변경</button>");
    out.println("  <button id='btn-delete' type='button'>삭제</button>");
    out.println("</div>");

    out.println("</form>");

    out.println("<script>");
    out.println("document.querySelector('#btn-list').onclick = function() {");
    out.println("  location.href = 'list';");
    out.println("}");
    out.println("document.querySelector('#btn-delete').onclick = function() {");
    out.println("  var form = document.querySelector('#teacher-form');");
    out.println("  form.action = 'delete';");
    out.println("  form.submit();");
    out.println("}");
    out.println("</script>");

    out.println("</body>");
    out.println("</html>");

  }
  }
}
