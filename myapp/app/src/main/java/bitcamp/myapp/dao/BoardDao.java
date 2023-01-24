package bitcamp.myapp.dao;

<<<<<<< HEAD
import java.util.Date;
=======
import java.sql.Date;
>>>>>>> d7a2983d627c2eb203690a46cbf8bd9d30bff8a6
import bitcamp.myapp.vo.Board;
import bitcamp.util.List;

<<<<<<< HEAD
public class BoardDao {

	  // 특정 클래스를 지정하기 보다는
	  // 인터페이스를 통해 관계를 느슨하게 만든다.
	  List list;

	  public BoardDao(List list) {
	    // List 규칙에 따라서 만든 객체를 외부에서 주입받는다.
	    // 이렇게 하면 이 클래스는 ArrayList 또는 LinkedList와 같은
	    // 특정 클래스와 관계가 없어진다.
	    this.list = list;
	  }

	  int lastNo;

	  public void insert(Board board) {
	    board.setNo(++lastNo);
	    board.setCreatedDate(new Date(System.currentTimeMillis()).toString());
	    list.add(board);
	  }

	  public Board[] findAll() {
	    Board[] boards = new Board[list.size()];
	    Object[] arr = list.toArray();
	    for (int i = 0; i < boards.length; i++) {
	      boards[i] = (Board) arr[i];
	    }
	    return boards;
	  }

	  public Board findByNo(int no) {
	    Board b = new Board();
	    b.setNo(no);

	    int index = list.indexOf(b);
	    if (index == -1) {
	      return null;
	    }

	    return (Board) list.get(index);
	  }

	  public void update(Board b) {
	    int index = list.indexOf(b);
	    list.set(index, b);
	  }

	  public boolean delete(Board b) {
	    return list.remove(b);
	  }
	}
=======
public class BoardDao extends ObjectDao {

  // 가장 최근 게시글의 글 번호를 저장하는 필드
  // 가장 최근 게시글이 삭제되더라도 그 값은 그대로 유지할 것이다.
  int lastNo;

  // Board 객체를 게시글 번호를 찾는 메서드
  public Board findByNo(int no) {
    Board b = new Board();
    b.setNo(no);

    return (Board) this.get(this.indexOf(b));
  }

  @Override // 컴파일러에게 오버라이딩을 제대로 했는지 검사해 달라고 표시함
  protected int indexOf(Object obj) {
    for (int i = 0; i < this.size(); i++) {
      if (((Board) this.get(i)).getNo() == ((Board)obj).getNo()) {
        return i;
      }
    }
    return -1;
  }

  // 수퍼 클래스의 insert()는 객체를 등록할 때 번호를 자동증가시키는 기능이 없다.
  // 그러나 BoardDao는 그런 기능이 필요하다.
  // => 수퍼 클래스의 메서드를 서브 클래스의 역할이나 목적에 맞게 재정의 한다.
  // => 이것을 '오버라이딩(overriding)"이라 부른다.
  @Override
  public void insert(Object object) {
    // 객체를 배열에 담기 전에 그 객체의 번호를 설정한다.
    ((Board) object).setNo(++lastNo);

    // 인스턴스를 생성할 때의 날짜와 시각을 설정한다.
    ((Board) object).setCreatedDate(new Date(System.currentTimeMillis()).toString());

    // 그런 후에 수퍼 클래스에서 상속 받은 insert()를 사용하여 객체를 배열에 보관한다.
    super.insert(object);

    // super.insert() ?
    // => 현재 클래스에서 insert()를 찾지 말고, 수퍼 클래스에서 찾아 올라 가라!
  }
}






















>>>>>>> d7a2983d627c2eb203690a46cbf8bd9d30bff8a6

