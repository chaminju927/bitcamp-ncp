package bitcamp.myapp.vo;

public class Student extends Member implements java.io.Serializable{

  private static final long serialVersionUID = 1L;

  private String postNo;
  private String basicAddress;
  private String detailAddress;
  private boolean working;
  private char gender;
  private byte level;

  public static Student create(String csv) {
    try {
      String[] values = csv.split(",");

      Student s = new Student();
      s.setNo(Integer.parseInt(values[0]));
      s.setName(values[1]);
      s.setTel(values[2]);
      s.setCreatedDate(values[3]);
      s.setPostNo(values[4]);
      s.setBasicAddress(values[5]);
      s.setDetailAddress(values[6]);
      s.setWorking(Boolean.parseBoolean(values[7]));
      s.setGender(values[8].charAt(0));
      s.setLevel(Byte.parseByte(values[9]));
      return s;

    } catch (Exception e) {
      throw new RuntimeException("Student객체 생성 오류", e);
    }
  }


  public String toCsvString() {
    return String.format("%d,%s,%s,%s,%s,%s,%s,%b,%s,%d",
        this.getNo(),
        this.getName(),
        this.getTel(),
        this.getCreatedDate(),
        this.getPostNo(),
        this.getBasicAddress(),
        this.getDetailAddress(),
        this.isWorking(),
        this.getGender(),
        this.getLevel());
  }


  public String getPostNo() {
    return postNo;
  }
  public void setPostNo(String postNo) {
    this.postNo = postNo;
  }
  public String getBasicAddress() {
    return basicAddress;
  }
  public void setBasicAddress(String basicAddress) {
    this.basicAddress = basicAddress;
  }
  public String getDetailAddress() {
    return detailAddress;
  }
  public void setDetailAddress(String detailAddress) {
    this.detailAddress = detailAddress;
  }
  public boolean isWorking() {
    return working;
  }
  public void setWorking(boolean working) {
    this.working = working;
  }
  public char getGender() {
    return gender;
  }
  public void setGender(char gender) {
    this.gender = gender;
  }
  public byte getLevel() {
    return level;
  }
  public void setLevel(byte level) {
    this.level = level;
  }
}

