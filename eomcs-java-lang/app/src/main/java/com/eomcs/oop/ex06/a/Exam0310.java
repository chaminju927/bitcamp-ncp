// 다형성 - 다형적 변수와 형변환(type casting) II
package com.eomcs.oop.ex06.a;

public class Exam0310 {

  public static void main(String[] args) {

    Car c = new Car();

    c.model = "티코"; // Vehicle의 인스턴스 변수. car는 vehicle 상속받으므로 사용 가능
    c.capacity = 5;  // Vehicle의 인스턴스 변수
    c.cc = 890;      // Car의 인스턴스 변수
    c.valve = 16;    // Car의 인스턴스 변수

    // 잘못된 형변환을 할 경우,
    // => 형변환(type casting)으로 컴파일러를 속일 수는 있지만,
    //    실행할 때 오류가 발생할 것이다.
    // => 속이지 말라!
    Sedan s = (Sedan) c; // 실행할 때 오류 발생! (runtime exception)
    s.sunroof = true;    // sedan은 car를 상속받음. 따라서 car는 sedan보다 좁은 범위인데
    s.auto = true;        // 더 확장된 기능을 가진 sedan으로 형변환 할 수 없다

  }

}










