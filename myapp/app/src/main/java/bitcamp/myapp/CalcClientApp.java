package bitcamp.myapp;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class CalcClientApp {
  public static void main(String[] args) throws Exception {

    Scanner keyScan = new Scanner(System.in);
    System.out.println("클라이언트 실행중");

    Socket socket = new Socket("127.0.0.1", 8888);
    // System.out.println("계산기 작동 시작!");

    PrintStream out = new PrintStream(socket.getOutputStream()); //클라->서버
    Scanner in = new Scanner(socket.getInputStream());

    //while (true) {
    System.out.println("계산기 작동 시작");
    System.out.println("식을 입력하세요! ex) 2 + 16");
    System.out.print(": ");

    String method = keyScan.nextLine();
    out.println(method);

    //      if (method.equals("quit")) {
    //        break;
    //      }
    // System.out.println("서버에 메세지 보냄");

    //서버에서 응답 올때까지 리턴하지 않는다.
    String response = in.nextLine(); //응답을 in에 받아와서 출력
    //      Integer answer = Integer.parseInt(response);
    System.out.printf("답 = %s", response);
    //}

    out.close();
    in.close();
    socket.close();

    System.out.println("클라이언트 종료");
    keyScan.close();
  }

}
