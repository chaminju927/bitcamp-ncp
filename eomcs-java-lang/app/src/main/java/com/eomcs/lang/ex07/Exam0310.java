package com.eomcs.lang.ex07;

//# 메서드 : call by value
//
public class Exam0310 {

  static void swap(int a, int b) {
    System.out.printf("swap(): a=%d, b=%d\n", a, b);
    int temp = a;  //temp값에 a(=100) 넣고
    a = b;             //a에 b값(=200) 넣고
    b = temp;           //b에 temp값(100) 넣기
    System.out.printf("swap(): a=%d, b=%d\n", a, b); //swap함수 실행이 끝나면 temp,a,b 로컬변수는 소멸
  }

  public static void main(String[] args) {
    int a = 100;  //main 메서드의 ab와
    int b = 200;   //위의 swap메서드 ab는 다르다

    // swap() 호출할 때 a 변수의 값과 b 변수의 값을 넘긴다.
    // => 그래서 "call by value"라 부른다.
    // => 비록 swap()에서 a와 b라는 이름의 변수가 있지만,
    //    이 변수는 main()에 있는 변수와 다른 변수이다.
    swap(a, b);
    System.out.printf("main(): a=%d, b=%d\n", a, b);
  }
}

// call by value
// => 아규먼트가 primitive data type인 경우,
//    메서드를 호출할 때 값을 넘긴다.
// => 자바에서는 primitive data type에 대해서
//    메모리(변수) 주소를 넘기는 방법이 없다.
//