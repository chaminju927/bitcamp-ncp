package bitcamp.boot.app.dao;

import java.util.Arrays;
import bitcamp.boot.app.vo.Member;

public class MemberDao {
  private static final int SIZE = 100;

  private int no;
  private int count;
  private Member[] members = new Member[SIZE];

  public void insert(Member member) {
    member.setNo(++no);
    this.members[this.count++] = member;
  }

  public Member[] findAll() {
    return Arrays.copyOf(members,count);
  }

  public void delete(Member member) {
    for (int i = this.indexOf(member) + 1; i < this.count; i++) {
      this.members[i - 1] = this.members[i];
    }
    this.members[--this.count] = null; // 레퍼런스 카운트를 줄인다.
  }

  public void update(Member member) {
    this.members[this.indexOf(member)] = member;
  }

  public Member findByNo(int no) {
    for (int i = 0; i < this.count; i++) {
      if (this.members[i].getNo() == no) {
        System.out.println(members[i].getGender());
        return this.members[i];
      }
    }
    return null;
  }


  int indexOf(Member m) {
    for (int i = 0; i < this.count; i++) {
      if (this.members[i].getNo() == m.getNo()) {
        return i;
      }
    }
    return -1;
  }

}
