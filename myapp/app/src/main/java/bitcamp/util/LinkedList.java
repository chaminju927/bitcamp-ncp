package bitcamp.util;

//기존의 ObjectDao의 범용성을 높이기 위해 LinkedList로 변경
public class LinkedList extends AbstractList {

  private Node head;
  private Node tail;

  @Override
  public void add(Object value) {
    Node node = new Node(value);
    if (this.tail == null) { // size == 0, head == null
      this.head = this.tail = node;

    } else {
      this.tail.next = node;
      this.tail = node;
    }

    this.size++;
  }

  @Override
  public Object[] toArray() {
    Object[] values = new Object[this.size];
    int index = 0;
    Node cursor = this.head;

    while (cursor != null) {
      values[index++] = cursor.value;
      cursor = cursor.next;
    }
    return values;
  }

  @Override
  public Object set(int index, Object value) {
    if (index < 0 || index >= this.size) {
      throw new IndexOutOfBoundsException("인덱스가 유효하지 않습니다.");
    }

    Node cursor = head;
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

  @Override
  public boolean remove(Object value) {
    Node prevNode = null;
    Node deletedNode = null;
    Node cursor = this.head;

    while (cursor != null) {
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

    if (prevNode == null) {   //맨 앞 노드를 삭제하는 경우
      this.head = this.head.next;
      deletedNode.next = null;
      if (this.head == null) {
        this.tail = null;
      }

    } else {   //중간이나 맨끝 노드를 삭제하는 경우
      prevNode.next = deletedNode.next;
      deletedNode.next = null;
      if (prevNode.next == null) {  //마지막 노드인 경우
        this.tail = prevNode;
      }
    }
    this.size--;
    return true;
  }

  @Override
  public int indexOf(Object b) {
    Node cursor = head;
    int i = 0;

    while (cursor != null) {
      if (cursor.value.equals(b)) {  //no외 다른 필드로도 찾을수있도록, 이 클래스에서 메서드가 완성되도록
        return i;         // Object클래스의 equals()메서드를 활용하고, 메서드를 재정의한다.
      }
      cursor = cursor.next;
      i++;
    }
    return -1;
  }



  @Override
  public Object get(int index) {
    super.get(index);

    Node cursor = head;
    int i = 0;

    while (i < index) {
      cursor = cursor.next;
      i++;
    }
    return cursor.value;
  }


  // LinkedList 클래스에서만 사용하는 클래스라면
  // LinkedList 클래스 안에 두는 것이 유지 보수에 더 낫다.
  // - 패키지 외부에 노출되지 않으므로 다른 개발자가 헷갈릴 이유가 없다.
  // => 스태틱 중첩 클래스(static nested class)
  //
  static class Node {
    Object value;
    Node next;

    public Node() {}

    public Node(Object value) {
      this.value = value;
    }

  }
}