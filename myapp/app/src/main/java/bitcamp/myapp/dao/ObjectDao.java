package bitcamp.myapp.dao;

import java.util.Arrays;

public abstract class ObjectDao {


  private static final int SIZE = 100;

  private int count;
  protected Object[] objects = new Object[SIZE];  //objects는 객체 주소를 담는 레퍼런스들의 배열
  //protected로 바꿔서 접근 가능하도록 한다

  public void insert(Object object) {            //다형적 변수, object 자식객체들이 들어올수 있음
    this.objects[this.count++] = object;
  }

  public Object[] findAll() {
    return Arrays.copyOf(objects, count);
  }

  //  public Object findByNo(int no) {  //번호를 식별자로 사용하는 경우에만 유효.범용 메서드 아님
  //    for (int i = 0; i < this.count; i++) {     //따라서 제거한다. 무엇을찾을지 매번 달라지므로
  //      if (this.objects[i].getNo() == no) {
  //        return this.objects[i];
  //      }
  //    }
  //    return null;
  //  }

  public void update(Object object) {
    this.objects[this.indexOf(object)] = object;
  }

  public void delete(Object object) {
    for (int i = this.indexOf(object) + 1; i < this.count; i++) {
      this.objects[i - 1] = this.objects[i];
    }
    this.objects[--this.count] = null; // 레퍼런스 카운트를 줄인다.
  }
  // 객체의 위치를 찾는 것은 객체의 타입에 따라 다를 수 있기 때문에
  // 이 클래스에서 정의하지 말고, 서브 클래스에서 정의할 수 있도록
  // 그 구현의 책임을 위임해야 한다
  protected abstract int indexOf(Object b);   //sub class마다 구현이 다를수 있어 superclass에서 구현 x

  public int size() {
    return this.count;
  }
  // 개발하는 중에 서브 클래스 들이 공통으로 필요로 하는 기능을 발견하게 된다.
  // 그런 상황이면 이렇게 수퍼 클래스의 해당 메서드를 정의하면 된다.
  public Object get(int i) {
    if(i < 0 || i >= this.count) {
      return null;
    }
    return objects[i];
  }
}
