package bitcamp.boot.app.dao;

import java.util.Arrays;
import java.util.Date;
import org.springframework.stereotype.Repository;
import bitcamp.boot.app.vo.Student;

@Repository
public class StudentDao {
  private static final int SIZE = 100;

  private int no;
  private int count;
  private Student[] students = new Student[SIZE];

  public void insert(Student student) {
    student.setNo(++no);
    this.students[this.count++] = student;
    student.setCreatedDate(new Date(System.currentTimeMillis()).toString());
  }

  public Student[] findAll() {
    return Arrays.copyOf(students,count);
  }

  public void delete(Student student) {
    for (int i = this.indexOf(student) + 1; i < this.count; i++) {
      this.students[i - 1] = this.students[i];
    }
    this.students[--this.count] = null; // 레퍼런스 카운트를 줄인다.
  }

  public void update(Student student) {
    this.students[this.indexOf(student)] = student;
  }

  public Student findByNo(int no) {
    for (int i = 0; i < this.count; i++) {
      if (this.students[i].getNo() == no) {
        System.out.println(students[i].getGender());
        return this.students[i];
      }
    }
    return null;
  }


  int indexOf(Student m) {
    for (int i = 0; i < this.count; i++) {
      if (this.students[i].getNo() == m.getNo()) {
        return i;
      }
    }
    return -1;
  }

}
