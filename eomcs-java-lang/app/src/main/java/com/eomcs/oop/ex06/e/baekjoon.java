package com.eomcs.oop.ex06.e;
import java.util.Scanner;

public class baekjoon {

  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);

    int A = sc.nextInt();
    int B = sc.nextInt();
    int C = sc.nextInt();

    if( A == B && B == C && C == A ) {
      System.out.println( A*1000 + 10000 );

    } else if( A == B || A == C) {
      System.out.println( A*100 + 1000 );
      if ( B == C ) {
        System.out.println( B*100 + 1000);
      }
    } else if( A != B && B != C && C != A) {
      int X = Math.max(A, B);
      int Y = Math.max(X, C);
      System.out.println(Y*100);
    }
  }
}

