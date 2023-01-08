package main.java.bitcamp.myapp;

import java.sql.Date;

import bitcamp.myapp.Board.;
import bitcamp.myapp.Prompt;

public class BoardHandler {
	 static final int SIZE = 100;
	 static int count = 0;
	 
	 static Board[] boards = new Board[SIZE];

	  static String title = "게시판관리";
	  
	  static void makeContent() {          //게시글작성
		  Board b = new Board();
		  b.no = Prompt.inputInt("번호? ");
		  b.title = Prompt.inputString("제목? ");
		  b.content = Prompt.inputString("내용? ");
		  b.password = Prompt.inputString("암호? ");
		  b.createdDate = new Date(System.currentTimeMillis()).toString();

		  boards[count++] = b;
		  }  

      static void printContent() {                    //게시글 목록
        System.out.println("번호\t제목\t작성일\t조회수");
    
        for (int i = 0; i < count; i++) {
          Board b = boards[i];     
          System.out.printf("%d\t%s\t%s\t%d\n", b.no, b.title, b.createdDate, b.view);
        }
      }
      
    static void searchContent() {              //게시글 조회
      int contentNo = Prompt.inputInt("게시글 번호? ");

      Board b = findByNo(contentNo);

      if (b == null) {
        System.out.println("해당 번호의 게시글이 없습니다.");
        return;
      }
      System.out.printf("    번호: %d\n", b.no);
      System.out.printf("    제목: %s\n", b.title);
      System.out.printf("    내용: %s\n", b.content);
      System.out.printf("    암호: %s\n", b.password);
      System.out.printf("  조회수: %d\n", b.view);
    }

    static Board findByNo(int no) {  //입력받은 int no가 배열에 있는지 검사
      for (int i = 0; i < count; i++) {
        if (boards[i].no == no) {
          return boards[i];
        }
      }
      return null;
    }
	  
    static void modifyContent() {            //게시글변경
      int contentNo = Prompt.inputInt("게시글번호? ");
  
      Board old = findByNo(contentNo);
  
      if (old == null) {
        System.out.println("해당 번호의 게시글이 없습니다.");
        return;
      }
      Board b = new Board();
      b.no = old.no;
      b.createdDate = old.createdDate;
      b.title = Prompt.inputString(String.format("제목(%s)? ", old.title));
      b.content = Prompt.inputString(String.format("내용(%s)? ", old.content));
      b.password = Prompt.inputString(String.format("비밀번호(%s)? ", old.password));
      
      String str = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
      if (str.equalsIgnoreCase("Y")) {
        boards[indexOf(old)] = b;
        System.out.println("변경했습니다.");
      } else {
        System.out.println("변경 취소했습니다.");
      }
    }
    
    static int indexOf(Board b) {     //
      for (int i = 0; i < count; i++) {
        if (boards[i].no == b.no) {
          return i;
        }
      }
      return -1;   ////??
    }

    static void deleteContent() {                 //게시글삭제
      int contentNo = Prompt.inputInt("게시글 번호? ");
      Board b = findByNo(contentNo);
  
      if (b == null) {
        System.out.println("해당 번호의 게시글이 없습니다.");
        return;
      }
      String str = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
      if (!str.equalsIgnoreCase("Y")) {
        System.out.println("삭제 취소했습니다.");
        return;
      }
      for (int i = indexOf(b) + 1; i < count; i++) {
        boards[i - 1] = boards[i];
      }
      boards[--count] = null; // 레퍼런스 카운트를 줄인다.
  
      System.out.println("삭제했습니다.");
    }
  
  










    static void service() {
    while (true) {
      System.out.println(title);
      System.out.println("1. 등록");
      System.out.println("2. 목록");
      System.out.println("3. 조회");
      System.out.println("4. 변경");
      System.out.println("5. 삭제");
      System.out.println("6. 검색");
      System.out.println("0. 이전");
      int menuNo = Prompt.inputInt(String.format("%s> ", title));

      switch (menuNo) {
        case 0: return;
        case 1: inputMember(); break;
        case 2: printMembers(); break;
        case 3: printMember(); break;
        case 4: modifyMember(); break;
        case 5: deleteMember(); break;
        case 6: searchMember(); break;
        default:
          System.out.println("잘못된 메뉴 번호 입니다.");
      }
    }
  }
} 


