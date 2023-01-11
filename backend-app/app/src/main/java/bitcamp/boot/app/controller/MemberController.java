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
import bitcamp.boot.app.dao.MemberDao;
import bitcamp.boot.app.vo.Member;

// 다음 클래스가 클라이언트 요청을 처리하는 일을 한다는 것을 SpringBoot에게 알리는 표시
// => SpringBoot는 다음 클래스의 인스턴스를 생성해서 보관해 둔다.
// => "/hello" 라는 URL로 클라이언트 요청이 들어오면 해당 메서드를 호출한다.
@RestController
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})  // origins에서 들어오는 요청만 허락한다
public class MemberController {        //html origin 서버에서 받아온 데이터 //url이 다수이면 배열 {}에 담기

  MemberDao memberDao = new MemberDao();

  //Post요청
  @PostMapping("/members")     //스프링부트가 호출하는 메서드
  public Object addMember(
      //@PathVariable int memberNo,
      String name,  // @RequestParam(required = false) String name과 같다
      String tel,
      // @RequestParam(required = false) String postNo, 필수로 요구되는 값이 아니라는 뜻,
      // String postNo만 써도 됨,  @부터()까지는 생략이 가능하다
      String postNo,  // ..&postNo=xxx&..
      String basicAddress,
      String detailAddress,
      boolean working,   //on=true/off=false, 1=true/0=false,
      char gender,   //..&gender=M&.. => 문자1개의 문자열 변환, null또는 그밖의 문자열은 변환 오류 발생.빈칸도 오류!
      byte level) { // ..&level=1&.. => Byte.parse
    //클라이언트가 post요청으로 보낸 데이터를 title,content,password라는 이름으로 나눠서 저장

    //System.out.printf("%s,%s,%s,%s,%s,%b,%c,%d\n",
    //   name, tel, postNo, basicAddress, detailAddress, working, gender, level);

    Member m = new Member();
    m.setName(name);             //데이터를 낱개의 객체에 각각 보관하고
    m.setTel(tel);
    m.setPostNo(postNo);
    m.setBasicAddress(basicAddress);
    m.setDetailAddress(detailAddress);
    m.setWorking(working);
    m.setLevel(level);
    m.setGender(gender);
    m.setCreatedDate(new Date(System.currentTimeMillis()).toString());

    this.memberDao.insert(m);      //memberDao에 그 객체 주소를 전부 저장
    // 응답 결과를 담을 맵 객체 준비
    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status","success");

    return contentMap;
  }

  @GetMapping("/members")     //get요청 들어왔을때 호출
  public Object getMembers() {

    Member[] members = this.memberDao.findAll();

    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status","success");
    contentMap.put("data",members);

    return contentMap;
  }

  //Get 요청 /더 디테일한 요청
  @GetMapping("/members/{memberNo}")
  public Object getMember(@PathVariable int memberNo) { //url에서 넘어오는{}값이 보드넘버로

    Member m = this.memberDao.findByNo(memberNo);

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


  @PutMapping("/members/{no}")
  public Object updateMember(Member member
      //@PathVariable int memberNo //Member인스턴스로 직접 받을 수 있다
      ) {

    //    System.out.printf("%d,%s,%s,%s,%s,%s,%b,%c,%d\n",
    //        member.getNo(), member.getName(), member.getTel(),
    //        member.getPostNo(), member.getBasicAddress(), member.getDetailAddress(),
    //        member.isWorking(), member.getGender(), member.getLevel());

    Map<String,Object> contentMap = new HashMap<>();

    Member old = this.memberDao.findByNo(member.getNo());
    if (old == null ) {
      contentMap.put("status", "failure");
      contentMap.put("data", "회원이 없습니다.");
      return contentMap;
    }
    member.setCreatedDate(old.getCreatedDate());

    this.memberDao.update(member);      //memberDao에 변경된 데이터를 전부 저장

    contentMap.put("status","success");

    return contentMap;
  }

  //삭제요청
  @DeleteMapping("/members/{memberNo}")
  public Object deleteMember(
      //파라미터를 낱개로 받을 때는 @pathvariable 생략 불가
      @PathVariable int memberNo) {

    Member m = this.memberDao.findByNo(memberNo);

    // 응답 결과를 담을 맵 객체 준비
    Map<String,Object> contentMap = new HashMap<>();

    if (m == null ) {
      contentMap.put("status","failure");
      contentMap.put("data","게시글이 없거나 암호가 맞지 않습니다.");
    } else {
      this.memberDao.delete(m);
      contentMap.put("status","success");
    }
    return contentMap;
  }
}
