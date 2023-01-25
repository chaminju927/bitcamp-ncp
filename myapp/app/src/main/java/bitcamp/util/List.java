package bitcamp.util;

// 객체 목록을 다루는 기능을 규정한다.
// linkedlist, arraylist의 중복된 기능을 한번에 다룬다.

// List 인터페이스는 Iterable규칙을 상속받기 때문에
// List를 구현하는 클래스는 Iterable규칙도 함께 구현해야 한다.
public interface List extends Iterable {
  void add(Object value);
  Object[] toArray();
  Object get(int index);
  Object set(int index, Object value);
  boolean remove(Object value);
  int indexOf(Object value);
  int size();
}
