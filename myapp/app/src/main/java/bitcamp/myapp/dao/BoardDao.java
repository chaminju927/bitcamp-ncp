package bitcamp.myapp.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import bitcamp.myapp.vo.Board;


public class BoardDao {

  List<Board> list;
  int lastNo;

  public BoardDao(List<Board> list) {
    this.list = list;
  }

  public void insert(Board board) {
    board.setNo(++lastNo);
    board.setCreatedDate(new Date(System.currentTimeMillis()).toString());
    list.add(board);
  }

  public Board[] findAll() {
    Board[] boards = new Board[list.size()];
    Iterator<Board> i = list.iterator();
    int index = 0;
    while (i.hasNext()) {
      boards[index++] = i.next();
    }
    return boards;
  }

  public Board findByNo(int no) {
    Board b = new Board();
    b.setNo(no);

    int index = list.indexOf(b);
    if (index == -1) {
      return null;
    }

    return list.get(index);
  }

  public void update(Board b) {
    int index = list.indexOf(b);
    list.set(index, b);
  }

  public boolean delete(Board b) {
    return list.remove(b);
  }

  public void save(String filename) {
    try (FileWriter out = new FileWriter(filename)) {

      //Gson gson = new Gson() ;  //사용시마다 매번 생성해야 함
      out.write(new Gson().toJson(list));

    } catch (Exception e ) {
      e.printStackTrace();
    }
  }


  public void load(String filename) {
    if (list.size() > 0) {  //중복 로딩 방지
      return;
    }
    try ( BufferedReader in = new BufferedReader(new FileReader(filename))) {

      //1) JSON 데이터를 어떤 타입의 객체로 변환할 것인지 그 타입 정보를 준비한다.
      TypeToken<List<Board>> collectionType = new TypeToken<>() {};
      //typetoken익클만들고 인스턴스 바로 생성, 보드객체를 담고 있는 컬렉션

      //2) 입력 스트림에서 JSON데이터를 읽고, 지정한 타입의 객체로 변환해 리턴한다.
      list = new Gson().fromJson(in, collectionType);

      if (list.size() > 0) {
        lastNo = list.get(list.size() - 1).getNo();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //  public static void load() {
  //    File filename = new File("./board.csv");
  //
  //    try {
  //      out = new FileReader(filename);
  //      Scanner scanner = new Scanner();
  //
  //      while (true) {
  //        String record = scanner.nextLine();
  //
  //        String[] values = record.split(",");
  //
  //        Board b = new Board();
  //        b.setNo(Integer.parseInt(values[0]));
  //        b.setTitle(values[1]);
  //        b.setContent(values[2]);
  //        b.setPassword(values[3]);
  //        b.setCreatedDate(values[4]);
  //        b.setViewCount(Integer.parseInt(values[5]));
  //
  //        list.add(b);
  //
  //      } catch (NoSuchElementException e) {
  //        break;
  //      }
  //    }
  //  } catch (Exception e){
  //    System.out.println("파일 출력 중 오류 발생");
  //  } finally {
  //    try {scanner.close();} catch (Exception e) {}
  //    try {out.close();} catch (Exception e) {}
  //
  //
  //  }

}




