package bitcamp.myapp.dao;

<<<<<<< HEAD
import java.util.Date;
import bitcamp.myapp.vo.Student;
import bitcamp.util.List;


public class StudentDao {

	  List list;

	  int lastNo;

	  public StudentDao(List list) {
	    this.list = list;
	  }


	  public void insert(Student s) {
	    s.setNo(++lastNo);
	    s.setCreatedDate(new Date(System.currentTimeMillis()).toString());
	    list.add(s);
	  }

	  public Student[] findAll() {
	    Student[] students = new Student[list.size()];
	    Object[] arr = list.toArray();
	    for (int i = 0; i < students.length; i++) {
	      students[i] = (Student) arr[i];
	    }
	    return students;
	  }

	  public Student findByNo(int no) {
	    Student s = new Student();
	    s.setNo(no);

	    int index = list.indexOf(s);
	    if (index == -1) {
	      return null;
	    }
	    return (Student) list.get(index);
	  }

	  public void update(Student s) {
	    int index = list.indexOf(s);
	    list.set(index, s);
	  }

	  public boolean delete(Student s) {
	    return list.remove(s);
	  }

	}
=======
import java.sql.Date;
import bitcamp.myapp.vo.Student;

public class StudentDao extends ObjectDao {

  int lastNo;

  public Student findByNo(int no) {
    Student s = new Student();
    s.setNo(no);
    return (Student) this.get(this.indexOf(s));
  }

  @Override
  protected int indexOf(Object obj) {
    for (int i = 0; i < this.size(); i++) {
      if (((Student) this.get(i)).getNo() == ((Student) obj).getNo()) {
        return i;
      }
    }
    return -1;
  }

  @Override
  public void insert(Object object) {
    Student s = (Student) object;
    s.setNo(++lastNo);
    s.setCreatedDate(new Date(System.currentTimeMillis()).toString());

    super.insert(object);
  }
}







>>>>>>> d7a2983d627c2eb203690a46cbf8bd9d30bff8a6
