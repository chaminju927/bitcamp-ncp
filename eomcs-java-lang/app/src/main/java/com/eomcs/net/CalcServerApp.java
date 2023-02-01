package com.eomcs.net;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class CalcServerApp {
  public static void main(String[] args) throws Exception {
    System.out.println("서버 실행 중...");

    ServerSocket serverSocket = new ServerSocket(8888);

    Socket socket = serverSocket.accept();
    System.out.println("계산기 연결됨!");

    Scanner in = new Scanner(socket.getInputStream());
    PrintStream out = new PrintStream(socket.getOutputStream());

    while(true) {
      String message = in.nextLine();
      System.out.printf("식 = %s\n", message);
      
      if(message.equals("quit")) {
    	  break;
      }

        String[] compute = message.split(" ");

        int a = Integer.parseInt(compute[0]);
        int b = Integer.parseInt(compute[2]);
        String c = compute[1];

        int result = 0;
        String failed = null;
        
        switch (c) {
        case "+" : result = a + b; break;
        case "-" : result = a - b; break;
        case "*" : result = a * b; break;
        case "/" : result = a / b; break;
        default: 
          failed = "Cannot Calculate";
          out.println(failed);
      }
        System.out.printf("답: \n %d %s %d = %d\n", a, c, b, result);
        out.println(result);
      }
        
        
      socket.close();
      serverSocket.close();

      System.out.println("계산기 종료!!");
    }
  }



