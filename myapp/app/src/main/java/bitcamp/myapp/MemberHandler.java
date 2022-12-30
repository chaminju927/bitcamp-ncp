package bitcamp.myapp;

import java.sql.Date;

public class MemberHandler {

  static final int SIZE =100;      //size 우클릭후 리팩터->리네임 모든 size 이름 한번에 수정 가능
  static int count = 0;

  static int[] no = new int [SIZE];                  //배열의 갯수를 각각 지정하기 보단 size크기 미리 설정해두고
  static String[] name = new String[SIZE];          //size만 입력하는게 낫다
  static String[] tel = new String[SIZE];
  static String[] postNo = new String[SIZE];
  static String[] basicAddress = new String[SIZE];
  static String[] detailAddress= new String[SIZE];
  static boolean[] working = new boolean[SIZE];
  static char[] gender = new char[SIZE];
  static byte[] level = new byte[SIZE];
  static String[] createdDate = new String[SIZE];

  static void inputMembers() {
    for (int i = 0; i< SIZE; i++) {

      no[i]= Prompt.inputInt("번호? ");
      name[i]= Prompt.inputString("이름? ");
      tel[i]= Prompt.inputString("전화? ");
      postNo[i]= Prompt.inputString("우편번호? ");
      basicAddress[i]= Prompt.inputString("주소? ");
      detailAddress[i]= Prompt.inputString("상세주소? ");

      working[i] = Prompt.inputInt("0. 미취업\n1. 재직중\n재직자? ") == 1;
      gender[i]= Prompt.inputInt("0. 남자\n1. 여자\n1성별?") == 0? 'M' : 'W';
      level[i] =(byte)Prompt.inputInt("0. 비전공자\n1. 준전공자\n2. 전공자\n전공?");
      createdDate[i] = new Date(System.currentTimeMillis()).toString();    //ctrl+shift+O 입력하면 import가능한 목록 뜸

      count++;

      String str = Prompt.inputString("계속 입력하시겠습니까?(Y/N)");
      if (!str.equalsIgnoreCase("Y") && str.length() != 0){  //문자열이 y와 같지 않고 문자열 길이가 0이 아니라면
        break;
      }
    }
    Prompt.close();
  }
  static void printMembers() {
    for (int i= 0; i < count; i++) {
      System.out.printf("번호: %d\n", no[i]); //system이라는 도구함에 out이라는 변수 안에 콘솔정보가 저장됨
      System.out.printf("이름: %s\n", name[i]);
      System.out.printf("전화: %s\n", tel[i]);
      System.out.printf("우편번호: %s\n", postNo[i]);
      System.out.printf("주소: %s\n", basicAddress[i]);
      System.out.printf("상세주소: %s\n", detailAddress[i]);
      System.out.printf("재직여부: %s\n", working[i] ? "예" : "아니오");
      System.out.printf("성별: %s\n", gender[i] =='M' ? "남자" : "여자");

      String levelTitle;
      switch (level[i]) {
        case 0: levelTitle = "비전공자"; break;
        case 1: levelTitle = "준전공자"; break;
        default: levelTitle = "전공자";
      }
      System.out.printf("전공: %s\n", levelTitle);
      System.out.printf("가입일: %s\n", createdDate[i]);

      System.out.println("---------------------------------");
    }
  }

}
