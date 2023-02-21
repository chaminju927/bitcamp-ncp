package bitcamp.myapp.servlet.teacher;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bitcamp.myapp.dao.TeacherDao;
import bitcamp.myapp.vo.Teacher;

@WebServlet("/teacher/list")
public class TeacherListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private TeacherDao teacherDao;

  @Override
  public void init() {
    ServletContext ctx = getServletContext();
    teacherDao = (TeacherDao) ctx.getAttribute("teacherDao");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String keyword = request.getParameter("keyword");
    List<Teacher> teachers = this.teacherDao.findAll();

    request.getRequestDispatcher("/teacher/list.jsp").forward(request, response);
  }

  //  private static String getDegreeText(int degree) {
  //    switch (degree) {
  //      case 1: return "고졸";
  //      case 2: return "전문학사";
  //      case 3: return "학사";
  //      case 4: return "석사";
  //      case 5: return "박사";
  //      default: return "기타";
  //    }
}

