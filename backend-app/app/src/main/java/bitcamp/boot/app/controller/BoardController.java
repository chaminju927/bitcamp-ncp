package main.java.bitcamp.boot.app.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import bitcamp.boot.app.dao.BoardDao;
import bitcamp.boot.app.vo.Board;

// 다음 클래스가 클라이언트 요청을 처리하는 일을 한다는 것을 SpringBoot에게 알리는 표시
// => SpringBoot는 다음 클래스의 인스턴스를 생성해서 보관해 둔다.
// => "/hello" 라는 URL로 클라이언트 요청이 들어오면 해당 메서드를 호출한다.
@RestController
//@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})  // origins에서 들어오는 요청만 허락한다
public class BoardController {        //html origin 서버에서 받아온 데이터 //url이 다수이면 배열 {}에 담기

  BoardDao boardDao = new BoardDao();
  public BoardController(BoardDao boardDao) {
    this.boardDao  = boardDao;
  }

  //Post요청
  @PostMapping("/boards")     //스프링부트가 호출하는 메서드
  public Object addBoard(
      @RequestParam(required = false) String title,   //title이라는 이름으로 넘어오는 데이터를 첫번재 파라미터에
      @RequestParam(required = false) String content,  //클라이언트가 보낸 데이터를 title,content,password라는
      @RequestParam(required = false) String password ) {     //이름으로 저장

    Board b = new Board();
    b.setNo(0);
    b.setTitle(title);             //데이터를 낱개의 객체에 각각 보관하고
    b.setContent(content);
    b.setPassword(password);
    this.boardDao.insert(b);      //boardDao에 그 객체 주소를 전부 저장
    b.setCreatedDate(new Date(System.currentTimeMillis()).toString());

    // 응답 결과를 담을 맵 객체 준비
    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status","success");  //클라이언트쪽으로 put요청

    return contentMap;
  }

  @GetMapping("/boards")     //get요청 들어왔을때 호출
  public Object getBoards() {
    Board[] boards = this.boardDao.findAll();

    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status","success");
    contentMap.put("data",boards);

    return contentMap;
  }

  //Get 요청 /더 디테일한 요청
  @GetMapping("/boards/{boardNo}")
  public Object getBoard(@PathVariable int boardNo) { //url에서 넘어오는{}값이 보드넘버로

    Board b = this.boardDao.findByNo(boardNo);

    // 응답 결과를 담을 맵 객체 준비
    Map<String,Object> contentMap = new HashMap<>();

    if (b == null) {
      contentMap.put("status","failure");
      contentMap.put("data","해당 번호의 게시글이 없습니다.");
    } else {
      contentMap.put("status","success");
      contentMap.put("data", b);
    }
    return contentMap;

  }


  @PutMapping("/boards/{boardNo}")
  public Object updateBoard(
      @PathVariable int boardNo,

      @RequestParam(required = false) String title,   //title이라는 이름으로 넘어오는 데이터를 첫번재 파라미터에
      @RequestParam(required = false) String content,
      @RequestParam(required = false) String password ) {  //클라이언트가 보낸 데이터를 쪼갬

    Map<String,Object> contentMap = new HashMap<>();

    Board old = this.boardDao.findByNo(boardNo);
    if (old == null || !old.getPassword().equals(password)) {
      contentMap.put("status", "failure");
      contentMap.put("data", "게시글이 없거나 권한이 없습니다.");
      return contentMap;
    }

    Board b = new Board();
    b.setNo(boardNo);
    b.setTitle(title);             //데이터를 낱개의 객체에 각각 보관하고
    b.setContent(content);
    b.setPassword(password);
    b.setCreatedDate(old.getCreatedDate());
    b.setViewCount(old.getViewCount());

    this.boardDao.update(b);      //boardDao에 변경된 데이터를 전부 저장

    contentMap.put("status","success");

    return contentMap;
  }

  //삭제요청
  @DeleteMapping("/boards/{boardNo}")
  public Object deleteBoard(
      @PathVariable int boardNo,
      @RequestParam(required = false) String password) {   //()는 암호가 필수가 아니라고 명시함

    Board b = this.boardDao.findByNo(boardNo);

    // 응답 결과를 담을 맵 객체 준비
    Map<String,Object> contentMap = new HashMap<>();

    if (b == null || b.getPassword().equals(password)) {
      contentMap.put("status","failure");
      contentMap.put("data","게시글이 없거나 암호가 맞지 않습니다.");
    } else {
      this.boardDao.delete(b);
      contentMap.put("status","success");
    }
    return contentMap;
  }
}
