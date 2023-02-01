package bitcamp.myapp.vo;

public class Teacher extends Member implements java.io.Serializable {


  private static final long serialVersionUID = 1L;

  private String email;
  private int degree;
  private String school;
  private String major;
  private int wage;

  public static Teacher create(String csv) {
    try {
      String[] values = csv.split(",");

      Teacher t = new Teacher();
      t.setNo(Integer.parseInt(values[0]));
      t.setName(values[1]);
      t.setTel(values[2]);
      t.setCreatedDate(values[3]);
      t.setEmail(values[4]);
      t.setDegree(Integer.parseInt(values[5]));
      t.setSchool(values[6]);
      t.setMajor(values[7]);
      t.setWage(Integer.parseInt(values[8]));
      return t;

    } catch (Exception e) {
      throw new RuntimeException("Teacher객체 생성 오류" , e);
    }

  }

  public String toCsvString() {
    return String.format("%d,%s,%s,%s,%s,%d,%s,%s,%d",
        this.getNo(),
        this.getName(),
        this.getTel(),
        this.getCreatedDate(),
        this.getEmail(),
        this.getDegree(),
        this.getSchool(),
        this.getMajor(),
        this.getWage());

  }

  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public int getDegree() {
    return degree;
  }
  public void setDegree(int degree) {
    this.degree = degree;
  }
  public String getSchool() {
    return school;
  }
  public void setSchool(String school) {
    this.school = school;
  }
  public String getMajor() {
    return major;
  }
  public void setMajor(String major) {
    this.major = major;
  }
  public int getWage() {
    return wage;
  }
  public void setWage(int wage) {
    this.wage = wage;
  }

}
