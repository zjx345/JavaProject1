package tool;

import java.util.Comparator;

import vo.Student;

public class MyComparator implements Comparator<Student>{
	@Override
	public int compare(Student s1, Student s2) {
		return s1.getAccount().compareTo(s2.getAccount());
	}

}
