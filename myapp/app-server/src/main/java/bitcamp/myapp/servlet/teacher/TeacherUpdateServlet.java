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

@WebServlet("/Teacher/update")
public class TeacherUpdateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private TeacherDao TeacherDao;

  public TeacherUpdateServlet() {
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
    teacher.setNo(Integer.parseInt(request.getParameter("no")));
    
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

    Teacher old = TeacherDao.findByNo(teacher.getNo());

    if (old == null) {
      out.println("<p>해당 번호의 강사가 없습니다.</p>");

    } else if (!old.getPassword().equals(teacher.getPassword())) {
      out.println("<p>암호가 맞지 않습니다!</p>");

    } else {
      this.TeacherDao.update(teacher);
      out.println("<p>변경했습니다.</p>");
    }

    out.println("</body>");
    out.println("</html>");

    // 웹브라우저가 화면 출력을 완료한 후 1초 후에 다시 목록 화면을 호출하도록 명령한다.
    // 어떻게? 응답 헤더에 Refresh 를 추가한다.
    response.setHeader("Refresh", "1;url=list");
  }

}
