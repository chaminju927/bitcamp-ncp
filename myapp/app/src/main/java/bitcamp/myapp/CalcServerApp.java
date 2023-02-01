package bitcamp.myapp;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class CalcServerApp {
  public static void main(String[] args) throws Exception {
    Scanner keyScan = new Scanner(System.in);
    System.out.println("서버 실행 중...");

    ServerSocket serverSocket = new ServerSocket(8888);

    Socket socket = serverSocket.accept();
    System.out.println("클라이언트와 연결됨!");

    Scanner in = new Scanner(socket.getInputStream());
    PrintStream out = new PrintStream(socket.getOutputStream());

    while(true) {
      String message = in.nextLine();
      System.out.printf("계산 식: %s\n", message);

      try {
        String[] compute = message.split(" ");

        int a = Integer.parseInt(compute[0]);
        int b = Integer.parseInt(compute[2]);
        String c = compute[1];

        int result = 0;

        switch (c) {
          case "+" : result = a + b; break;
          case "-" : result = a - b; break;
          case "*" : result = a * b; break;
          case "/" : result = a / b; break;
          default: System.out.println("입력값 오류!");
        }
        System.out.printf("계산 결과는 %d입니다!", result);
        out.println(result);

      } catch (Exception e) {
        System.out.println("연산자를 제대로 입력하세요");
        e.printStackTrace();
      }

      //String result = keyScan.nextLine();

      socket.close();
      serverSocket.close();

      System.out.println("서버 종료!!");
      keyScan.close();
    }
  }
}

