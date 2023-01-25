package bitcamp.util;

import bitcamp.myapp.dao.DaoException;
//기존의 ObjectDao의 범용성을 높이기 위해 LinkedList로 변경
public class LinkedList implements List {

  private Node head;
  private Node tail;
  private int size;

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
  public int size() {
    return this.size;
  }

  @Override
  public Object get(int index) {
    if (index < 0 || index >= this.size) {
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
  @Override   //수퍼 클래스/인터페이스의 추상메서드도 재정의 가능하다!
  public Iterator iterator() {
    //이 LinkedList 객체에서 데이터를 꺼내주는 일을 할
    //Iterator 구현체를 만들어 리턴한다. this는 LinkedList객체주소!!
    return new ListIterator(this);
  }
}