package bitcamp.myapp.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bitcamp.util.RequestHandlerMapping;

@MultipartConfig(maxFileSize = 1024 * 1024 * 50)
@WebServlet("/app/*")
public class DispatcherServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      // 요청 URL => http://localhost:8080/web/app/board/list
      // - Context Root: /web
      // - ServletPath: /app
      // - PathInfo: /board/list
      String pathInfo = request.getPathInfo();

      // 페이지 컨트롤러 실행
      if (pathInfo.equals("/")) {
        response.sendRedirect(request.getContextPath() + "/");
        return;
      }

      ServletContext ctx = getServletContext();

      // 클라이언트가 요청한 URL을 가지고 페이지 컨트롤러의 요청 핸들러를 찾는다.
      RequestHandlerMapping requestHandlerMapping = (RequestHandlerMapping) ctx.getAttribute(pathInfo);
      if (requestHandlerMapping == null) {
        request.getRequestDispatcher("/NotFoundController.jsp").forward(request, response);
        return;
      }

      // 요청 핸들러 실행 // 인스턴스 주소부터 넘겨주기
      String view = (String) requestHandlerMapping.method.invoke(
          requestHandlerMapping.controller, request, response); //controller.execute(request, response)

      // 쿠키 처리
      @SuppressWarnings("unchecked")
      List<Cookie> cookies = (List<Cookie>) request.getAttribute("cookies");
      if (cookies != null) {
        for (Cookie cookie : cookies) {
          response.addCookie(cookie);
        }
      }

      // 뷰 컴포넌트 실행
      if (view != null) {
        if (view.startsWith("redirect:")) {
          response.sendRedirect(view.substring(9));
        } else {
          request.getRequestDispatcher(view).forward(request, response);
        }
      }
    } catch (Exception e) {
      System.out.println("요청 처리 실패");
      request.getRequestDispatcher("/ServerError.jsp").forward(request, response);
    }

  }
}






