package bitcamp.myapp;

import java.sql.Date;  //  ctrl shift o로 자동 import
import java.util.Scanner;

public class App {
	static final int SIZE = 100;
	static int count = 0;
	
	static int[] no = new int[SIZE];
	static String[] name = new String[SIZE];
	static String[] tel = new String[SIZE];
	static String[] postNo = new String[SIZE];  // int일때는 맨앞에 0이 생략됨
	static String[] basicAddress = new String[SIZE];
	static String[] detailAddress = new String[SIZE];
	static boolean[] working = new boolean[SIZE];
	static char[] gender = new char[SIZE];
	static byte[] level = new byte[SIZE];
	static String[] cratedDate = new String[SIZE];
	
  public static void main(String[] args) {
	  
	  inputMembers();
	  
	  System.out.println();
	  
	  printMembers();
  }
  
  static void inputMembers() {
	  Scanner keyScanner = new Scanner(System.in);  //파라미터값?

    for (int i = 0; i < SIZE; i++ ) {
      no[i] = promptInt(keyScanner,"번호?");
      name[i] = promptString(keyScanner,"이름?");
      tel[i] = promptString(keyScanner,"전화?");
      postNo[i] = promptString(keyScanner,"우편번호?");
      basicAddress[i] = promptString(keyScanner,"주소?"); ///prompt
      detailAddress[i] = promptString(keyScanner,"상세주소?");

      String title = "0.미취업\n1. 재직중\n재직자?";
      working[i] = promptInt(keyScanner, title) ==1;
  
      title = "0. 남자\n1. 여자\n성별? ";
      working[i]= promptInt(keyScanner, title) == 0 ? 'M' : 'W';
     
      title = "0. 비전공자\n1. 준전공자\n2. 전공자\n전공?";
      level[i] = (byte)promptInt(keyScanner, title);   //byte도 파라미터값?
      
      Date today = new Date(System.currentTimeMillis());
      createdDate[i] = today.toString();

      count++;

      String str = promptString(keyScanner."계속 입력하시겠습니까? (Y/N)");
      if (!str.equalsIgnoreCase("Y")&& str.length()!=0); {
        break;
      }
    }
    keyScanner.close();

    static void printMembers() {
    for (int i = 0; i < count; i++) {
      System.out.printf("번호: %d\n","no[i]");  //숫자는 %d 문자는 %s
      System.out.printf("이름: %s\n","name[i]");
      System.out.printf("전화: %s\n","tel[i]");
      System.out.printf("우편번호: %s\n","postNo[i]");
      System.out.printf("주소: %s\n","basicAddress[i]");
      System.out.printf("상세주소: %s\n","detailAddress[i]");
      System.out.printf("재직자: %s\n",working[i]? "yes" : "no");
      System.out.printf("성별: %s\n",gender[i] == 'M' ? "남자" : "여자");

      String levelTitle;
      switch (level[i]) {
        case 0 : levelTitle = "비전공자"; break;
        case 1 ; levelTitle = "준전공자"; break;
        default : levelTitle = "전공자";
      }
      System.out.printf("전공: %s\n", levelTitle);
      System.out.printf("가입일: %s\n", createdDate[i]);

      System.out.println("-----------------------");
    }
  } 
  
    static String promptString(Scanner scanner, String title) {
    	System.out.print(title);
    	return scanner.nextLine();
    }
    static int promptInt(Scanner scanner, String title) {//////scanner scanner?
    	return Integer.parseInt(promptString(scanner, title));
    }
} // class App







