package bitcamp.myapp.dao;

public class Node {
<<<<<<< HEAD
	 Object value;
	  Node next;

	  public Node() {}

	  public Node(Object value) {
	    this.value = value;
	  }

=======
  Object value; //현재 노드의 데이터를 저장
  Node next;   //다음 노드의 주소를 next에 저장

  public Node() {}  //초기 상태는 first node/ last node 모두 없는 상태이므로
  //null로 초기화한다

  public Node(Object value) {
    this.value = value;
  }
>>>>>>> d7a2983d627c2eb203690a46cbf8bd9d30bff8a6
}
