package bitcamp.boot.app.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import bitcamp.boot.app.dao.MemberDao;
import bitcamp.boot.app.vo.Member;

@CrossOrigin(origins = {"http://localhost:5500", "http://127.0.0.1:5500"})
@RestController

public class MemberController {

  MemberDao memberDao = new MemberDao();

  @PostMapping("/members")
  public Object addMember(
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String tel,
      @RequestParam(required = false) String postNo,
      @RequestParam(required = false) String basicAddress,
      @RequestParam(required = false) String detailAddress,
      @RequestParam(required = false) boolean working,
      @RequestParam(required = false) char gender,
      @RequestParam(required = false) byte level,
      @RequestParam(required = false) String password) {

    Member m = new Member();
    m.setNo(0);
    m.setName(name);
    m.setTel(tel);
    m.setPostNo(postNo);
    m.setBasicAddress(basicAddress);
    m.setDetailAddress(detailAddress);
    m.setWorking(working);
    m.setGender(gender);
    m.setLevel(level);
    m.setPassword(password);
    m.setCreatedDate(new Date(System.currentTimeMillis()).toString());
    this.memberDao.insert(m);

    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status","success");

    return contentMap;
  }

  @GetMapping("/members")
  public Object getMembers() {
    Member[] members = this.memberDao.findAll();

    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status","success");

    return contentMap;
  }

  @GetMapping("/members/{memberNo}")
  public Object getMember(@PathVariable int memberNo) {

    Member m = this.memberDao.findByNo(memberNo);

    Map<String,Object> contentMap = new HashMap<>();

    if(m == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "해당하는 회원이 없습니다.");
    } else {
      contentMap.put("status", "success");
      contentMap.put("data", m);
    }
    return contentMap;
  }

  @PostMapping("/members/{memberNo}")
  public Object updateMember(
      @PathVariable int memberNo,

      @RequestParam(required = false) String name,
      @RequestParam(required = false) String tel,
      @RequestParam(required = false) String postNo,
      @RequestParam(required = false) String basicAddress,
      @RequestParam(required = false) String detailAddress,
      @RequestParam(required = false) boolean working,
      @RequestParam(required = false) char gender,
      @RequestParam(required = false) byte level,
      @RequestParam(required = false) String password ) {

    Map<String,Object> contentMap = new HashMap<>();

    Member old = this.memberDao.findByNo(memberNo);
    if (old == null || !old.getPassword().equals(password)) {
      contentMap.put("status", "failure");
      contentMap.put("data", "게시글이 없거나 권한이 없습니다.");
      return contentMap;
    }

    Member m = new Member();
    m.setNo(memberNo);
    m.setName(name);
    m.setTel(tel);
    m.setPostNo(postNo);
    m.setBasicAddress(basicAddress);
    m.setDetailAddress(detailAddress);
    m.setWorking(working);
    m.setGender(gender);
    m.setLevel(level);
    m.setPassword(password);
    m.setCreatedDate(new Date(System.currentTimeMillis()).toString());
    this.memberDao.update(m);

    this.memberDao.update(m);
    contentMap.put("status","success");

    return contentMap;
  }

  @DeleteMapping("/members/{memberNo}")
  public Object deleteMember(
      @PathVariable int memberNo,
      @RequestParam String name,
      @RequestParam String password) {

    Member m = this.memberDao.findByNo(memberNo);

    Map<String,Object> contentMap = new HashMap<>();

    if (m == null || m.getPassword().equals(password)) {
      contentMap.put("status", "failure");
      contentMap.put("data", "회원 정보가 없거나 암호가 맞지 않습니다.");
    } else {
      this.memberDao.delete(m);
      contentMap.put("status", "success");
    }
    return contentMap;
  }
}


