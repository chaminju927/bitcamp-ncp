package bitcamp.myapp.listener;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration.Dynamic;
import javax.servlet.annotation.WebListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import bitcamp.myapp.config.AppConfig;

@WebListener
public class AppInitListener implements ServletContextListener {
  @Override
  public void contextInitialized(ServletContextEvent sce) {
    // Sprint IoC 컨테이너 준비
    AnnotationConfigWebApplicationContext iocContainer = new AnnotationConfigWebApplicationContext();
    iocContainer.register(AppConfig.class);

    // DispatcherServlet 프론트 컨트롤러 준비
    DispatcherServlet dispatcherServlet = new DispatcherServlet(iocContainer);
    Dynamic registration = sce.getServletContext().addServlet("app", dispatcherServlet);
    registration.addMapping("/app/*");
    registration.setLoadOnStartup(1);   //낮은 숫자부터 실행
    registration.setMultipartConfig(new MultipartConfigElement(
        System.getProperty("java.io.tmpdir"), // 클라이언트가 보낸 파일을 임시 보관할 폴더
        1024 * 1024 * 20, // 한 파일의 최대 크기 10은 메가
        1024 * 1024 * 20 * 10, // 한 요청당 최대 총 파일 크기 100메가
        1024 * 1024 * 1 //1메가. 클라이언트가 보낸 파일을 메모리에 임시 보관하는 최대 크기
        //이 최대 크기를 초과시 파일에 내보낸다.
        ));

  }
}
