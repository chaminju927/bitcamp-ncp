package bitcamp.boot.app.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import bitcamp.boot.app.dao.StudentDao;
import bitcamp.boot.app.vo.Student;

// 다음 클래스가 클라이언트 요청을 처리하는 일을 한다는 것을 SpringBoot에게 알리는 표시
// => SpringBoot는 다음 클래스의 인스턴스를 생성해서 보관해 둔다.
// => "/hello" 라는 URL로 클라이언트 요청이 들어오면 해당 메서드를 호출한다.
@RestController
//@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})  // origins에서 들어오는 요청만 허락한다
public class StudentController {        //html origin 서버에서 받아온 데이터 //url이 다수이면 배열 {}에 담기

  StudentDao studentDao;           // StudentDao를 사용해 studentDao객체 선언

  public StudentController(StudentDao studentDao) {
    this.studentDao = studentDao;
  }

  @PostMapping("/students")
  public Object addStudent(Student student) {

    this.studentDao.insert(student);      //studentDao에 그 객체 주소를 전부 저장
    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status","success");
    return contentMap;
  }

  @GetMapping("/students")     //get요청 들어왔을때 호출
  public Object getStudents() {

    Student[] students = this.studentDao.findAll();

    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status","success");
    contentMap.put("data",students);

    return contentMap;
  }

  //Get 요청 /더 디테일한 요청
  @GetMapping("/students/{no}")
  public Object getStudent(@PathVariable int no) { //url에서 넘어오는{}값이 보드넘버로

    Student m = this.studentDao.findByNo(no);

    // 응답 결과를 담을 맵 객체 준비
    Map<String,Object> contentMap = new HashMap<>();

    if (m == null) {
      contentMap.put("status","failure");
      contentMap.put("data","해당 번호의 회원이 없습니다.");
    } else {
      contentMap.put("status","success");
      contentMap.put("data", m);
    }
    return contentMap;

  }


  @PutMapping("/students/{no}")
  public Object updateStudent(Student student
      //@PathVariable int no //Student인스턴스로 직접 받을 수 있다
      ) {

    //    System.out.printf("%d,%s,%s,%s,%s,%s,%b,%c,%d\n",
    //        student.getNo(), student.getName(), student.getTel(),
    //        student.getPostNo(), student.getBasicAddress(), student.getDetailAddress(),
    //        student.isWorking(), student.getGender(), student.getLevel());

    Map<String,Object> contentMap = new HashMap<>();

    Student old = this.studentDao.findByNo(student.getNo());
    if (old == null ) {
      contentMap.put("status", "failure");
      contentMap.put("data", "회원이 없습니다.");
      return contentMap;
    }
    student.setCreatedDate(old.getCreatedDate());

    this.studentDao.update(student);      //studentDao에 변경된 데이터를 전부 저장

    contentMap.put("status","success");

    return contentMap;
  }

  //삭제요청
  @DeleteMapping("/students/{no}")
  public Object deleteStudent(
      //파라미터를 낱개로 받을 때는 @pathvariable 생략 불가
      @PathVariable int no) {

    Student m = this.studentDao.findByNo(no);

    // 응답 결과를 담을 맵 객체 준비
    Map<String,Object> contentMap = new HashMap<>();

    if (m == null ) {
      contentMap.put("status","failure");
      contentMap.put("data","게시글이 없거나 암호가 맞지 않습니다.");
    } else {
      this.studentDao.delete(m);
      contentMap.put("status","success");
    }
    return contentMap;
  }
}
