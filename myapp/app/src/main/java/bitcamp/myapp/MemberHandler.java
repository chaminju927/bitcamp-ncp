package bitcamp.myapp;

import java.sql.Date;

public class MemberHandler {
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
	
	 static void inputMembers() {

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