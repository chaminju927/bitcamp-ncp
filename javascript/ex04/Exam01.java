class Exam01 {      
                //클래스이름 파일이름 같게!
  public static void main(String[] args) {
    System.out.println("Hello!");
    //설계도 - 영어 대소문자 체크
    class Student {
      String name;
      int age;
      boolean working;
    }
      // 설계도에 따라 객체 생성(메모리 준비)
    Student obj = new Student();       //student설계도에따라 준비한 객체의 주소만 저장

      //객체 프로퍼티에 값 저장
      obj.name = "홍길동";
      obj.age = 20;
      obj.working = true;
      //obj.tel = "02-1111-2222"        //설계도에 없는 프로퍼티는 출력이 안된다
                                             //즉 컴파일 오류
      //객체 프로퍼티에 저장된 값 꺼내기
      System.out.println(obj.name);    //한글 깨짐 소스코드는 utf8이지만 cmd에서는 ms940
      System.out.println(obj.age);
      System.out.println(obj.working);

      java.util.HashMap obj2 = new java.util.HashMap();
      obj2.put("name", "홍길동");
      obj2.put("age", 20);
      obj2.put("working", true);

       System.out.println(obj2.get("name"));
       System.out.println(obj2.get("age"));
       System.out.println(obj2.get("working"));
      


  }
}