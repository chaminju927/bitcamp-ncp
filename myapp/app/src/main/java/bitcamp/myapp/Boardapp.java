package main.java.bitcamp.myapp;

public class Boardapp {
	public static void app(String args[]) {
		mainMenu();
		System.out.println("Bye!");
		
		Prompt.close();	
	}
	 private static void mainMenu() {
		    while (true) {
		      System.out.println("1. 게시글 입력");
		      System.out.println("2. 게시글 목록");
		      System.out.println("3. 게시글 조회");
		      System.out.println("4. 게시글 변경");
		      System.out.println("5. 게시글 삭제");
		      System.out.println("9. 종료");
		      int menuNo = Prompt.inputInt("메뉴> ");

		      if (menuNo == 1) {
		        BoardHandler.title ="게시글 작성하기";
		        BoardHandler.makeContent();

		      } else if (menuNo == 2) {
		    	BoardHandler.title = "게시글 목록";
		        BoardHandler.printContent();

		      } else if (menuNo == 3) {
		    	BoardHandler.title = "게시글 조회하기";
				BoardHandler.searchContent();

		      } else if (menuNo == 4) {
			    	BoardHandler.title = "게시글 변경하기";
			    	BoardHandler.modifyContent();

		      } else if (menuNo == 5) {
			    	BoardHandler.title = "게시글 삭제하기"; 	
					BoardHandler.deleteContent();
		       
		      } else if (menuNo == 9) {
		        break;
		      } else {
		        System.out.println("잘못 입력하셨습니다.");
		      }
		    }
		  }

}