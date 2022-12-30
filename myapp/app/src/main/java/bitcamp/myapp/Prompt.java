package bitcamp.myapp;

import java.util.Scanner;

public class Prompt {
  static Scanner scanner = new Scanner(System.in);

  static String inputString(String title) {
    System.out.print(title);
    return scanner.nextLine();   //nextLine은 사용자가 엔터칠때까지 기다리다가 치면 그때 리턴
  }

  static int inputInt(String title) {
    return Integer.parseInt(inputString(title));   //int값을 리턴하는 프롬프트
  }
  //prompt클래스를 다 사용한 후에 자원을 해제시킬 수 있는 메서드 추가
  static void close() {
    scanner.close();
  }
}
