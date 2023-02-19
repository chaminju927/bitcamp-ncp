package bitcamp.myapp.dao;

import java.util.List;
import bitcamp.myapp.vo.Teacher;

public interface TeacherDao {
  void insert(Teacher t);
  List<Teacher> findAll();
  Teacher findByNo(int no);
  List<Teacher> findByKeyword(String keyword);
 // void increaseViewCount(int no);
  int update(Teacher t);
  int delete(int no);
}







