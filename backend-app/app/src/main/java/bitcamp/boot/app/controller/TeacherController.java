package bitcamp.boot.app.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import bitcamp.boot.app.dao.TeacherDao;
import bitcamp.boot.app.vo.Teacher;

@RestController
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
public class TeacherController {

  TeacherDao teacherDao = new TeacherDao();

  @PostMapping("/teachers")     //스프링부트가 호출하는 메서드
  public Object addTeacher(Teacher teacher
      //      String name,
      //      String tel,
      //      String email,
      //      String academicLevel,
      //      String university,
      //      String major,
      //      int pay
      ) {

    Teacher t = new Teacher();
    t.setName(name);
    t.setTel(tel);
    t.setEmail(email);
    t.setAcademicLevel(academicLevel);
    t.setUniversity(university);
    t.setMajor(major);
    t.setPay(pay);

    t.setCreatedDate(new Date(System.currentTimeMillis()).toString());

    this.teacherDao.insert(t);

    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status","success");

    return contentMap;
  }

  @GetMapping("/teachers")
  public Object getTeachers() {

    Teacher[] teachers = this.teacherDao.findAll();

    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status","success");
    contentMap.put("data",teachers);

    return contentMap;
  }

  @GetMapping("/teachers/{no}")
  public Object getTeacher(@PathVariable int no) {
    Teacher t = this.teacherDao.findByNo(no);
    Map<String,Object> contentMap = new HashMap<>();

    if (t == null) {
      contentMap.put("status","failure");
      contentMap.put("data", "강사가 없습니다.");
    } else {
      contentMap.put("status","success");
      contentMap.put("data", t);
    }
    return contentMap;
  }

  @PutMapping("/teachers/{no}")
  public Object updateTeacher(Teacher teacher) {

    Map<String,Object> contentMap = new HashMap<>();

    Teacher old = this.teacherDao.findByNo(teacher.getNo());
    if (old == null ) {
      contentMap.put("status", "failure");
      contentMap.put("data", "강사가 없습니다.");
      return contentMap;
    }
    teacher.setCreatedDate(old.getCreatedDate());

    this.teacherDao.update(teacher);

    contentMap.put("status","success");
    return contentMap;
  }


  @DeleteMapping("/teachers/{teacherNo}")
  public Object deleteTeacher(@PathVariable int teacherNo) {

    Teacher t = this.teacherDao.findByNo(teacherNo);

    Map<String,Object> contentMap = new HashMap<>();

    if (t == null ) {
      contentMap.put("status","failure");
      contentMap.put("data","강사가 없거나 암호가 맞지 않습니다.");
    } else {
      this.teacherDao.delete(t);
      contentMap.put("status","success");
    }
    return contentMap;
  }







}
