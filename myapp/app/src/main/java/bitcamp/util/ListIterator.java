package bitcamp.util;

public class ListIterator implements Iterator {

  List list;
  int cursor;

  public ListIterator(List list) { //리스트 규칙에 따라 만든 객체
    this.list = list ;  //파라미터에 ArrayList/ LinkedList 객체 주소 뭐든 가능
  }

  @Override
  public boolean hasNext() {
    // cursor가 유효한 인덱스를 가리키고 있는지 검사한다.
    return cursor >= 0 && cursor < list.size();
  }

  @Override
  public Object next() {
    // cursor가 가리키는 인덱스의 값을 꺼낸다.
    // => 작업 수행 후 cursor는 다음 인덱스를 가리킨다.
    return list.get(cursor++); //꺼낼때 인덱스 하나씩 증가시킴
  }


}
