package bitcamp.myapp.dao;

import java.sql.Date;
<<<<<<< HEAD

import bitcamp.myapp.vo.Teacher;
import bitcamp.util.LinkedList;
import bitcamp.util.List;

public class TeacherDao {

	  List list;

	  int lastNo;

	  public TeacherDao(List list) {
	    this.list = list;
	  }

	  public void insert(Teacher t) {
	    t.setNo(++lastNo);
	    t.setCreatedDate(new Date(System.currentTimeMillis()).toString());
	    list.add(t);
	  }

	  public Teacher[] findAll() {
	    Teacher[] teachers = new Teacher[list.size()];
	    Object[] arr = list.toArray();
	    for (int i = 0; i < teachers.length; i++) {
	      teachers[i] = (Teacher) arr[i];
	    }
	    return teachers;
	  }

	  public Teacher findByNo(int no) {
	    Teacher t = new Teacher();
	    t.setNo(no);

	    int index = list.indexOf(t);
	    if (index == -1) {
	      return null;
	    }

	    return (Teacher) list.get(index);
	  }

	  public void update(Teacher t) {
	    int index = list.indexOf(t);
	    list.set(index, t);
	  }

	  public boolean delete(Teacher t) {
	    return list.remove(t);
	  }

	}
=======
import bitcamp.myapp.vo.Teacher;

public class TeacherDao extends ObjectDao {

  int lastNo;

  public Teacher findByNo(int no) {
    Teacher t = new Teacher();
    t.setNo(no);
    return (Teacher) this.get(this.indexOf(t));
  }

  @Override
  protected int indexOf(Object obj) {
    for (int i = 0; i < this.size(); i++) {
      if (((Teacher) this.get(i)).getNo() == ((Teacher) obj).getNo()) {
        return i;
      }
    }
    return -1;
  }

  @Override
  public void insert(Object object) {
    Teacher t = (Teacher) object;
    t.setNo(++lastNo);
    t.setCreatedDate(new Date(System.currentTimeMillis()).toString());

    super.insert(object);
  }
}
>>>>>>> d7a2983d627c2eb203690a46cbf8bd9d30bff8a6







