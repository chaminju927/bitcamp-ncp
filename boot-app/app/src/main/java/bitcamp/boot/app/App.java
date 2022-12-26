package bitcamp.boot.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
    @GetMapping("/hello")
    public String hello() {
      return "Hello, world!";    //스프링부트 포트번호는 8080
    }                       //localhost:8080/hello 웹에서 확인
}

