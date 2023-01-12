package bitcamp.boot.app.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import bitcamp.boot.app.dao.TeacherDao;
import bitcamp.boot.app.vo.Teacher;

//@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
@RestController
public class TeacherController {
  @Autowired TeacherDao teacherDao = new TeacherDao();//객체와 그 주소를 자동생성시켜줌, 인스턴스필드에만 사용한다
  //안붙이면 주소는 주지 않아 null


  @PostMapping("/teachers")
  public Object addTeacher(Teacher teacher) {

    this.teacherDao.insert(teacher);

    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status", "success");

    return contentMap;
  }

  @GetMapping("/teachers")
  public Object getTeachers() {

    Teacher[] teachers = this.teacherDao.findAll();

    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status", "success");
    contentMap.put("data", teachers);

    return contentMap;
  }


  @GetMapping("/teachers/{no}")
  public Object getTeacher(@PathVariable int no) {

    Teacher b = this.teacherDao.findByNo(no);

    Map<String,Object> contentMap = new HashMap<>();

    if (b == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "강사가 없습니다.");
    } else {
      contentMap.put("status", "success");
      contentMap.put("data", b);
    }

    return contentMap;
  }

  @PutMapping("/teachers/{no}")
  public Object updateTeacher(Teacher teacher) {

    Map<String,Object> contentMap = new HashMap<>();

    Teacher old = this.teacherDao.findByNo(teacher.getNo());
    if (old == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "강사가 없습니다.");
      return contentMap;
    }

    teacher.setCreatedDate(old.getCreatedDate());

    this.teacherDao.update(teacher);

    contentMap.put("status", "success");

    return contentMap;
  }

  @DeleteMapping("/teachers/{no}")
  public Object deleteTeacher(@PathVariable int no) {

    Teacher m = this.teacherDao.findByNo(no);

    Map<String,Object> contentMap = new HashMap<>();

    if (m == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "강사가 없습니다.");

    } else {
      this.teacherDao.delete(m);
      contentMap.put("status", "success");
    }

    return contentMap;
  }
}
