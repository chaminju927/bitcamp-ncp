package bitcamp.util;

<<<<<<< HEAD
import bitcamp.myapp.dao.DaoException;

public class LinkedList {
	
	private Node head;
	private Node tail;
	private int size;
	
	
	public void add(Object value) {
		Node node = new Node(value);
		if (this.tail == null) {  //전체 링크의 tail이 비어있으면
			this.head = this.tail = node;  // this.head는 새 노드의 head?
		} else {
			this.tail.next = node;
			this.tail = node;
		}
		this.size++;
	}
	
	
	public Object[] toArray() {    //findAll
		Object[] values = new Object[this.size];   //???
		int index = 0;
		Node cursor = this.head;
		
		while (cursor != null) {
			values[index++] = cursor.value;
			cursor = cursor.next;
		}
		return values;
 	}
	
	
	public Object set(int index, Object value) {  //변경
		if (index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException("인덱스가 유효하지 않습니다!");
		}
		
		Node cursor = head;
		int i = 0;
		
		while (cursor != null) {   //왜 반복문?
			if(i == index) {
				Object old = cursor.value;
				cursor.value = value;
				return old;
			}
			cursor = cursor.next;
			i++;
		}
		return null;
	}
	
	
	public boolean remove(Object value) {
		Node prevNode = null;
		Node deletedNode = null;
		Node cursor = this.head;
		
		while (cursor != null) {
			if(cursor.value.equals(value)) {
				deletedNode = cursor;
				break;
			}
			prevNode = cursor;
			cursor = cursor.next;
		}
		
		if (deletedNode == null) {
			return false;
		}
		
		if (prevNode == null) {
			this.head = this.head.next;
			deletedNode.next = null;
			if (this.head == null) {
				this.tail = null;
			}
		} else {
			prevNode.next = deletedNode.next;
			deletedNode.next = null;
			if(prevNode.next == null) {
				
			}
		}
		this.size--;
		return true;
	}
	
	
	public int indexOf(Object b) {
		Node cursor = head;   //head의미? 링크드리스트 전체에서의 head가 아닌지?
		int i = 0;
		
		while(cursor != null) {
			if(cursor.value.equals(b)) {
				return i;
			}
			cursor = cursor.next;
			i++;
		}
		return -1;
	}
	
	public int size() {
		return this.size;
	}
	
	public Object get(int index) {
		if(index < 0 || index>= this.size) {
			throw new DaoException("인덱스가 무효합니다!");
		}
		
		Node cursor = head;
		int i = 0;
		
		while (i < index) {
			cursor = cursor.next;
			i++;
		}
		return cursor.value;
	}
=======
public class LinkedList {

  private Node head;  //연결된 링크중 가장 첫번째 노드가 헤드
  private Node tail;  // 연결된 링크중 가장 마지막 노드가 꼬리
  private int size;  //이때 사이즈는 한 노드의 크기가 아닌 연결된 전체노드를 말한다
  //사이즈가 2 라는 것은 두개의 노드가 연결된 것

  public void add(Object value) {  //노드를 추가
    Node node = new Node(value);
    if (this.tail == null) {   //마지막 tail에 주소값이 없다면
      this.head = this.tail = node;
    } else {   //
      this.tail.next = node; //다음 노드를 만들고
      this.tail = node;
    }
    this.size++;
  }

  public Object[] toArray() { // 노드에 값을 배열 형태로 저장한다
    Object[] values = new Object[this.size];
    int index = 0;
    Node cursor = this.head;

    while (cursor != null) {
      values[index++] = cursor.value;
      cursor = cursor.next;
    }
    return values;
  }

  public Object set(int index, Object value) { /////////////////?
    if (index< 0 || index >= this.size) { // index값이 음수거나 노드사이즈보다 클때
      throw new IndexOutOfBoundsException("인덱스가 유효하지 않습니다");
    }
    Node cursor = head;   //굳이 이렇게 하는 이유...? 그냥 바로 head라 하면 안되나?
    int i = 0;

    while (cursor != null) {
      if (i == index) {
        Object old = cursor.value;
        cursor.value = value;
        return old;
      }
      cursor = cursor.next;
      i++;
    }
    return null;
  }

  public boolean remove(Object value) {
    Node prevNode = null;
    Node deletedNode = null;
    Node cursor = this.head;

    while (cursor != null) {  //
      if (cursor.value.equals(value)) {
        deletedNode = cursor;
        break;
      }
      prevNode = cursor;
      cursor = cursor.next;
    }
    if (deletedNode == null) {
      return false;
    }

  }



>>>>>>> d7a2983d627c2eb203690a46cbf8bd9d30bff8a6
}
