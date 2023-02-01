package com.eomcs.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientApp2 {
  public static void main(String[] args) throws Exception {
    Scanner keyScan = new Scanner(System.in);
    System.out.println("클라이언트 실행중");

    Socket socket = new Socket("127.0.0.1", 8888);
    System.out.println("서버에 연결됨");

    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
    DataInputStream in = new DataInputStream(socket.getInputStream());

    File file = new File("photo.png");

    //전송할 파일의 이름을 보낸다.
    out.writeUTF(file.getName());

    //전송할 파일의 크기를 먼저 보낸다.
    out.writeLong(file.length());

    //파일을 1바이트씩 읽어 보낸다.
    FileInputStream fileIn = new FileInputStream(file);
    int b;  //read 메서드에서는 더이상 읽을 게 없으면 -1
    while ((b = fileIn.read()) != -1) {
      out.writeByte(b);
    }
    fileIn.close();

    out.close();
    in.close();
    socket.close();

    System.out.println("클라이언트 종료");
  }
}
