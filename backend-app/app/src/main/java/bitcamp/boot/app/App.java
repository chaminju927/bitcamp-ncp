package main.java.bitcamp.boot.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import main.java.bitcamp.boot.app.config.WebConfig;

@SpringBootApplication
public class App {

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

  // 스프링부트가 클래스를 자동 생성하지 않는다면,
  // 개발자가 직접 객체를 생성해서 리턴해야 한다.
  // 단, 스프링부트가 아래 메서드를 호출하게 하려면 표시를 해야 한다.
  @Bean
  public WebMvcConfigurer corsConfigurer() {
    System.out.println("WebMvcConfigurer 구현체를 생성함");

    class WebConfig3 implements WebMvcConfigurer{

      public WebConfig3() {
        System.out.println("스태틱 중첩 클래스 WebConfig 객체 생성함");
      }


      // - 이 클래스는 WebMvcConfigurer 규칙에 따라 메서드를 만들었음을 선언한다.
      // 단, 모든 메서드를 정의할 필요는 없고
      // 이 프로젝트 요구 조건에 맞는 부분만 설정하면 된다. (customizing.고객화)
      // - Spring WebMVC 프레임워크는 이 클래스의 정의된 메서드를 호출하여 설정을 완성한다.

      // Cross-Origin 관련해서 기본 값 외 추가로 설정할 것이 있다면 이 메서드를 정의한다.
      // 스프링부트가 시작되면 이 메서드를 호출하여 Cross-Origin을 설정할 것이다.
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
        .allowedOrigins("http://localhost:5500", "http://127.0.0.1:5500")
        .allowedMethods("*");   //없으면 put delete안됨!!
      }
    }
    return new WebConfig3();
  }
