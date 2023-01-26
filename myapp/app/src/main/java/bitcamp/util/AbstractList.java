package bitcamp.util;

public abstract class AbstractList implements List {

  protected int size;

  @Override
  public Object get(int index) {
    if (index < 0 || index >= this.size) {
      throw new IndexOutOfBoundsException("인덱스가 무효합니다.");
    }
    return null;
  }



  @Override
  public int size() {
    return this.size;
  }

  @Override
  public Iterator iterator() {


    // interator() 메서드 안에서만 사용하는 클래스라면
    // 이 메서드 안에 두는 것이 유지보수에 좋다   => local class
    // 이때, 인스턴스를 한 개만 만들어 사용하고, 클래스 크기도 작다면
    // 익명 클래스로 만드는 것이 코드를 간결하게 만든다.
    // => anonymous class = 객체 생성 코드 + 클래스 정의 + return문

    return new Iterator() { //retur문 + anonymous class

      int cursor;
      @Override
      public boolean hasNext() {
        // cursor가 유효한 인덱스를 가리키고 있는지 검사한다.
        return cursor >= 0 && cursor < AbstractList.this.size();
      }

      @Override
      public Object next() {
        // cursor가 가리키는 인덱스의 값을 꺼낸다.
        // => 작업 수행 후 cursor는 다음 인덱스를 가리킨다.
        return AbstractList.this.get(cursor++); //꺼낼때 인덱스 하나씩 증가시킴
      }
    };
    // 로컬 클래스의 생성자를 호출할때
    // 바깥 클래스의 인스턴스 주소를 넘길 필요가 없다.
    // 컴파일러가 대신 처리한다.
    // 바깥 클래스의 인스턴스 주소를 넘길 수 있도록 생성자 호출을 변경한다.
    //
    // return new ListIterator(this);
  }


  // AbstractList 클래스에서만 사용하는 클래스라면
  // 이 클래스 안에 두는 것이 유지보수에 좋다
  //
  //=> 스태틱 중첩 클래스 (static nested class)
  //  static class ListIterator implements Iterator {
  //
  //    List list;
  //    int cursor;
  //
  //    public ListIterator(List list) { //리스트 규칙에 따라 만든 객체
  //      this.list = list ;  //파라미터에 ArrayList/ LinkedList 객체 주소 뭐든 가능
  //    }
  //
  //    @Override
  //    public boolean hasNext() {
  //      // cursor가 유효한 인덱스를 가리키고 있는지 검사한다.
  //      return cursor >= 0 && cursor < list.size();
  //    }
  //
  //    @Override
  //    public Object next() {
  //      // cursor가 가리키는 인덱스의 값을 꺼낸다.
  //      // => 작업 수행 후 cursor는 다음 인덱스를 가리킨다.
  //      return list.get(cursor++); //꺼낼때 인덱스 하나씩 증가시킴
  //    }
  //
  //
  //  }
}
