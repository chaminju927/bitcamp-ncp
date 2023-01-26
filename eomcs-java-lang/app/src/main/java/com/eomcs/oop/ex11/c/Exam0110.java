// inner class : 클래스 정의와 인스턴스 생성
package com.eomcs.oop.ex11.c;

class X {} // Top Level Class

class A {
  static void m1(int a) {
    //static 메서드는 A의 인스턴스를 저장하는 this 라는 변수가 없다.
  }
  void m2(int b) {
    //non-static 메서드는 A의 인스턴스를 저장할 this라는 변수가 있다.
  }

  class X {  // inner class, non-static 중첩 클래스
    // 컴파일러는 inner 클래스를 컴파일 할 때 다음과 같이
    // - 바깥 클래스의 인스턴스 주소를 저장할 필드를 추가하고,
    // - 바깥 클래스의 인스턴스의 주소를 파라미터로 받는 생성자를 만든다.
    //
    //    A outer;  필드 추가
    //    public X(A obj) { 생성자 추가
    //      this.outer = obj;
    //    }
  }

  static class Y {} //생성자()는 없지만 컴파일러가 나중에 생성
}                    //A클래스의 스태틱 중첩 클래스

public class Exam0110 {

  public static void main(String[] args) {
    // 레퍼런스 선언
    A.X obj;
    A.Y obj2;

    // 인스턴스 생성
    obj2 = new A.Y();   //스태틱 중첩 클래스는 바깥 클래스의 인스턴스 없이 생성 가능
    //obj = new A.X(); // 컴파일 오류! 바깥 클래스 A의 인스턴스 주소 없이 생성 불가!

    A.m1(0); // 스태틱 멤버를 사용할때는 인스턴스 없어도 된다.


    //1) 바깥 클래스의 인스턴스 준비
    A  outer = new A();

    // non-static 메서드를 호출하려면 인스턴스 주소가 필요하듯이,
    outer.m2(0);

    //2) inner class의 인스턴스 생성할 때도 바깥 클래스의 인스턴스 주소가 필요하다.
    obj = outer.new X();

    // 컴파일러는 컴파일 할 때 다음과 같이
    // - 바깥 클래스의 객체를 생성자에 전달하는 코드로 변경한다.
    //    obj = new A.X(outer);

  }

}
