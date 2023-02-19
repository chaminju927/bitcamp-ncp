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

@WebServlet("/Teacher/insert")
public class TeacherInsertServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private TeacherDao TeacherDao;

  public TeacherInsertServlet() {
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
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");

    Teacher teacher = new Teacher();
    teacher.setName(request.getParameter("name"));
    teacher.setEmail(request.getParameter("email"));
    teacher.setPassword(request.getParameter("password"));
    teacher.setTel(request.getParameter("tel"));
    teacher.setDegree(Integer.parseInt(request.getParameter("degree")));
    teacher.setSchool(request.getParameter("school"));
    teacher.setMajor(request.getParameter("major"));
    teacher.setWage(Integer.parseInt(request.getParameter("wage")));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='Refresh' content='1;url=list'>");
    out.println("<title>비트캠프 - NCP 1기</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>강사 관리</h1>");
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
    
    TeacherDao.insert(teacher);
    out.println("<p>입력 했습니다.</p>");

    out.println("</body>");
    out.println("</html>");
  }
}
