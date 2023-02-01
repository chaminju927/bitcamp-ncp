package com.eomcs.net;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class CalcClientApp {
  public static void main(String[] args) throws Exception {

    Scanner keyScan = new Scanner(System.in);

    Socket socket = new Socket("127.0.0.1", 8888);
     System.out.println("계산기 작동 시작!");

    PrintStream out = new PrintStream(socket.getOutputStream()); //클라->서버
    Scanner in = new Scanner(socket.getInputStream());
    System.out.println("종료는 quit을 입력해 주세요!");

    while (true) {
    System.out.println("식을 입력하세요> \nex. 15 / 3 \n");
    String method = keyScan.nextLine();
    out.println(method);

      if (method.equals("quit")) {
         break;
       }

    String response = in.nextLine(); 
    System.out.printf("답 = %s\n", response);
    }

    out.close();
    in.close();
    socket.close();

    System.out.println("계산기 종료");
    keyScan.close();
  }

}
