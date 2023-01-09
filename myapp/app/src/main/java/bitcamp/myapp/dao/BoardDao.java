package bitcamp.myapp.dao;

import java.util.Arrays;
import bitcamp.myapp.vo.Board;

public class BoardDao {
  private static final int SIZE = 100;

  private int count;
  private Board[] boards = new Board[SIZE];  //boards는 객체 주소를 담는 레퍼런스들의 배열

  public void insert(Board board) {
    this.boards[this.count++] = board;
  }
  public Board[] findAll() {
    // 배열의 값 복제하기
    //    Board[] arr = new Board[this.count];  //count갯수만큼만 배열 가져오기
    //    for (int i = 0; i < this.count; i++) {
    //      arr[i] = this.boards[i];
    //    }
    //    return arr;

    return Arrays.copyOf(boards, count);
  }         //위 코드와 같다!!count갯수만큼 boards 배열에서 복사해옴

  public Board findByNo(int no) {
    for (int i = 0; i < this.count; i++) {
      if (this.boards[i].getNo() == no) {    //boards[i]의 주소로 가서 getNo()
        return this.boards[i];
      }
    }
    return null;
  }

  public void update(Board board) {
    this.boards[this.indexOf(board)] = board;
  }

  public void delete(Board board) {
    for (int i = this.indexOf(board) + 1; i < this.count; i++) {
      this.boards[i - 1] = this.boards[i];
    }
    this.boards[--this.count] = null; // 레퍼런스 카운트를 줄인다.
  }

  private int indexOf(Board b) {
    for (int i = 0; i < this.count; i++) {
      if (this.boards[i].getNo() == b.getNo()) {
        return i;
      }
    }
    return -1;
  }
}
